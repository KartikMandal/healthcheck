/**
 * 
 */
package com.kartik.input.bean;

/**
 * @author kmandal
 *
 */
public class DataSourceInit {
	//oracle.jdbc.driver.OracleDriver
	private String diverName;
	//jdbc:oracle:thin:@localhost:1521:xe
	private String connectionUrl;
	//system
	private String userId;
	//oracle
	private String password;
	//select * from emp
	private String query;
	
	/**
	 * @return the diverName
	 */
	public String getDiverName() {
		return diverName;
	}
	/**
	 * @param diverName the diverName to set
	 */
	public void setDiverName(String diverName) {
		if(diverName!=null)
		this.diverName = diverName;
		else
			this.diverName = "oracle.jdbc.driver.OracleDriver";
	}
	/**
	 * @return the connectionUrl
	 */
	public String getConnectionUrl() {
		return connectionUrl;
	}
	/**
	 * @param connectionUrl the connectionUrl to set
	 */
	public void setConnectionUrl(String connectionUrl) {
		if(connectionUrl!=null)
		this.connectionUrl = connectionUrl;
		else
			this.connectionUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		if(userId!=null){
		this.userId = userId;
		}else{
			this.userId = "system";
		}
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		if(password !=null)
		this.password = password;
		else
			this.password = "oracle";
	}
	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}
	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

}
