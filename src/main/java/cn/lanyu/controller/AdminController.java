package cn.lanyu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import cn.lanyu.base.page.Page;
import cn.lanyu.client.Client;
import cn.lanyu.insurance.Insurance;
import cn.lanyu.insurance.InsuranceDao;
import cn.lanyu.insurance.Insurance.Type;
import cn.lanyu.user.User;
import cn.lanyu.user.UserContext;
import cn.lanyu.user.UserDao;
import cn.lanyu.user.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserService userService;
	@Autowired
	private InsuranceDao insuranceDao;
	@Autowired
	private UserDao userDao;
	
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
	
	@RequestMapping(value = "/repair/{clientid}", method = RequestMethod.GET)
	public String repair(Model model, @PathVariable long clientid){
		model.addAttribute("emps", userService.getAllEmp());
		model.addAttribute("client", userService.getById(clientid));
		model.addAttribute("type", Type.values());
		return "admin/repair";
	}
	
	@RequestMapping(value = "/repair", method = RequestMethod.POST)
	public String repair(RedirectAttributes attr, Insurance insurance){
		insurance.setStartDate(new Date());
		insuranceDao.insert(insurance);
		attr.addFlashAttribute("message", "提交报修单成功");
		return "redirect:/index";
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
		
		return "common/finish";
	}
	
	@RequestMapping("/finish/{currentPage}")
	public String finish(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithFeedback(page));
		
		return "common/finish";
	}
	
	@RequestMapping("/nofeed")
	public String nofeed(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedback(page));
		
		return "common/nofeed";
	}
	
	@RequestMapping("/nofeed/{currentPage}")
	public String nofeed(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedback(page));
		
		return "common/nofeed";
	}
	
	@RequestMapping("/repairs/{currentPage}")
	public String repairs(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("repairs", userDao.pageQueryAllRepairEmp(page));
		
		return "admin/repairemp";
	}
	
	@RequestMapping("/service/{currentPage}")
	public String services(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("services", userDao.pageQueryAllServiceEmp(page));
		
		return "admin/serviceemp";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(RedirectAttributes attr, @PathVariable long id) {
		userDao.delete(new User(id));
		attr.addFlashAttribute("message", "删除员工成功");
		
		return "redirect:/index";
	}
}
