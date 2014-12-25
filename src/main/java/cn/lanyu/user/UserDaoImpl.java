package cn.lanyu.user;

import org.springframework.stereotype.Repository;

import cn.lanyu.base.dao.GenericDao;


@Repository
public class UserDaoImpl extends GenericDao<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public User getByUsername(String username) {
		// TODO 
		return null;
	}

}
