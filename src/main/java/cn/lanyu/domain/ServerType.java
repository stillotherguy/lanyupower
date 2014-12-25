package cn.lanyu.domain;

public enum ServerType {
	WEB("Web服务器"),DB("数据库服务器"),FILE("文件服务器"),PASSPORT("认证服务器");

	private String displayName;

	private ServerType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}

