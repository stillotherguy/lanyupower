package cn.lanyu.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.lanyu.base.dao.IGenericDao;
import cn.lanyu.base.page.Page;

public abstract class BaseService<T> implements IBaseService<T> {
	private Class<T> entityClass;
	
	protected IGenericDao<T> dao;
	
	//通过set方式注入可以让子类覆盖灵活注入自己想要的类型
	@Autowired
    public void setBaseRepository(IGenericDao<T> dao) {
        this.dao = dao;
    }
	
	public BaseService(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Override
	public List<T> queryAll() {
		return dao.queryAll();
	}

	@Override
	public List<T> queryIndexPage() {
		String hql = "from " + entityClass.getSimpleName();
		return dao.queryForList(hql, null, new Page(1));
	}

	@Override
	public List<T> queryPage(Page page) {
		String hql = "from " + entityClass.getSimpleName();
		return dao.queryForList(hql, null, page);
	}

	@Override
	public T queryById(long id) {
		return dao.get(id);
	}
	
	@Transactional
	@Override
	public void add(T t) {
		dao.insert(t);
	}
	
	@Transactional
	@Override
	public void update(T t) {
		dao.update(t);
	}
	
	@Transactional
	@Override
	public void delete(T t) {
		dao.delete(t);
	}

}
