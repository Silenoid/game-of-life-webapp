package com.sileno.gol.controller;

import com.sileno.gol.service.GameService;
import com.sileno.gol.service.ServiceResponse;
import com.sileno.gol.utils.GolUtils;
import com.sileno.gol.utils.ImageRenderer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class HomeController {

    private final GameService gameService;

    private static final String SESSION_BYTE_DATA = "byte_data";
    private static final String VIEW_HOME = "home";

    @Autowired
    public HomeController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "/")
    public ModelAndView welcomePage(HttpSession session) {
        log.info("Welcome page for session {}", session.getId());
        ModelAndView page = new ModelAndView(VIEW_HOME);

        gameService.isSessionPresent(session.getId()).ifNoErrors(responseIsPresent -> {
            log.info("Is session {} present? {}", session.getId(), responseIsPresent);
            page.addObject("disable_load_button", !responseIsPresent);
        });
        page.addObject("session_id", session.getId());

        return page;
    }

    @GetMapping(value = "/save")
    public void saveState(HttpServletResponse response, HttpSession session) {
        log.debug("Saving request for session {}", session.getId());
        gameService.saveState(session.getId(), (byte[]) session.getAttribute(SESSION_BYTE_DATA)).ifNoErrors(everythingIsOk -> {
            if (everythingIsOk)
                log.info("Game state saved succesfully");
            else
                log.warn("Game state not saved because of errors");
        });
    }

    @GetMapping(value = "/load")
    public ResponseEntity<String> loadState(HttpServletResponse response, HttpSession session) {
        log.debug("Loading request for session {}", session.getId());

        ServiceResponse<byte[]> serviceResponse = gameService.loadState(session.getId());
        serviceResponse.ifNoErrors(populationData -> {
                    session.setAttribute(SESSION_BYTE_DATA, populationData);
                }
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(generateImageFromSessionData(session), headers, HttpStatus.OK);
    }

    @GetMapping(value = "/generate/{strategy_type:[0-2]}")
    public ResponseEntity<String> generatePopulation(HttpServletResponse response, HttpSession session, @PathVariable("strategy_type") int strategyType) {
        log.debug("Generate population with strategy {} for session {}", strategyType, session.getId());

        gameService.generatePopulation(session.getId(), strategyType).ifNoErrors(populationData -> {
                    session.setAttribute(SESSION_BYTE_DATA, populationData);
                }
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(generateImageFromSessionData(session), headers, HttpStatus.OK);
    }

    @GetMapping(value = "/forward")
    public ResponseEntity<String> forwardGeneration(HttpServletResponse response, HttpSession session) {
        log.debug("Forward request");

        gameService.forwardGeneration(session.getId(), (byte[]) session.getAttribute(SESSION_BYTE_DATA)).ifNoErrors(forwardedData -> {
                    session.setAttribute(SESSION_BYTE_DATA, forwardedData);
                }
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(generateImageFromSessionData(session), headers, HttpStatus.OK);
    }

    private String generateImageFromSessionData(HttpSession session) {
        boolean[][] booleanMatrix = GolUtils.deserialize((byte[]) session.getAttribute(SESSION_BYTE_DATA));
        String base64Image = ImageRenderer.renderToBase64(booleanMatrix);
        if(base64Image.isEmpty())
            log.warn("Generate empty image for sessio {}", session.getId());
        return base64Image;
    }

}
