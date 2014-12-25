package cn.lanyu.user;

import cn.lanyu.base.dao.IGenericDao;

public interface UserDao extends IGenericDao<User> {

	User getByUsername(String username);
}
