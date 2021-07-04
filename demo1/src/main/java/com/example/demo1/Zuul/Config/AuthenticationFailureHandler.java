package com.example.demo1.Zuul.Config;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
@Component
public class AuthenticationFailureHandler
		implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if(null!=request && null!=request.getAttribute("invalid_login_message") )
		{
		String jsonMessage = request.getAttribute("invalid_login_message").toString();
		response.getWriter().write(jsonMessage);
		response.setContentType("application/json");
		}
	}
}