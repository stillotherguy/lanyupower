package cn.lanyu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import cn.lanyu.base.page.Page;
import cn.lanyu.client.Client;
import cn.lanyu.insurance.InsuranceDao;
import cn.lanyu.user.UserContext;
import cn.lanyu.user.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserService userService;
	@Autowired
	private InsuranceDao insuranceDao;
	
	@RequestMapping(value = "/{searchType}/{param}", method = RequestMethod.GET)
	@ResponseBody
	public List<Client> index(@PathVariable String searchType, @PathVariable String param){
		switch(searchType){
		case "card":
			return Lists.newArrayList(userService.getByNo(param));
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
	
	@RequestMapping(value = "/insurance/unhandle", method = RequestMethod.GET)
	public String index(){
		return "common/unhandle";
	}
	
	@RequestMapping("/unhandle")
	public String unhandle(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageUnfinished(page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/unhandle/{currentPage}")
	public String unhandle(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageUnfinished(page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/finish")
	public String finish(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithFeedbackByClientId(UserContext.getUserId(), page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/finish/{currentPage}")
	public String finish(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithFeedback(page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/nofeed")
	public String nofeed(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedback(page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/nofeed/{currentPage}")
	public String nofeed(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedback(page));
		
		return "common/unhandle";
	}
}
