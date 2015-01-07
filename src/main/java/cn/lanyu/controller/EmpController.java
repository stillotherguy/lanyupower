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
@RequestMapping("/emp")
public class EmpController {
	@Autowired
	private InsuranceDao insuranceDao;
	
	@RequestMapping(value = "/feedback/{id}", method = RequestMethod.GET)
	public String handle(Model model, @PathVariable long id) {
		model.addAttribute("insurance", insuranceDao.get(id));
		
		return "emp/handle";
	}
	
	@RequestMapping(value = "/feedback", method = RequestMethod.POST)
	public String handleAction(Model model, Insurance insurance) {
		insurance.setEndDate(new Date());
		insuranceDao.update(insurance);
		model.addAttribute("message", "处理成功");
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
	/*@RequestMapping(value = "/upload/{uploadType}", method = RequestMethod.POST)
	@ResponseBody
	public Map upload(@RequestParam(value = "upfile") MultipartFile mf, HttpServletRequest request,
			@PathVariable String uploadType)
	{
		Assert.notNull(mf, "上传的文件不能为null");
		UploadType type = Enum.valueOf(UploadType.class, uploadType.toUpperCase());
		Map<String, String> map = Maps.newHashMap();
		if (OptionalUtil.isNull(type)) {
			map.put("state", "提交参数有误");
			return map;
		}
		String path = request.getServletContext().getRealPath("/");
		List<String> result = fileService.upload(mf, path);
		switch (result.get(0)) {
		case "type":
			map.put("state", "文件类型不符");
			break;
		case "size":
			map.put("state", "文件过大");
			break;
		case "exception":
			map.put("state", "内部错误");
			break;
		case "success":
			switch (type) {
			case IMAGEUP:
				map.put("url", fileServer + "/site/" + result.get(1));
				map.put("title", result.get(2));
				map.put("state", "SUCCESS");
				map.put("original", mf.getOriginalFilename());
				break;
			case FILEUP:
				map.put("url", fileServer + "/site/" + result.get(1));
				map.put("original", mf.getOriginalFilename());
				map.put("fileType", result.get(3));
				map.put("state", "SUCCESS");
				break;
			}
			break;
		default:
			map.put("state", "未知错误");
		}
		return map;
	}*/
}
