package cn.lanyu.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ClientDao clientDao;
	//TODO @Autowired
	private ClientRemoteDao remoteDao;
	//SPRING_SECURITY_CONTEXT存在session里
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	public void signup(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.insert(user);
	}

	public Client getByNo(String param) {
		//先从本地取
		Client client = clientDao.getByCardno(param);
		if(client == null) {
			//从远程取
			client = remoteDao.getClientByNo(param);
			if(client != null) {
				clientDao.insert(client);
				return client;
			}
		}
		return null;
	}

	public Client getByAddress(String param) {
		//先从本地取
		Client client = clientDao.getByAddress(param);
		if(client == null) {
			//从远程取
			client = remoteDao.getClientByAddress(param);
			if(client != null) {
				clientDao.insert(client);
				return client;
			}
		}
		return null;
	}

	public Client getByName(String param) {
		//先从本地取
		Client client = clientDao.getByUserName(param);
		if(client == null) {
			//从远程取
			client = remoteDao.getClientByUsername(param);
			if(client != null) {
				clientDao.insert(client);
				return client;
			}
		}
		return null;
	}

	public Client getByMobile(String param) {
		//先从本地取
		Client client = clientDao.getByMobile(param);
		if(client == null) {
			//从远程取
			client = remoteDao.getClientByMobile(param);
			if(client != null) {
				clientDao.insert(client);
				return client;
			}
		}
		return null;
	}
	
	public void changePwd(String username, String pwd){
		clientDao.changePwd(username, pwd);
	}
	
	public List<User> getAllEmp() {
		return userDao.getAllEmp();
	}

}
