package cn.lanyu.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ClientDao clientDao;
	//TODO @Autowired
	private ClientRemoteDao remoteDao;
	//SPRING_SECURITY_CONTEXT存在session里
	@Autowired
	private PasswordEncoder encoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.equals("admin")) {
			return new User("admin", encoder.encode("admin@123"), Authority.ROLE_ADMIN);
		}
		//先从本地取
		Client client = clientDao.getByCardno(username);
		if(client == null) {
			//从远程取
			client = remoteDao.getClientByNo(username);
			if(client != null) {
				clientDao.insert(client);
				return client;
			}
			User user = null;
			if(client == null) {
				user = userDao.getByUsername(username);
			}
			if(user == null) {
				throw new UsernameNotFoundException(username + " not exist");
			}
			return user;
		}
		return client;
	}
	
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
