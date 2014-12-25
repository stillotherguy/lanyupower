package cn.lanyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.lanyu.user.User;
import cn.lanyu.user.UserService;

@Controller
@RequestMapping("/signup")
public class SignupController {
	//TODO @Autowired
	private UserService userSerivce;
	
	@RequestMapping(method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("command", new User());
		return "signup/signup";
	}
	
	/**
	 * TODO:index页面要有signup按钮，针对特定人员
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String signupAction(User user) {
		userSerivce.signup(user);
		return "redirect:/index";
	}
}
