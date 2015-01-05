package cn.lanyu.auth;

public enum Authority {
	ROLE_USER("ROLE_USER"),ROLE_ADMIN("ROLE_ADMIN"),ROLE_SERVICE("ROLE_SERVICE"),ROLE_LEADER("ROLE_LEADER"),ROLE_REPAIR("ROLE_REPAIR");
	
	private String role;
	
	Authority(String role){
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	
}
