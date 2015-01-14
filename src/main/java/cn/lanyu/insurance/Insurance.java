package cn.lanyu.insurance;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import cn.lanyu.client.Client;
import cn.lanyu.user.User;

@Entity
@Table(name = "insurance")
public class Insurance {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private Type itype;
	@ManyToOne(targetEntity = User.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private User user;
	@JsonBackReference  
	@ManyToOne(targetEntity = Client.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_ID",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Client client;
	private String contact;
	private String phone;
	private String comment;
	private boolean finished;
	private boolean feedback;
	private Date startDate;
	private Date endDate;
	private String reason;
	private String images;
	private String empComment;
	private Assessment assessment;
	private String complaint;
	//TODO:
	//private int complaintCount;
	
	public Insurance(){}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Type getItype() {
		return itype;
	}

	public void setItype(Type itype) {
		this.itype = itype;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean isFeedback() {
		return feedback;
	}

	public void setFeedback(boolean feedback) {
		this.feedback = feedback;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	public String getEmpComment() {
		return empComment;
	}

	public void setEmpComment(String empComment) {
		this.empComment = empComment;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}
	
	public static enum Assessment {
		VERY_SATISFIED("非常满意"),SATISFIED("满意"),ORDINARY("一般"),UNSATISFIED("不满意"),COMPLAINT("投诉");
		
		private String desc;
		
		Assessment(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
		
	}

	public static enum Type {
		LEAK("漏水"),HEATMETER("热表故障"),COLD("无法升温"),OTHER("其他");
		
		private String desc;
		
		Type(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
		
	}
	
}
