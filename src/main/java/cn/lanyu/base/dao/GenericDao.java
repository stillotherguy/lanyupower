package cn.lanyu.base.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.lanyu.base.page.Page;

public abstract class GenericDao<T> implements IGenericDao<T> {

	private Class<T> entityClass;

	public GenericDao(Class<T> clazz) {
		this.entityClass = clazz;
	}

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void insert(T t) {
		getSession().save(t);
	}

	@Override
	public void delete(T t) {
		getSession().delete(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}
	
	@Override
	public void updateBySql(String sql) {
	    SQLQuery query = getSession().createSQLQuery(sql);  
	    query.executeUpdate(); 	
	}
	
	@Override
	public void updateBySql(String sql, Object[] params) {
	    SQLQuery query = getSession().createSQLQuery(sql);  
	    setQueryParams(query, params);
	    query.executeUpdate(); 	
	}


	@Override
	public void merge(T t) {
		getSession().merge(t);
	}

	@Override
	public T get(Long id) {
		return (T) getSession().get(entityClass, id);
	}
	
	@Override
	public int delete(String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		int delEntity = query.executeUpdate();
		return delEntity;
	}

	@Override
	public List<T> queryAll() {
		String hql = "from " + entityClass.getSimpleName();
		return queryForList(hql, null);
	}
	
	@Override
	public T queryForObject(String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		return (T) query.uniqueResult();
	}
	
	@Override
	public T queryForTopObject(String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		return (T) query.setFirstResult(0).setMaxResults(1).uniqueResult();
	}

	@Override
	public List<T> queryForList(String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		return query.list();
	}

	@Override
	public List<T> queryForList(final String hql, final Object[] params, final int recordNum) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		return query.setFirstResult(0).setMaxResults(recordNum).list();
	}

	@Override
	public List<T> queryForList(String hql, Object[] params, Page page) {
		generatePageTotalCount(hql, params, page);
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		query.setFirstResult(page.getFirstIndex());
		query.setMaxResults(page.getPageSize());
		return query.list();
	}

	@Override
	public int queryForInt(String originHql, Object[] params) {
		String generatedCountHql = "select count(*) " + originHql;
		Query countQuery = getSession().createQuery(generatedCountHql);
		setQueryParams(countQuery, params);
		return ((Long) countQuery.uniqueResult()).intValue();
	}

	@Override
	public List<Map> queryForMapPage(String originHql, Object[] params, Page page) {
		Query mapQuery = getSession().createQuery(originHql);
		setQueryParams(mapQuery, params);
		mapQuery.setFirstResult(page.getFirstIndex());
		mapQuery.setMaxResults(page.getPageSize());
		return mapQuery.list();
	}

	@Override
	public List<Map> queryForMap(String originHql, Object[] params) {
		Query mapQuery = getSession().createQuery(originHql);
		setQueryParams(mapQuery, params);
		return mapQuery.list();
	}
	
	@Override
	public List<T> queryPage(Page page) {
		String hql = "from " + entityClass.getSimpleName();
		return queryForList(hql, null, page);
	}
	
	@Override
	public List<T> queryPageHql(Page page, String hql) {
		return queryForList(hql, null, page);
	}

	/**
	 * 该方法会改变参数page的totalCount字段
	 * 
	 * @param originHql
	 *            原始hql语句
	 * @param params
	 *            原始参数
	 * @param page
	 *            页面对象
	 */
	private void generatePageTotalCount(String originHql, Object[] params, Page page) {
		String generatedCountHql = "select count(*) " + originHql;
		Query countQuery = getSession().createQuery(generatedCountHql);
		setQueryParams(countQuery, params);
		int totalCount = ((Long) countQuery.uniqueResult()).intValue();
		page.setTotalCount(totalCount);
	}

	private void setQueryParams(Query query, Object[] params) {
		if (null == params) {
			return;
		}
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
