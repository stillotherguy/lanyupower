package cn.lanyu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lanyu.client.Client;
import cn.lanyu.user.User;
import cn.lanyu.user.UserContext;
import cn.lanyu.user.UserDao;
import cn.lanyu.user.UserService;

@Controller
@RequestMapping("/pwd")
public class ChangePwdController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder encoder;
	
	@RequestMapping(value = "/change", method = RequestMethod.POST, params="pwd")
	@ResponseBody
	public boolean changePwdAction(@RequestParam String pwd, @RequestParam(required = false) String username) {
		if(StringUtils.isEmpty(username)){
			username = UserContext.getUsername();
			if(StringUtils.isEmpty(username)){
				return false;
			}
			//throw new UsernameNotFoundException("用户名不存在");
		}
		Client client = userService.getByNo(username);
		if(client == null){
			User user = userDao.getByUsername(username);
			if(user == null || !encoder.matches(pwd, user.getPassword())){
				return false;
			}
			userDao.changePwd(username, pwd);
			return true;
		}
		if(encoder.matches(pwd, client.getPassword())){
			userDao.changePwd(username, pwd);
			return true;
		}
		
		return false;
	}
	
	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public String changePwd(Model model) {
		model.addAttribute("username", UserContext.getUsername());
		return "common/changepwd";
	}

	

}
