package com.nnk.springboot.configuration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * CustomErrorController is a controller class that handles error routing in the application.
 * It implements the ErrorController interface provided by Spring Boot.
 */
@Controller
public class CustomErrorController implements ErrorController {

    /**
     * This method handles the routing for different HTTP error status codes.
     * It checks the status code of the incoming request and returns the corresponding error page.
     *
     * @param request the incoming HttpServletRequest
     * @return a string representing the name of the error page
     */
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // handle HTTP 404 Not Found error
                return "error/404";

            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                // handle HTTP 403 Forbidden error
                return "error/403";
            }
        }

        // handle HTTP 500 Internal Server Error
        return "error/500";
    }
}
