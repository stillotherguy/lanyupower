package cn.lanyu.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        final String targetUrl = super.determineTargetUrl(request, response);
        
        if(targetUrl.contains("static") || isAjaxRequest(request)) {
        	return "/";
        }

        return targetUrl;
    }
	
	/**
	 * 判断请求是否是ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}
	
}
