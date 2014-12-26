package cn.lanyu.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.lanyu.user.Authority;
import cn.lanyu.user.UserContext;

@Controller
@RequestMapping("/index")
public class IndexController {
	public String index() {
		GrantedAuthority auth = UserContext.getAuthority();
		if(auth != null) {
			Authority authority = Authority.valueOf(auth.getAuthority());
			switch(authority) {
			case ROLE_ADMIN:
				return "admin/index";
			case ROLE_EMP:
				return "emp/index";
			case ROLE_LEADER:
				return "leader/index";
			case ROLE_USER:
				return "client/index";
			default:
				break;
			}
		}
		return "index";
	}
}
