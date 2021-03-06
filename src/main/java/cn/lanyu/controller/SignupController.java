package cn.lanyu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.lanyu.user.User;
import cn.lanyu.user.UserDao;
import cn.lanyu.user.UserService;

@Controller
@RequestMapping("/signup")
public class SignupController {
	@Autowired
	private UserService userSerivce;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("command", new User());
		return "signup/signup";
	}
	
	@RequestMapping(value = "/exist", method = RequestMethod.GET)
	@ResponseBody
	public boolean signup(String username) {
		return !userDao.isExist(username);
	}
	
	/**
	 * TODO:index页面要有signup按钮，针对特定人员
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String signupAction(User user, RedirectAttributes attr) {
		userSerivce.signup(user);
		attr.addFlashAttribute("message", "注册成功");
		return "redirect:/index";
	}
}
