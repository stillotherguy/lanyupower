package cn.lanyu.user;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.lanyu.base.dao.IGenericDao;
import cn.lanyu.base.page.Page;

@Transactional
public interface UserDao extends IGenericDao<User> {
	User getByUsername(String username);
	List<User> pageQueryAllRepairEmp(Page page);
	List<User> pageQueryAllServiceEmp(Page page);
	boolean isExist(String username);
	void changePwd(String username, String password);
	public List<User> getAllEmp();
}
