package org.zk.redis.detector.model;

import java.io.Serializable;

/**
 * 
 * @author captain.guo 
 * 每个redis连接信息实体
 */
public class RedisConfigDto implements Serializable{
	private static final long serialVersionUID = -7603770150482950205L;
	private String host;
	private Integer port;
	private String username;
	private String password;
	/** master->M,slave->S **/
	private String redismode;
	private String status;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}



	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
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

	public String getRedismode() {
		return redismode;
	}

	public void setRedismode(String redismode) {
		this.redismode = redismode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



}
