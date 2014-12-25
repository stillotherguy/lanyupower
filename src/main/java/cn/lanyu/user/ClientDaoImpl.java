package cn.lanyu.user;

import org.springframework.stereotype.Repository;

import cn.lanyu.base.dao.GenericDao;


@Repository
public class ClientDaoImpl extends GenericDao<Client> implements ClientDao {

	public ClientDaoImpl() {
		super(Client.class);
	}

	@Override
	public Client getByUserName(String username) {
		// TODO 
		return null;
	}

}
