/**
 * 
 */
package com.kartik.input.bean;

/**
 * @author kmandal
 *
 */
public class KafkaInit {

	private String kafkaServers;
	private String acks;
	private String keySerializer;
	private String valueSerializer;
	private int timeOut;
	private boolean blockBuffer;
	private int retries;
	private int autoCommit;
	private int lingerMs;
	private String topic;
	private String key;
	private String message;
	/**
	 * @return the kafkaServers
	 */
	public String getKafkaServers() {
		return kafkaServers;
	}
	/**
	 * @param kafkaServers the kafkaServers to set
	 */
	public void setKafkaServers(String kafkaServers) {
		this.kafkaServers = kafkaServers;
	}
	/**
	 * @return the acks
	 */
	public String getAcks() {
		return acks;
	}
	/**
	 * @param acks the acks to set
	 */
	public void setAcks(String acks) {
		this.acks = acks;
	}
	/**
	 * @return the keySerializer
	 */
	public String getKeySerializer() {
		return keySerializer;
	}
	/**
	 * @param keySerializer the keySerializer to set
	 */
	public void setKeySerializer(String keySerializer) {
		this.keySerializer = keySerializer;
	}
	/**
	 * @return the valueSerializer
	 */
	public String getValueSerializer() {
		return valueSerializer;
	}
	/**
	 * @param valueSerializer the valueSerializer to set
	 */
	public void setValueSerializer(String valueSerializer) {
		this.valueSerializer = valueSerializer;
	}
	/**
	 * @return the timeOut
	 */
	public int getTimeOut() {
		return timeOut;
	}
	/**
	 * @param timeOut the timeOut to set
	 */
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	/**
	 * @return the blockBuffer
	 */
	public boolean isBlockBuffer() {
		return blockBuffer;
	}
	/**
	 * @param blockBuffer the blockBuffer to set
	 */
	public void setBlockBuffer(boolean blockBuffer) {
		this.blockBuffer = blockBuffer;
	}
	/**
	 * @return the retries
	 */
	public int getRetries() {
		return retries;
	}
	/**
	 * @param retries the retries to set
	 */
	public void setRetries(int retries) {
		this.retries = retries;
	}
	/**
	 * @return the autoCommit
	 */
	public int getAutoCommit() {
		return autoCommit;
	}
	/**
	 * @param autoCommit the autoCommit to set
	 */
	public void setAutoCommit(int autoCommit) {
		this.autoCommit = autoCommit;
	}
	/**
	 * @return the lingerMs
	 */
	public int getLingerMs() {
		return lingerMs;
	}
	/**
	 * @param lingerMs the lingerMs to set
	 */
	public void setLingerMs(int lingerMs) {
		this.lingerMs = lingerMs;
	}
	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}
	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
