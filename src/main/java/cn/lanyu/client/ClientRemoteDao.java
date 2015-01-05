package cn.lanyu.client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.google.common.collect.Lists;

//TODO @Repository
public class ClientRemoteDao {
	@Autowired
	@Qualifier("sessionFactory")
	private JdbcTemplate jdbcTemplate;
	
	private static final String COMMONSQL = "select "
			+ "t2.F111_01,t2.F111_41,t2.F111_33,t2.F111_05,t2.F111_08,t2.F111_34,t2.F111_03,"
		 	+ "t2.F111_07,t2.F111_10,t2.F111_35,t2.F111_36,t2.F111_32,t2.F111_37,"
		 	+ "t2.F111_62,t2.F12_15 as lastmonth,F12F201412.f12_15 as thismonth,t2.F101_75,t2.F101_21 "
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
		 			+ "left outer join F12f201412 on t2.F111_01=F12F201412.f12_03";
	
	
	public List<Client> getClientByUsername(String username) {
		//33用户名 05状态（已经开户） 08？发卡次数34地址  03??? 07结算户类型 10用户数 35身份证号  36单位 37联系电话 62签约银行F12F201411.F12_15合计量
		//F12F201412.f12_15合计量 F101.F101_75供热面积 F101.F101_21基本热价
		final String sql = COMMONSQL + " where t2.F111_33=?";
		return jdbcTemplate.query(sql, new String[]{username}, getResultExtractor());
	}

	public Client getClientByNo(String param) {
		final String sql = COMMONSQL + " where t2.F111_01=?";
		return jdbcTemplate.query(sql, new String[]{param}, getSingleResultExtractor());
	}

	public List<Client> getClientByAddress(String param) {
		final String sql = COMMONSQL + " where t2.f111_34=?";
		return jdbcTemplate.query(sql, new String[]{param}, getResultExtractor());
	}

	public List<Client> getClientByMobile(String param) {
		final String sql = COMMONSQL + " where t2.f111_62=?";
		return jdbcTemplate.query(sql, new String[]{param}, getResultExtractor());
	}
	
	private ResultSetExtractor<Client> getSingleResultExtractor() {
		return new ResultSetExtractor<Client>() {

			@Override
			public Client extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					Client client = getClient(rs);
					return client;
				}
				return null;
			}

		};
	}
	
	private ResultSetExtractor<List<Client>> getResultExtractor() {
		return new ResultSetExtractor<List<Client>>() {

			@Override
			public List<Client> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Client> clients = Lists.newArrayList();
				while(rs.next()){
					Client client = getClient(rs);
					clients.add(client);
				}
				return clients;
			}

		};
	}
	
	private Client getClient(ResultSet rs) throws SQLException {
		Client client = new Client();
		//client.setAuthority(authority);
		client.setBank(rs.getString("F111_62"));
		
		String cardtotal = rs.getString("F111_08");
		if(!StringUtils.isEmpty(cardtotal)){
			client.setCardtotal(Integer.parseInt(cardtotal));
		}
		client.setChargetype(rs.getString("F111_03"));
		client.setContact(rs.getString("F111_32"));
		client.setHeatarea(rs.getString("F101_75"));
		client.setIdentity(rs.getString("F111_35"));
		client.setLastmonthkj(rs.getString("lastmonth"));
		//client.setLastyearcharge(rs.getString("F111_32"));
		client.setName(rs.getString("F111_33"));
		client.setNo(rs.getString("F111_01"));
		client.setOrganization(rs.getString("F111_36"));
		client.setPhone(rs.getString("F111_37"));
		String price = rs.getString("F101_21");
		if(!StringUtils.isEmpty(price)){
			client.setPrice(Integer.parseInt(price));
		}
		//client.setRevnum(rs.getString("F111_01"));
		client.setState(rs.getString("F111_05"));
		client.setThismonthkj(rs.getString("thismonth"));
		//client.setThisyearcharge(thisyearcharge);
		String totaluser = rs.getString("F111_10");
		if(!StringUtils.isEmpty(totaluser)){
			client.setTotaluser(Integer.parseInt(totaluser));
		}
		client.setUsertype(rs.getString("F111_07"));
		client.setAddress(rs.getString("F111_34"));
		return client;
	}
	
}
