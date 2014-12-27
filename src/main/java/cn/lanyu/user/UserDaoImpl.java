package cn.lanyu.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lanyu.base.dao.GenericDao;


@Repository
public class UserDaoImpl extends GenericDao<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public User getByUsername(String username) {
		return queryForObject("from User u where u.username=?", new Object[]{username});
	}

	@Override
	public List<User> getAllEmp() {
		return queryForList("from User u where u.authority=?", new Object[]{Authority.ROLE_EMP});
	}

}
