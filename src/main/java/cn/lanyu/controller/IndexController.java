package cn.lanyu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.lanyu.auth.Authority;
import cn.lanyu.insurance.Insurance.Type;
import cn.lanyu.user.UserContext;
import cn.lanyu.user.UserService;

@Controller
@RequestMapping("/index")
public class IndexController {
	@Autowired
	private UserService userService;
	
	@RequestMapping
	public String index(Model model) {
		final GrantedAuthority auth = UserContext.getAuthority();
		if(auth.getAuthority().equals("ROLE_USER")){
			final Object password = UserContext.getPassword();
			if(password == null){
				return "common/changepwd";
			}
		}
		final String no = UserContext.getUsername();
		if(auth != null) {
			final Authority authority = Authority.valueOf(auth.getAuthority());
			switch(authority) {
			case ROLE_ADMIN:
				break;
			case ROLE_REPAIR:
				return "emp/index";
			case ROLE_LEADER:
				return "leader/index";
			case ROLE_USER:
				model.addAttribute("allEmp", userService.getAllEmp());
				model.addAttribute("client", userService.getByNo(no));
				model.addAttribute("type", Type.values());
				break;
			default:
				break;
			}
		}
		
		return "common/index";
	}
}
