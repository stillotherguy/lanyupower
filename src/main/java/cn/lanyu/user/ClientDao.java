package cn.lanyu.user;

import cn.lanyu.base.dao.IGenericDao;

public interface ClientDao extends IGenericDao<Client> {

	Client getByUserName(String username);
	Client getByCardno(String cardno);
	Client getByPhone(String phoneno);
	Client getByCard(String param);
	Client getByAddress(String param);
	Client getByName(String param);
	Client getByMobile(String param);
}
