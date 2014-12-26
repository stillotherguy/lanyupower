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
		return queryForObject("from Client c where c.username=?", new Object[]{username});
	}

	@Override
	public Client getByCardno(String cardno) {
		return queryForObject("from Client c where c.no=?", new Object[]{cardno});
	}

	@Override
	public Client getByPhone(String phoneno) {
		return queryForObject("from Client c where c.phone=?", new Object[]{phoneno});
	}

	@Override
	public Client getByCard(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client getByAddress(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client getByName(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client getByMobile(String param) {
		// TODO Auto-generated method stub
		return null;
	}

}
