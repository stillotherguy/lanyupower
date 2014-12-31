package cn.lanyu.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ClientDao clientDao;
	//TODO @Autowired
	private ClientRemoteDao remoteDao;
	//SPRING_SECURITY_CONTEXT存在session里
	@Autowired
	private PasswordEncoder encoder;

	private static final String DEFAULT_PWD = "123456";
	
	
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
				client.setPassword(encoder.encode(DEFAULT_PWD));
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
	
}
