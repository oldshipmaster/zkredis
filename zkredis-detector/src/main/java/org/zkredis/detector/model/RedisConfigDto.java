package org.zkredis.detector.model;

/**
 * 
 * @author captain.guo 每个redis连接实体
 */
public class RedisConfigDto {
	private String host;
	private String port;
	private String username;
	private String password;
	/** master->M,slave->S **/
	private String MasterOrSlave;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMasterOrSlave() {
		return MasterOrSlave;
	}

	public void setMasterOrSlave(String masterOrSlave) {
		MasterOrSlave = masterOrSlave;
	}

}
