package cn.lanyu.user;

import java.util.Collection;
import java.util.List;

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
}