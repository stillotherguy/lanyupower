package cn.lanyu.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        final String targetUrl = super.determineTargetUrl(request, response);
        
        if(targetUrl.contains("static")) {
        	return "/";
        }

        return targetUrl;
    }
	
}
