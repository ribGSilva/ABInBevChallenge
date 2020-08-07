package br.com.gabriel.abambevchallenge.gatewayapi.filter;

import br.com.gabriel.abambevchallenge.gatewayapi.beans.dto.ErrorDTO;
import br.com.gabriel.abambevchallenge.gatewayapi.exceptions.GatewayApiException;
import br.com.gabriel.abambevchallenge.gatewayapi.service.AuthorizationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = "/product/*")
public class OAuth2Filter implements Filter {

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
            if (Objects.isNull(authorization) || authorization.isEmpty()) {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                authorizationService.validateToken(authorization);
                chain.doFilter(request, response);
            }
        } catch (GatewayApiException e) {
            ErrorDTO errorDTO = new ErrorDTO() {{
                setCode(e.getCode());
                setMessage(e.getMessage());
            }};
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.getWriter().print(new JSONObject(errorDTO).toString());
        } catch (Exception e) {
            e.printStackTrace();
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
