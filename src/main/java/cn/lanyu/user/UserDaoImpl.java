package cn.lanyu.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lanyu.auth.Authority;
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
		return queryForList("from User u where u.authority in (?,?)", new Object[]{Authority.ROLE_REPAIR, Authority.ROLE_SERVICE});
	}

	@Override
	public boolean isExist(String username) {
		return queryForInt("from User u where u.username = ?", new Object[] {username}) > 0;
	}

	@Override
	public void changePwd(String username, String password) {
		updateBySql("update user set password=? where username=?", new Object[]{password, username});
	}

}
