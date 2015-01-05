package cn.lanyu.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lanyu.client.Client;
import cn.lanyu.client.ClientDao;
import cn.lanyu.client.ClientRemoteDao;

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
				//client.setPassword(encoder.encode(DEFAULT_PWD));
				clientDao.insert(client);
				return client;
			}
		}
		return client;
	}
	
	public Client getById(long param) {
		//先从本地取
		Client client = clientDao.getById(param);
		return client;
	}

	public List<Client> getByAddress(String param) {
		//先从本地取
		List<Client> clients = clientDao.getByAddress(param);
		if(clients == null || clients.isEmpty()) {
			//从远程取
			clients = remoteDao.getClientByAddress(param);
			if(clients != null && !clients.isEmpty()) {
				for(Client c:clients){
					//c.setPassword(encoder.encode(DEFAULT_PWD));
					clientDao.insert(c);
				}
				return clients;
			}
		}
		return clients;
	}

	public List<Client> getByName(String param) {
		//先从本地取
		List<Client> clients = clientDao.getByUserName(param);
		if(clients == null || clients.isEmpty()) {
			//从远程取
			clients = remoteDao.getClientByUsername(param);
			if(clients != null && !clients.isEmpty()) {
				for(Client c:clients){
					//c.setPassword(encoder.encode(DEFAULT_PWD));
					clientDao.insert(c);
				}
				return clients;
			}
		}
		return clients;
	}

	public List<Client> getByMobile(String param) {
		//先从本地取
		List<Client> clients = clientDao.getByMobile(param);
		if(clients == null || clients.isEmpty()) {
			//从远程取
			clients = remoteDao.getClientByMobile(param);
			if(clients != null && !clients.isEmpty()) {
				for(Client c:clients){
					//c.setPassword(encoder.encode(DEFAULT_PWD));
					clientDao.insert(c);
				}
				return clients;
			}
		}
		return clients;
	}
	
	public void changePwd(String username, String pwd){
		clientDao.changePwd(username, pwd);
	}
	
	public List<User> getAllEmp() {
		return userDao.getAllEmp();
	}

	public boolean isExist(String username) {
		return userDao.isExist(username);
	}

}
