# Full stack simple Conway's Game of Life web app.
A full-stack web app for the classic evergreen Conway's Game of Life. This is an attempt on making something interactive through a web interface and, meanwhile, experimenting with some anti-patterns or "not-really-best-practices" for the Java way of doing things.

## How to build and run
There are multiple ways to build and run this app. The easiest one is to use Maven's Spring Boot plugin:
```
mvn spring-boot:run
```
Then, go to your `localhost:8080` and play the hell out of it.
If you don't have Maven installed on your system, you can use the embedded one inside the root directory.

## Technologies used
- Backend:
    - Java 1.8
    - Maven
    - Spring Boot
    - Lombok
    - Hibernate
    - MySQL
    - Slf4j + Logback
- Frontend:
    - HTML, JS, CSS
    - JSP
    - Bootstrap
    - JQuery
    - Vanilla-tilt

## Project choices
- Everything about data manipulation has been done server-sided. This can be seen as a trade-off between security and robustness in exchange for a heavy and avoidable communication between the two parts.
My main reason, anyway, was to play more with the backend side. If there were no concern about storing and elaborating data, I'd have considered doing everything client-side, maybe even using WebGL.
- I've deliberately avoided as much as possible Exception management. I've done this just to have fun and experiment with a different way of doing things.
- I've tried the functional approach, with a template method through generics, so that the communication between the Controller layer and the Service one would have been painless and hopefully cleaner, at least for me:
```Java
gameService.generatePopulation(strategyType).ifNoErrors(
        populationData -> session.setAttribute(SESSION_BYTE_DATA, populationData));
```
- Lombok was used to avoid boilerplate code and to speed up prototyping.
