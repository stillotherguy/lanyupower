package cn.lanyu.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContext {
	@SuppressWarnings("unchecked")
	public static GrantedAuthority getAuthority() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			if(authorities instanceof List) {
				return ((List<GrantedAuthority>)authorities).get(0);
			}
			return (GrantedAuthority) authorities.toArray()[0];
		}
		return null;
	}
	
	public static Object getPassword() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null && authentication instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;
			return token.getCredentials();
		}
		return null;
	}
	
	public static String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null && authentication instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;
			return ((User)token.getPrincipal()).getUsername();
		}
		return null;
	}
	
	public static boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null){
			return false;
		}
		return authentication.isAuthenticated();
	}
}
