package cn.lanyu.base.dao;

import java.util.List;
import java.util.Map;

import cn.lanyu.base.page.Page;

public interface IGenericDao<T> {

	void insert(T t);

	void delete(T t);

	void update(T t);

	void merge(T t);

	T get(Long id);

	List<T> queryAll();

	void updateBySql(String sql);

	void updateBySql(String sql, Object[] params);

	List<T> queryPage(Page page);

	List<Map> queryForMap(String originHql, Object[] params);

	int delete(String hql, Object[] params);

	T queryForObject(String hql, Object[] params);

	T queryForTopObject(String hql, Object[] params);

	List<T> queryForList(String hql, Object[] params);

	List<T> queryForList(String hql, Object[] params, int recordNum);

	List<T> queryForList(String hql, Object[] params, Page page);

	int queryForInt(String originHql, Object[] params);

	List<Map> queryForMapPage(String originHql, Object[] params, Page page);

	List<T> queryPageHql(Page page, String hql);
}
