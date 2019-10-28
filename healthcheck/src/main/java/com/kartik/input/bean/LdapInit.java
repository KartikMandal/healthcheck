/**
 * 
 */
package com.kartik.input.bean;


/**
 * @author kmandal
 *
 */
public class LdapInit {

/*	String url = "ldap://localhost:10389";
	Hashtable env = new Hashtable();
	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	or
	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
	env.put(Context.PROVIDER_URL, url);
	env.put(Context.SECURITY_AUTHENTICATION, "simple");
	env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
	env.put(Context.SECURITY_PRINCIPAL, username + "@" + domainName);
	//example
	// env.put(Context.SECURITY_PRINCIPAL, kmandal"@"corp.yodlee.com);
	env.put(Context.SECURITY_CREDENTIALS, "secret");
	
	<!-- AD Configuration -->
				<prop key="DOMAIN">corp.yodlee.com</prop>
				<prop key="AD_ATTR_NAME_DISTINGUISHED_NAME">distinguishedName</prop>
				<prop key="AD_ATTR_NAME_MEMBER_OF">memberOf</prop>
				<prop key="AD_ATTR_NAME_GROUP_TYPE">groupType</prop>
				<prop key="AD_ATTR_NAME_SAM_ACCOUNT_TYPE">sAMAccountType</prop>
				<prop key="AD_ATTR_NAME_USER_ACCOUNT_CONTROL">userAccountControl</prop>
				<prop key="CONTEXT_FACTORY_CLASS">com.sun.jndi.ldap.LdapCtxFactory</prop>
				<prop key="LDAP_CONNECTION_TIMEOUT">30000</prop>
				<prop key="DC_NAME">DC=corp,DC=yodlee,DC=com</prop>
				<prop key="ADMIN_MEMBER_OF_INFO">CN=Config-Admin-Users</prop>
				<prop key="PROVIDER_URL">ldaps://192.168.227.46:636</prop>
				<prop key="CORP_DOMAIN">corp.yodlee.com</prop>
				<prop key="PROD_DOMAIN">centrify.yodlee.com</prop>
	*/
	
	private String url;
	private String initialContextfactory;
	private String securityAuth;
	private String principal;
	private String securityCredential;
	private int connectionTimeOut;
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the initialContextfactory
	 */
	public String getInitialContextfactory() {
		return initialContextfactory;
	}
	/**
	 * @param initialContextfactory the initialContextfactory to set
	 */
	public void setInitialContextfactory(String initialContextfactory) {
		this.initialContextfactory = initialContextfactory;
	}
	/**
	 * @return the securityAuth
	 */
	public String getSecurityAuth() {
		return securityAuth;
	}
	/**
	 * @param securityAuth the securityAuth to set
	 */
	public void setSecurityAuth(String securityAuth) {
		this.securityAuth = securityAuth;
	}
	/**
	 * @return the principal
	 */
	public String getPrincipal() {
		return principal;
	}
	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	/**
	 * @return the securityCredential
	 */
	public String getSecurityCredential() {
		return securityCredential;
	}
	/**
	 * @param securityCredential the securityCredential to set
	 */
	public void setSecurityCredential(String securityCredential) {
		this.securityCredential = securityCredential;
	}
	/**
	 * @return the connectionTimeOut
	 */
	public int getConnectionTimeOut() {
		return connectionTimeOut;
	}
	/**
	 * @param connectionTimeOut the connectionTimeOut to set
	 */
	public void setConnectionTimeOut(int connectionTimeOut) {
		this.connectionTimeOut = connectionTimeOut;
	}
}
