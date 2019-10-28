package com.kartik.demo;

import com.kartik.Ihealth.IHealth;
import com.kartik.Ihealth.impl.IHealthImpl;
import com.kartik.input.bean.JmsSourceInit;
import com.kartik.input.bean.LdapInit;
import com.kartik.input.bean.MongoInit;
import com.kartik.input.bean.ResponseStatus;
import com.kartik.input.bean.ServiecApiInit;

public class Demo {

	public static void main(String[] args) {
		IHealth iHealth=new IHealthImpl();
		System.out.println("===============Start Ldap====================");
		LdapInit ldapInit=new LdapInit();
		ldapInit.setInitialContextfactory("com.sun.jndi.ldap.LdapCtxFactory");
		ldapInit.setPrincipal("sc9-adc-pub.corp.yodlee.com");
		//ldapInit.setPrincipal("corp.yodlee.com");
		ldapInit.setSecurityAuth("configmanagertool");
		ldapInit.setSecurityCredential("Yodlee@123");
		ldapInit.setUrl("ldaps://192.168.227.46:636");
		//ldapInit.setUrl("ldaps://192.168.228.45:636");
		ResponseStatus dd=iHealth.getLdapHelath(ldapInit);
		System.out.println(dd.getServerDecription()+" "+dd.getServerStatus());
		System.out.println("===============End Ldap====================");
		System.out.println();
		/*System.out.println("===============Start Serviec Api====================");
		ServiecApiInit ser=new ServiecApiInit();
		ser.setServiceUrl("http://dummy.restapiexample.com/");
		ser.setTimeOut(3000);
		dd=iHealth.getServiceApiHelath(ser);
		System.out.println(dd.getServerDecription()+" "+dd.getServerStatus());
		System.out.println("===============End Serviec Api====================");
		System.out.println();
		System.out.println("===============Start Mongo Api====================");
		MongoInit mongoInit=new MongoInit();
		mongoInit.setAuthMechanisum("SCRAM-SHA-1");
		mongoInit.setDbName("StreamPermissionService");
		mongoInit.setHost("192.168.112.169");
		mongoInit.setPassWord("app@Strm");
		mongoInit.setPort(27017);
		mongoInit.setUserName("app");
		dd=iHealth.getMongoHelath(mongoInit);
		System.out.println(dd.getServerDecription()+" "+dd.getServerStatus());
		System.out.println("===============End Mongo Api====================");
		System.out.println();
		System.out.println("===============Start JMS Api====================");
		JmsSourceInit jmsSourceInit=new JmsSourceInit();
		jmsSourceInit.setChannel("SYSTEM.DEF.SVRCONN");
		jmsSourceInit.setHost("192.168.210.116");
		jmsSourceInit.setListenerPort(1947);
		jmsSourceInit.setQueueManager("MFA_APP_REQUEST");
		jmsSourceInit.setQueueName("NeoloreQueueManager");
		dd=iHealth.getJmsHelath(jmsSourceInit);
		System.out.println(dd.getServerDecription()+" "+dd.getServerStatus());
		System.out.println("===============End JMS Api====================");*/
	}

}
