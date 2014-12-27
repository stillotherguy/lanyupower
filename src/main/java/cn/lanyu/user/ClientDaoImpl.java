package cn.lanyu.user;

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
	public Client getByAddress(String address) {
		return queryForObject("from Client c where c.address=?", new Object[]{address});
	}

	@Override
	public Client getByMobile(String mobile) {
		return queryForObject("from Client c where c.phone=?", new Object[]{mobile});
	}

	@Override
	public void changePwd(String username, String pwd) {
		updateBySql("update client set password=? where no=?", new Object[]{pwd, username});
	}

}
