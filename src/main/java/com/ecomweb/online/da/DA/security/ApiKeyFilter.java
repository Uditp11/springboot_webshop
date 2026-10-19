package com.ecomweb.online.da.DA.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;

@Component
public class ApiKeyFilter extends GenericFilterBean {

    @Value("${api.key}")
    private String apiKey;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI();


        // Case-insensitive so /saas/catalog is covered. CORS preflight requests carry no
        // custom headers, so OPTIONS is let through for the Svelte client.
        if (path.toLowerCase().startsWith("/saas") && !"OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            String requestApiKey = httpRequest.getHeader("X-API-KEY");

            if (!apiKey.equals(requestApiKey)) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("Unauthorized");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
