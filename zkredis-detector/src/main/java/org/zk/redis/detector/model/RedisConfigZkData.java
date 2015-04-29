package org.zk.redis.detector.model;

import java.io.Serializable;

public class RedisConfigZkData implements Serializable {
	private static final long serialVersionUID = -6446174402446690125L;
	private String id;
	/** redis配置的创建时间 */
	private Long createTime;
	/** redis配置的最后一次访问时间 */
	private Long lastAccessTime;
	private String host;
	private String port;
	private String username;
	private String password;
	/** M->master,S->slave **/
	private String redismode;
	/** 是否可用 */
	private String status;
	/** 当前版本 */
	private int version = 0;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(Long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
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
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public RedisConfigZkData(String id, Long createTime, Long lastAccessTime,
			String host, String port, String username, String password,
			String redismode, String status, int version) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.lastAccessTime = lastAccessTime;
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.redismode = redismode;
		this.status = status;
		this.version = version;
	}
	
	
}
