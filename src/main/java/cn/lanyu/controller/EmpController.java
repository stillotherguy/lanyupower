package cn.lanyu.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.lanyu.base.page.Page;
import cn.lanyu.file.FileService;
import cn.lanyu.insurance.Insurance;
import cn.lanyu.insurance.InsuranceDao;
import cn.lanyu.user.UserContext;
import cn.lanyu.util.BeanUtils;

import com.google.common.collect.Maps;

@Controller
@RequestMapping("/emp")
public class EmpController {
	@Autowired
	private InsuranceDao insuranceDao;
	@Autowired
	private FileService fileService;
	@Autowired
	private SessionFactory sessionFactory;
	
	@RequestMapping(value = "/feedback/{id}", method = RequestMethod.GET)
	public String handle(Model model, @PathVariable long id) {
		model.addAttribute("insurance", insuranceDao.get(id));
		
		return "emp/handle";
	}
	
	@RequestMapping(value = "/feedback", method = RequestMethod.POST)
	public String handleAction(RedirectAttributes model, Insurance insurance) {
		insurance.setEndDate(new Date());
		insurance.setFinished(true);
		insuranceDao.updateWithSQL(insurance);
		//sessionFactory.getCurrentSession().evict(target);
		//BeanUtils.copyProperties(insurance, target);
		//insuranceDao.update(insurance);
		model.addFlashAttribute("message", "处理成功");
		return "redirect:/emp/unhandle";
	}

	@RequestMapping("/unhandle/{id}/{currentPage}")
	public String pageWithId(Model model, @PathVariable long id, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageUnfinishedByUserId(id, page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/unhandle/{currentPage}")
	public String page(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageUnfinishedByUserId(UserContext.getUserId(), page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/unhandle")
	public String index(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageUnfinishedByUserId(UserContext.getUserId(), page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/finish/{id}/{currentPage}")
	public String pageFinishWithId(Model model, @PathVariable long id, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithFeedbackByUserId(id, page));
		
		return "common/finish";
	}
	
	@RequestMapping("/finish/{currentPage}")
	public String pageFinish(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithFeedbackByUserId(UserContext.getUserId(), page));
		return "common/finish";
	}
	
	@RequestMapping("/finish")
	public String finish(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithFeedbackByUserId(UserContext.getUserId(), page));
		
		return "common/finish";
	}
	
	@RequestMapping("/nofeed/{id}/{currentPage}")
	public String pageNofeedWithId(Model model, @PathVariable long id, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedbackByUserId(id, page));
		
		return "common/nofeed";
	}
	
	@RequestMapping("/nofeed/{currentPage}")
	public String pageNofeed(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedbackByUserId(UserContext.getUserId(), page));
		return "common/nofeed";
	}
	
	@RequestMapping("/nofeed")
	public String nofeed(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedbackByUserId(UserContext.getUserId(), page));
		
		return "common/nofeed";
	}
	
	/**
	 * 上传图片逻辑
	 * 
	 * @param mf
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(@RequestParam(value = "file") MultipartFile mf, HttpServletRequest request){
		Assert.notNull(mf, "上传的文件不能为null");
		Map<String, Object> map = Maps.newHashMap();
		String path = request.getServletContext().getRealPath("/");
		List<String> result = fileService.upload(mf, path);
		switch (result.get(0)) {
		case "type":
			map.put("state", false);
			map.put("message", "文件类型不符");
			break;
		case "size":
			map.put("state", false);
			map.put("message", "文件过大");
			break;
		case "exception":
			map.put("state", false);
			map.put("message", "服务器发生错误");
			break;
		case "success":
			map.put("state", true);
			map.put("file", result.get(1));
			break;
		default:
			map.put("state", false);
			map.put("message", "未知错误");
		}
		return map;
	}
}
