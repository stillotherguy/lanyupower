package cn.lanyu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.lanyu.user.UserContext;
import cn.lanyu.user.UserService;

@RestController
@RequestMapping("/pwd")
public class ChangePwdController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/change", method = RequestMethod.POST, params="pwd")
	public boolean changePwdAction(@RequestParam String pwd, @RequestParam(required = false) String username) {
		if(StringUtils.isEmpty(username)){
			username = UserContext.getUsername();
			if(StringUtils.isEmpty(username)){
				throw new UsernameNotFoundException("用户名不存在");
			}
			//throw new UsernameNotFoundException("用户名不存在");
		}
		userService.changePwd(username, pwd);
		return true;
	}
	
	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public String changePwd(Model model) {
		model.addAttribute("logined", UserContext.isAuthenticated());
		return "common/changepwd";
	}

	

}
