package cn.lanyu.client;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.lanyu.base.dao.IGenericDao;

@Transactional
public interface ClientDao extends IGenericDao<Client> {

	List<Client> getByUserName(String username);
	Client getByCardno(String cardno);
	List<Client> getByPhone(String phoneno);
	List<Client> getByAddress(String param);
	List<Client> getByMobile(String param);
	void changePwd(String username, String pwd);
}
