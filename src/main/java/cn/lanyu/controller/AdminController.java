package cn.lanyu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lanyu.user.Client;
import cn.lanyu.user.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/{searchType}/{param}", method = RequestMethod.GET)
	@ResponseBody
	public Client index(@PathVariable String searchType, @PathVariable String param){
		switch(searchType){
		case "card":
			return userService.getByNo(param);
		case "address":
			return userService.getByAddress(param);
		case "phone":
			return userService.getByMobile(param);
		case "name":
			return userService.getByName(param);
		case "mobile":
			return userService.getByMobile(param);
		default:
			return null;
		}
	}
}
