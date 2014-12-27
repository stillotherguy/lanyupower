package cn.lanyu.user;

import org.springframework.transaction.annotation.Transactional;

import cn.lanyu.base.dao.IGenericDao;

@Transactional
public interface ClientDao extends IGenericDao<Client> {

	Client getByUserName(String username);
	Client getByCardno(String cardno);
	Client getByPhone(String phoneno);
	Client getByAddress(String param);
	Client getByMobile(String param);
	void changePwd(String username, String pwd);
}
