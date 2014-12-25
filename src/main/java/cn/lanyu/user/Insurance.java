package cn.lanyu.user;

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

@Entity
@Table(name = "insurance")
public class Insurance {
	@Id
	@GeneratedValue
	private long id;
	private Type itype;
	@ManyToOne(targetEntity = User.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private User user;
	@ManyToOne(targetEntity = Client.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "CLIENT_ID",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Client client;
	private String contact;
	private String phone;
	private String comment;
	private boolean done;
	private boolean feedback;
	
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
	
	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean isFeedback() {
		return feedback;
	}

	public void setFeedback(boolean feedback) {
		this.feedback = feedback;
	}



	private static enum Type {
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
