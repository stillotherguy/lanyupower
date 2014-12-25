package cn.lanyu.base;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring容器上下文，以静态变量保存Spring ApplicationContext,提供静态的getBean方法.
 * 
 * @author Ricky
 */
public class SpringContext implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	private static final Logger logger = LoggerFactory.getLogger(SpringContext.class);

	/**
	 * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContext.applicationContext = applicationContext; // NOSONAR
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext not defined!");
		}
		return applicationContext;
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		logger.debug("getBean:{}", name);
		return (T) applicationContext.getBean(name);
	}

	public static <T> T getBean(T t) {
		return t;
	}

	/**
	 * 返回同一类型的beans
	 * 
	 * @param type
	 * @return
	 */
	public static <T> Map<String, T> getBeans(Class<T> type) {
		return applicationContext.getBeansOfType(type);
	}

	public static String[] getBeanNames(Class<?> type) {
		return applicationContext.getBeanNamesForType(type);
	}

	/**
	 * 判断Spring ApplicationContext容器中是否有id为name的Bean
	 * 
	 * @param name BeanName
	 * @return
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	/**
	 * 清除applicationContext静态变量.
	 */
	public static void cleanApplicationContext() {
		applicationContext = null;
	}
}
