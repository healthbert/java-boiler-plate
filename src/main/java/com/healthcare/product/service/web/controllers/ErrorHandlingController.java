package com.healthcare.product.service.web.controllers;

import com.healthcare.product.service.domain.ExceptionResponse;
import com.mindscapehq.raygun4java.webprovider.RaygunServletClient;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;

/**
 * Created by wichon on 2/13/17.
 */

@ControllerAdvice
public class ErrorHandlingController {
    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandlingController.class);

    @Value("${spring.profiles.active}")
    private String env;

    @Value("${raygun.apikey}")
    private String raygunApiKey;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> generalException(
            Exception ex, HttpServletRequest request) throws Exception {
        LOG.error(String.format("The following request failed: %s %s",
                request.getMethod() , request.getRequestURI()));
        LOG.error("Unhandled exception: ", ex);

        // Set Raygun tracking data
        ArrayList<String> tags = new ArrayList<String>();
        tags.add(env);
        HashMap<String, String> customData = new HashMap<String, String>();
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            if (request instanceof ContentCachingRequestWrapper) {
                ContentCachingRequestWrapper wrapper = (ContentCachingRequestWrapper) request;
                byte[] buf = wrapper.getContentAsByteArray();
                if (buf.length > 0) {
                    customData.put("Body",
                            new String(buf, 0, buf.length, wrapper.getCharacterEncoding()));
                }
            }
        }
        customData.put("Scheme", request.getScheme());
        RaygunServletClient raygunServletClient =
                new RaygunServletClient(raygunApiKey, request);
        raygunServletClient.Send(ex, tags, customData);

        // Set exception response object
        ExceptionResponse response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getClass().getSimpleName(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}