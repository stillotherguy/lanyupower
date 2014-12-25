package cn.lanyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lanyu.user.Client;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping(value = "/{searchType}/{param}", method = RequestMethod.GET)
	@ResponseBody
	public Client index(@PathVariable String searchType, @PathVariable String param){
		switch(searchType){
		case "":
			break;
		case "":
			break;
		case "":
			break;
		case "":
			break;
		default:
			break;
		}
		
		return null;
	}
}
