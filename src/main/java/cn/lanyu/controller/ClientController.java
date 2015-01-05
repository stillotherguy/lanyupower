package cn.lanyu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.lanyu.base.page.Page;
import cn.lanyu.insurance.InsuranceDao;
import cn.lanyu.user.UserContext;

@Controller
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private InsuranceDao insuranceDao;
	
	@RequestMapping("/unhandle/{id}/{currentPage}")
	public String pageUnhandleWithId(Model model, @PathVariable long id, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageUnfinishedByClientId(id, page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/unhandle/{currentPage}")
	public String pageUnhandle(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageUnfinishedByClientId(UserContext.getUserId(), page));
		return "common/unhandle";
	}
	
	@RequestMapping("/unhandle")
	public String unhandle(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageUnfinishedByClientId(UserContext.getUserId(), page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/finish/{id}/{currentPage}")
	public String pageFinishWithId(Model model, @PathVariable long id, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithFeedbackByClientId(id, page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/finish/{currentPage}")
	public String pageFinish(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithFeedbackByClientId(UserContext.getUserId(), page));
		return "common/unhandle";
	}
	
	@RequestMapping("/finish")
	public String finish(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithFeedbackByClientId(UserContext.getUserId(), page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/nofeed/{id}/{currentPage}")
	public String pageNofeedWithId(Model model, @PathVariable long id, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedbackByClientId(id, page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/nofeed/{currentPage}")
	public String pageNofeed(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedbackByClientId(UserContext.getUserId(), page));
		return "common/unhandle";
	}
	
	@RequestMapping("/nofeed")
	public String nofeed(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedbackByClientId(UserContext.getUserId(), page));
		
		return "common/unhandle";
	}
}
