package cn.lanyu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.google.common.collect.Lists;

import cn.lanyu.user.Authority;
import cn.lanyu.user.InsuranceDao;
import cn.lanyu.user.UserContext;

@ControllerAdvice
public class Common {
	@Autowired
	private InsuranceDao insuranceDao;
	
	@ModelAttribute("allcount")
    public List<Integer> count() {
		if(!UserContext.isAuthenticated()){
			return null;
		}
		final Authority authority = Authority.valueOf(UserContext.getAuthority().getAuthority());
		final String username = UserContext.getUsername();
		switch(authority){
		case ROLE_ADMIN:
			return Lists.newArrayList(insuranceDao.countUnfinished(), insuranceDao.countFinishedWithoutFeedback(), insuranceDao.countFinishedWithFeedback());
		case ROLE_REPAIR:
			return Lists.newArrayList(insuranceDao.countUnfinishedByUsername(username), insuranceDao.countFinishedWithoutFeedbackByUsername(username), insuranceDao.countFinishedWithFeedbackByUsername(username));
		case ROLE_LEADER:
			break;
		case ROLE_USER:
			return Lists.newArrayList(insuranceDao.countUnfinishedByClientno(username), insuranceDao.countFinishedWithoutFeedbackByClientno(username), insuranceDao.countFinishedWithFeedbackByClientno(username));
		default:
		break;
		}
		return null;  
		//应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model
    }  
  
    /*@InitBinder  
    public void initBinder(WebDataBinder binder) {  
        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器");  
    }  
  
    @ExceptionHandler(UnauthenticatedException.class)  
    @ResponseStatus(HttpStatus.UNAUTHORIZED)  
    public String processUnauthenticatedException(NativeWebRequest request, UnauthenticatedException e) {  
        System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出UnauthenticatedException异常时执行");  
        return "viewName"; //返回一个逻辑视图名  
    }  */
}
