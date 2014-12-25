package cn.lanyu.user;

public enum Authority {
	ROLE_USER("ROLE_USER"),ROLE_ADMIN("ROLE_ADMIN"),ROLE_EMP("ROLE_EMP"),ROLE_LEADER("ROLE_LEADER");
	
	private String role;
	
	Authority(String role){
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	
}
