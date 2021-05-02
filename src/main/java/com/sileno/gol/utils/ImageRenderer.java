package com.sileno.gol.utils;

import com.sileno.gol.config.AppConfiguration;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageRenderer {

    public static String renderToBase64(boolean[][] booleanMatrix) {
        return imgToBase64String(render(booleanMatrix), "png");
    }

    private static BufferedImage render(boolean[][] booleanMatrix) {
        BufferedImage bufferedImage = new BufferedImage(AppConfiguration.MATRIX_SQUARE_SIZE, AppConfiguration.MATRIX_SQUARE_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();

        graphics.setColor(Color.BLACK);
        for (int x = 0; x < AppConfiguration.MATRIX_SQUARE_SIZE; x++) {
            for (int y = 0; y < AppConfiguration.MATRIX_SQUARE_SIZE; y++) {
                if(booleanMatrix[x][y])
                    graphics.drawLine(x, y, x, y);
            }
        }

        graphics.dispose();
        return bufferedImage;
    }

    public static String renderSampleToBase64() {
        BufferedImage bufferedImage = new BufferedImage(AppConfiguration.MATRIX_SQUARE_SIZE, AppConfiguration.MATRIX_SQUARE_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();

        graphics.setColor(Color.BLACK);
        graphics.drawString("SAMPLE IMAGE", (int)(bufferedImage.getWidth() * 0.5), (int)(bufferedImage.getHeight() * 0.5));
        graphics.drawRect(0, 0, AppConfiguration.MATRIX_SQUARE_SIZE - 1, AppConfiguration.MATRIX_SQUARE_SIZE - 1);

        graphics.dispose();
        return imgToBase64String(bufferedImage, "png");
    }

    public static String imgToBase64String(RenderedImage img, final String formatName) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, formatName, Base64.getEncoder().wrap(os));
            return os.toString(StandardCharsets.UTF_8.name());
        } catch (IOException ioe) {
            log.error("Error during img -> base64", ioe);
            return null;
        }
    }

    public static BufferedImage base64StringToImg(final String base64String) {
        try {
            return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64String)));
        } catch (IOException ioe) {
            log.error("Error during base64 -> img", ioe);
            return null;
        }
    }
}
