package cn.lanyu.base.service;

import java.util.List;

import cn.lanyu.base.page.Page;

//@Transactional(readOnly = true)
public interface IBaseService <T>{
	List<T> queryAll();
	
	List<T> queryIndexPage();

	List<T> queryPage(Page page);
	
	T queryById(long id);
	
	void add(T t);

	void update(T t);
	
	void delete(T t);
	
}
 