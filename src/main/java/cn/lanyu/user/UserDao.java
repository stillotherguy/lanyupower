package cn.lanyu.user;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.lanyu.base.dao.IGenericDao;

@Transactional
public interface UserDao extends IGenericDao<User> {
	User getByUsername(String username);
	List<User> getAllEmp();
	boolean isExist(String username);
}
