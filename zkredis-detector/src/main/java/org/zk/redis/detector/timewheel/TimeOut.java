package org.zk.redis.detector.timewheel;

/**
 * 
 * 
 * 描述：连接管理 创建时间：2011-9-16下午08:42:34
 * 
 * @author yq76034150
 * 
 */
public class TimeOut {
	long time;
	long deadline;
	private int rounds;

	// 连接的用户id
	// 连接的channel

	public TimeOut(long time, long deadline) {
		super();
		this.time = time;
		this.deadline = deadline;
	}

	public TimeOut(long time, long deadline, int rounds) {
		this.time = time;
		this.deadline = deadline;
		this.rounds = rounds;
	}

	public int getRounds() {
		return rounds;
	}

	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void setDeadline(long deadline) {
		this.deadline = deadline;
	}

	public long getDeadline() {
		return deadline;
	}

	public String toString() {
		return "survival: " + (System.currentTimeMillis() - time);
	}
}