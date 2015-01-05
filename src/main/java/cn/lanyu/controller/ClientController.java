package cn.lanyu.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.lanyu.base.page.Page;
import cn.lanyu.insurance.Insurance;
import cn.lanyu.insurance.InsuranceDao;
import cn.lanyu.user.UserContext;

@Controller
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private InsuranceDao insuranceDao;
	
	@RequestMapping(value = "/unhandle/{id}/{currentPage}", method = RequestMethod.GET)
	public String pageUnhandleWithId(Model model, @PathVariable long id, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageUnfinishedByClientId(id, page));
		
		return "common/unhandle";
	}
	
	@RequestMapping(value = "/unhandle/{currentPage}", method = RequestMethod.GET)
	public String pageUnhandle(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageUnfinishedByClientId(UserContext.getUserId(), page));
		return "common/unhandle";
	}
	
	@RequestMapping(value = "/unhandle", method = RequestMethod.GET)
	public String unhandle(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageUnfinishedByClientId(UserContext.getUserId(), page));
		
		return "common/unhandle";
	}
	
	@RequestMapping(value = "/finish/{id}/{currentPage}", method = RequestMethod.GET)
	public String pageFinishWithId(Model model, @PathVariable long id, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithFeedbackByClientId(id, page));
		
		return "common/unhandle";
	}
	
	@RequestMapping(value = "/finish/{currentPage}", method = RequestMethod.GET)
	public String pageFinish(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithFeedbackByClientId(UserContext.getUserId(), page));
		return "common/unhandle";
	}
	
	@RequestMapping(value = "/finish", method = RequestMethod.GET)
	public String finish(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithFeedbackByClientId(UserContext.getUserId(), page));
		
		return "common/unhandle";
	}
	
	@RequestMapping(value = "/nofeed/{id}/{currentPage}", method = RequestMethod.GET)
	public String pageNofeedWithId(Model model, @PathVariable long id, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedbackByClientId(id, page));
		
		return "common/unhandle";
	}
	
	@RequestMapping(value = "/nofeed/{currentPage}", method = RequestMethod.GET)
	public String pageNofeed(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedbackByClientId(UserContext.getUserId(), page));
		return "common/unhandle";
	}
	
	@RequestMapping(value = "/nofeed", method = RequestMethod.GET)
	public String nofeed(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedbackByClientId(UserContext.getUserId(), page));
		
		return "common/unhandle";
	}
	
	@RequestMapping(value = "/repair", method = RequestMethod.POST)
	public String nofeed(Insurance insurance) {
		insurance.setStartDate(new Date());
		return "redirect:/index";
	}
}
