package cn.lanyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/leader")
public class StatisticController {
	
	@RequestMapping("/satisfied")
	public String satisfied(){
		return "leader/satisfied";
	}
	
	@RequestMapping("/repair")
	public String repair(){
		return "leader/repair";
	}
	
	@RequestMapping("/complaint")
	public String complaint(){
		return "leader/complaint";
	}
}
