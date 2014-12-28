package cn.lanyu.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.lanyu.user.UserContext;

@Controller
@RequestMapping({"/login","/index.xhtml"})
public class LoginController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		if(UserContext.isAuthenticated()) {
			return "redirect:/index";
		}
		return "login/login";
	}
	
	@RequestMapping(value = "/{message}", method = RequestMethod.GET)
	public String error(RedirectAttributes model, @PathVariable String message) {
		if(UserContext.isAuthenticated()) {
			return "redirect:/index";
		}
		switch(message) {
		case "401":
			model.addFlashAttribute("message", "用户名或密码错误");
			break;
		case "403":
			model.addFlashAttribute("message", "无权限");
			break;
		default:
			model.addFlashAttribute("message", "未知错误");
			break;
		}
		return "redirect:/login";
	}
}
