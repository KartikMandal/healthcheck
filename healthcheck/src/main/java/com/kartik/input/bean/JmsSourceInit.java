/**
 * 
 */
package com.kartik.input.bean;

/**
 * @author kmandal
 *
 */
public class JmsSourceInit {
	/*private static final String QUEUE_NAME = "ALERTS_FAST_QUEUE";
    private static final String MQ_QUEUE_MANEGER = "NextGenMQ";
    private static final int MQ_LISTENER_PORT = 11485;
    private static final String MQ_HOST = "192.168.56.112";
    
    private static String channel = "SYSTEM.DEF.SVRCONN";*/
	
	private String queueName;
    private String queueManager;
    private int listenerPort;
    private String host;
    private String channel;
	/**
	 * @return the queueName
	 */
	public String getQueueName() {
		return queueName;
	}
	/**
	 * @param queueName the queueName to set
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	/**
	 * @return the queueManager
	 */
	public String getQueueManager() {
		return queueManager;
	}
	/**
	 * @param queueManager the queueManager to set
	 */
	public void setQueueManager(String queueManager) {
		this.queueManager = queueManager;
	}
	/**
	 * @return the listenerPort
	 */
	public int getListenerPort() {
		return listenerPort;
	}
	/**
	 * @param listenerPort the listenerPort to set
	 */
	public void setListenerPort(int listenerPort) {
		this.listenerPort = listenerPort;
	}
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

}
