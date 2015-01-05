package cn.lanyu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import cn.lanyu.base.page.Page;
import cn.lanyu.insurance.InsuranceDao;
import cn.lanyu.user.UserContext;

import com.google.common.collect.Maps;

@Controller
@RequestMapping("/emp")
public class EmpController {
	@Autowired
	private InsuranceDao insuranceDao;
	
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
		
		return "common/unhandle";
	}
	
	@RequestMapping("/finish/{currentPage}")
	public String pageFinish(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithFeedbackByUserId(UserContext.getUserId(), page));
		return "common/unhandle";
	}
	
	@RequestMapping("/finish")
	public String finish(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithFeedbackByUserId(UserContext.getUserId(), page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/nofeed/{id}/{currentPage}")
	public String pageNofeedWithId(Model model, @PathVariable long id, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedbackByUserId(id, page));
		
		return "common/unhandle";
	}
	
	@RequestMapping("/nofeed/{currentPage}")
	public String pageNofeed(Model model, @PathVariable int currentPage) {
		Page page = new Page(currentPage);
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedbackByUserId(UserContext.getUserId(), page));
		return "common/unhandle";
	}
	
	@RequestMapping("/nofeed")
	public String nofeed(Model model) {
		Page page = new Page();
		model.addAttribute("page", page)
		.addAttribute("insurances", insuranceDao.pageFinishedWithoutFeedbackByUserId(UserContext.getUserId(), page));
		
		return "common/unhandle";
	}
	
	/**
	 * 上传图片逻辑
	 * 
	 * @param mf
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/upload/{uploadType}", method = RequestMethod.POST)
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
	}
}
