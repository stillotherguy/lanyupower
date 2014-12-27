package cn.lanyu.user;

import java.util.List;

import cn.lanyu.base.dao.IGenericDao;

public interface UserDao extends IGenericDao<User> {
	User getByUsername(String username);
	List<User> getAllEmp();
}
