package ca.sheridancollege.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class LoggingAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger LOG = Logger.getLogger(LoggingAccessDeniedHandler.class.getName());

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException {
        LOG.warning("Access Denied for user " + request.getUserPrincipal().getName() + " on " + request.getRequestURI());
        
        // Redirect to the access denied page
        response.sendRedirect(request.getContextPath() + "/access-denied");
    }
}
