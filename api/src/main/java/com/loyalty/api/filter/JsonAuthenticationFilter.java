package com.loyalty.api.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.dto.api.LoginRequest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private boolean postOnly = true;

    public JsonAuthenticationFilter() {
        super();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else if (!MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
            throw new AuthenticationServiceException("Authentication content type not supported: " + request.getContentType());
        } else {
            LoginRequest creds;
            try {
                creds = this.obtainCredentials(request);
            } catch (IOException e) {
                throw new AuthenticationServiceException("Fail read authentication object", e);
            }
            String username = creds.getUsername();
            String password = creds.getPassword();

            if(username == null) {
                username = "";
            }

            if(password == null) {
                password = "";
            }

            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    /**
     * Solution taken from SO post
     * @see @url https://stackoverflow.com/a/19572145/2633197
     * @param request HttpServletRequest
     * @return LoginRequest dto
     * @throws IOException
     */
    public LoginRequest obtainCredentials(HttpServletRequest request) throws IOException {
        /*
         * HttpServletRequest can be read only once
         */
        StringBuffer sb = new StringBuffer();
        String line = null;

        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null){
            sb.append(line);
        }

        //json transformation
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(sb.toString(), LoginRequest.class);
    }
}
