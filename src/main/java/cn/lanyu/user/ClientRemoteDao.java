package cn.lanyu.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//TODO @Repository
public class ClientRemoteDao {
	@Autowired
	@Qualifier("sessionFactory")
	private JdbcTemplate jdbcTemplate;
	
	
	@Transactional
	public Client getClientByUsername(String username) {
		final String sql = "select "
							+ "t2.F111_01,t2.F111_41,t2.F111_33,t2.F111_05,t2.F111_08,t2.F111_34,t2.F111_03,"
						 	+ "t2.F111_07,t2.F111_10,t2.F111_35,t2.F111_36,t2.F111_32,t2.F111_37,"
						 	+ "t2.F111_62,t2.F12_15,F12F201412.f12_15,t2.F101_75,t2.F101_21 "
						 	+ "from"
						 + "(select "
						 	+ "t1.F111_01,t1.F111_41,t1.F111_33,t1.F111_05,t1.F111_08,t1.F111_34,t1.F111_03,"
						 	+ "t1.F111_07,t1.F111_10,t1.F111_35,t1.F111_36,t1.F111_32,t1.F111_37,t1.F111_62,"
						 	+ "t1.F12_15,F101.F101_75,F101.F101_21 "
						 	+ "from"
						 		+ "(select "
						 			+ "F111.F111_01,F111.F111_41,F111.F111_33,F111.F111_05,F111.F111_08,F111.F111_34,F111.F111_03,"
						 			+ "F111.F111_07,F111.F111_10,F111.F111_35,F111.F111_36,F111.F111_32,F111.F111_37,F111.F111_62,"
						 			+  "F12F201411.F12_15 "
						 			+ "from f111 "
						 			+ "left outer join F12F201411 on F111.F111_01=F12F201411.F12_03) as t1 "
						 			+ "left outer join f101 on t1.F111_01=F101.F101_01) as t2 "
						 			+ "left outer join F12f201412 on t2.F111_01=F12F201412.f12_03 and t2.f111_01=";
		return jdbcTemplate.query(sql, getResultExtractor());
	}


	private ResultSetExtractor<Client> getResultExtractor() {
		return new ResultSetExtractor<Client>() {

			@Override
			public Client extractData(ResultSet rs) throws SQLException, DataAccessException {
				return new Client();
			}
			
		};
	}


	public Client getClientByCard(String param) {
		// TODO Auto-generated method stub
		return null;
	}


	public Client getClientByAddress(String param) {
		// TODO Auto-generated method stub
		return null;
	}


	public Client getClientByName(String param) {
		// TODO Auto-generated method stub
		return null;
	}


	public Client getClientByMobile(String param) {
		// TODO Auto-generated method stub
		return null;
	}
}
