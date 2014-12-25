package cn.lanyu.user;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.Lists;
// TODO
@Entity
@Table(name = "client")
@JsonInclude(Include.NON_NULL)
public class Client implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "F111_01")
	private String no;
	@Column(name = "F111_33")
	private String name;
	/**
	 * 是否开户
	 */
	@Column(name = "F111_05")
	private int state;
	/**
	 * 发卡次数
	 */
	@Column(name = "F111_08")
	private int cardtotal;
	@Column(name = "F111_34")
	private String address;
	/**
	 * 计费类型
	 */
	@Column(name = "F111_03")
	private String chargetype;
	/**
	 * 上年度缴费
	 */
	private String lastyear;
	/**
	 * 本年度缴费
	 */
	private String thisyear;
	/**
	 * 结算户类型
	 */
	@Column(name = "F111_07")
	private String usertype;
	/**
	 * 用户总数
	 */
	@Column(name = "F111_10")
	private int totaluser;
	/**
	 * 关联数量
	 */
	private int revnum;
	/**
	 * 身份证号
	 */
	@Column(name = "F111_35")
	private String identity;
	/**
	 * 单位
	 */
	@Column(name = "F111_36")
	private String organization;
	/**
	 * 联系人
	 */
	@Column(name = "F111_32")
	private String contact;
	/**
	 * 联系电话
	 */
	@Column(name = "F111_37")
	private String phone;
	/**
	 * 签约银行
	 */
	@Column(name = "F111_62")
	private String bank;
	/**
	 * 上月热表数
	 */
	private String lastmonthkj;
	/**
	 * 本月热表数
	 */
	private String thismonthkj;
	/**
	 * 供热面积
	 */
	private String heatarea;
	private String password;
	@Enumerated(EnumType.STRING)
	private Authority authority;
	/**
	 * 基本热价
	 */
	private double price;
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "client", fetch = FetchType.LAZY)
	private List<Insurance> insurances;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNo() {
		return no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public int getCardtotal() {
		return cardtotal;
	}
	
	public void setCardtotal(int cardtotal) {
		this.cardtotal = cardtotal;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getChargetype() {
		return chargetype;
	}
	
	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}
	
	public String getLastyear() {
		return lastyear;
	}
	
	public void setLastyear(String lastyear) {
		this.lastyear = lastyear;
	}
	
	public String getThisyear() {
		return thisyear;
	}
	
	public void setThisyear(String thisyear) {
		this.thisyear = thisyear;
	}
	
	public String getUsertype() {
		return usertype;
	}
	
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	public int getTotaluser() {
		return totaluser;
	}
	
	public void setTotaluser(int totaluser) {
		this.totaluser = totaluser;
	}
	
	public int getRevnum() {
		return revnum;
	}
	
	public void setRevnum(int revnum) {
		this.revnum = revnum;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public String getOrganization() {
		return organization;
	}
	
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	public String getContact() {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getBank() {
		return bank;
	}
	
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public String getLastmonthkj() {
		return lastmonthkj;
	}
	
	public void setLastmonthkj(String lastmonthkj) {
		this.lastmonthkj = lastmonthkj;
	}
	
	public String getThismonthkj() {
		return thismonthkj;
	}
	
	public void setThismonthkj(String thismonthkj) {
		this.thismonthkj = thismonthkj;
	}
	
	public String getHeatarea() {
		return heatarea;
	}
	
	public void setHeatarea(String heatarea) {
		this.heatarea = heatarea;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Authority getAuthority() {
		return authority;
	}
	
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	
	public List<Insurance> getInsurances() {
		return insurances;
	}
	
	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> list = Lists.newArrayList(new SimpleGrantedAuthority(Authority.ROLE_USER.toString()));
		return list;
	}

	@Override
	public String getUsername() {
		return no;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
