package com.kartik.input.bean;
/**
 * 
 * @author kmandal
 *
 */
public class ResponseStatus {

	private String serviceName;
	
	private String serverDecription;
	
	private String serverStatus;

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the serverDecription
	 */
	public String getServerDecription() {
		return serverDecription;
	}

	/**
	 * @param serverDecription the serverDecription to set
	 */
	public void setServerDecription(String serverDecription) {
		this.serverDecription = serverDecription;
	}

	/**
	 * @return the serverStatus
	 */
	public String getServerStatus() {
		return serverStatus;
	}

	/**
	 * @param serverStatus the serverStatus to set
	 */
	public void setServerStatus(String serverStatus) {
		this.serverStatus = serverStatus;
	}
	
}
