package cn.lanyu.client;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.lanyu.base.dao.GenericDao;


@Repository
@Transactional
public class ClientDaoImpl extends GenericDao<Client> implements ClientDao {

	public ClientDaoImpl() {
		super(Client.class);
	}

	@Override
	public List<Client> getByUserName(String username) {
		return queryForList("from Client c where c.name=?", new Object[]{username});
	}

	@Override
	public Client getByCardno(String cardno) {
		return queryForObject("from Client c where c.no=?", new Object[]{cardno});
	}

	@Override
	public List<Client> getByPhone(String phoneno) {
		return queryForList("from Client c where c.phone=?", new Object[]{phoneno});
	}

	@Override
	public List<Client> getByAddress(String address) {
		return queryForList("from Client c where c.address=?", new Object[]{address});
	}

	@Override
	public List<Client> getByMobile(String mobile) {
		return queryForList("from Client c where c.phone=?", new Object[]{mobile});
	}

	@Override
	public void changePwd(String username, String pwd) {
		updateBySql("update client set password=?,firstlogin=0 where no=?", new Object[]{pwd, username});
	}

	@Override
	public Client getById(long id) {
		return queryForObject("from Client c where c.id=?", new Object[]{id});
	}

}
