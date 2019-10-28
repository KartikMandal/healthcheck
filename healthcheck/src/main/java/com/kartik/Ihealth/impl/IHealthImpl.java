package com.kartik.Ihealth.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ExecutionException;

import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.bson.Document;

import com.ibm.mq.jms.JMSC;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.kartik.Ihealth.IHealth;
import com.kartik.input.bean.ActiveMqInit;
import com.kartik.input.bean.DataSourceInit;
import com.kartik.input.bean.EmailInit;
import com.kartik.input.bean.JbossServerStartUpInit;
import com.kartik.input.bean.JmsSourceInit;
import com.kartik.input.bean.JvmHealthCheck;
import com.kartik.input.bean.KafkaInit;
import com.kartik.input.bean.LdapInit;
import com.kartik.input.bean.MongoInit;
import com.kartik.input.bean.ResponseStatus;
import com.kartik.input.bean.ServiecApiInit;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class IHealthImpl implements IHealth {

	public ResponseStatus getMongoHelath(MongoInit mongoInit) {
		ResponseStatus responseStatus=new ResponseStatus();
		MongoClient mongoClient = null;
			try {
			String user = null;
			String password = null;
			try {
				user = URLEncoder.encode(mongoInit.getUserName(), "UTF-8");
				password = URLEncoder.encode(mongoInit.getPassWord(), "UTF-8");
			} catch (UnsupportedEncodingException ex) {
				throw new RuntimeException("Failed to encode user-name and password using UTF-8");
			}
			
			//Added the default auth mechanism 
			if (mongoInit.getAuthMechanisum()== null || mongoInit.getAuthMechanisum().isEmpty()) {
					// Added MongoDB-CR as default mechanism if we have both
					// mongoRef.authMechanism
					// and jvm parameter as null or empty.
					mongoInit.setAuthMechanisum("MONGODB-CR");
			}
			
			
			String clientUrl = "mongodb://" + user + ":" + password + "@" + mongoInit.getHost()+":"+mongoInit.getPort() + "/"
						+ mongoInit.getDbName();
			if (mongoInit.getAuthMechanisum() != null) {
				clientUrl =clientUrl+ "?authMechanism=" + mongoInit.getAuthMechanisum();
			} 
			
			MongoClientURI uri = new MongoClientURI(clientUrl);
			// Connecting to the mongodb server using the given client uri.
			mongoClient = new MongoClient(uri);
			
			MongoDatabase db= mongoClient.getDatabase(mongoInit.getDbName());
			Document serverStatus = db.runCommand(new Document("serverStatus", 1));
			System.out.println(serverStatus);
			if (serverStatus != null){
				responseStatus.setServiceName("getMongoHelath");
				responseStatus.setServerDecription("Ip and port and other information");
				responseStatus.setServerStatus("UP");			
			}
			mongoClient.close();
		} catch (Exception e) {
			responseStatus.setServiceName("getMongoHelath");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("DOWN");			
			mongoClient.close();
		}
			return responseStatus;
	}

	public ResponseStatus getDataSourceHelath(DataSourceInit datasorceInit) {
		ResponseStatus responseStatus=new ResponseStatus();
		 
		//if(oracle)
		try{  
			//step1 load the driver class  
			Class.forName(datasorceInit.getDiverName());  
			  
			//step2 create  the connection object  
			Connection con = DriverManager.getConnection(
					datasorceInit.getConnectionUrl(),
					datasorceInit.getUserId(), datasorceInit.getPassword()); 
			  
			//step3 create the statement object  
			Statement stmt=con.createStatement();  
			  
			//step4 execute query  
			ResultSet rs=stmt.executeQuery(datasorceInit.getQuery());  
			while(rs.next())  
			//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			  
			//step5 close the connection object  
			con.close();  
			responseStatus.setServiceName("DataSource");
			 responseStatus.setServerDecription("Ip and port and other information");
			 responseStatus.setServerStatus("UP");
			}catch(Exception e){ 
				responseStatus.setServiceName("DataSource");
				responseStatus.setServerDecription("Ip and port and other information");
				responseStatus.setServerStatus("DOWN");				
				System.out.println(e);  
			  
			}    
		 //if mysql
		 try{  
			 Class.forName("com.mysql.jdbc.Driver");  
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","root");  
			 //here sonoo is database name, root is username and password  
			 Statement stmt=con.createStatement();  
			 ResultSet rs=stmt.executeQuery("select * from DUAL");  
			 while(rs.next())  
				 con.close();
			 responseStatus.setServiceName("DataSource");
			 responseStatus.setServerDecription("Ip and port and other information");
			 responseStatus.setServerStatus("UP");
			 }catch(Exception e){ 
				 responseStatus.setServiceName("DataSource");
				 responseStatus.setServerDecription("Ip and port and other information");
				 responseStatus.setServerStatus("DOWN");
				 System.out.println(e);
			 }  
		 
		return responseStatus;
	}

	public ResponseStatus getJmsHelath(JmsSourceInit jmssorceInit) {
		ResponseStatus responseStatus=new ResponseStatus();
		 QueueConnection queueConnection;
	     QueueSession queueSession;
		try{
	    	MQQueueConnectionFactory mqQcf = new MQQueueConnectionFactory();
	        mqQcf.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
	        mqQcf.setUseConnectionPooling(false);
	        mqQcf.setQueueManager(jmssorceInit.getQueueManager());
	        mqQcf.setHostName(jmssorceInit.getHost());
	        mqQcf.setPort(jmssorceInit.getListenerPort());
	        mqQcf.setChannel(jmssorceInit.getChannel());
		    queueConnection = mqQcf.createQueueConnection("mqm","mqm");
	        queueSession = queueConnection.createQueueSession(false,
	                        Session.AUTO_ACKNOWLEDGE);
	        queueSession.getMessageListener();
	        responseStatus.setServiceName("getJmsHelath");
			 responseStatus.setServerDecription("Ip and port and other information");
			 responseStatus.setServerStatus("UP");
    	}catch(Exception ex){
    		 responseStatus.setServiceName("getJmsHelath");
			 responseStatus.setServerDecription("Ip and port and other information");
			 responseStatus.setServerStatus("DOWN");
			 System.out.println("Failed connecting to QueueManager.");
		}
    	
		return responseStatus;
	}

	public ResponseStatus getServiceApiHelath(ServiecApiInit serviecApiInit) {
		ResponseStatus responseStatus=new ResponseStatus();
		URL siteURL;
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
			}};
			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					// TODO Auto-generated method stub
					return true;
				}

			};
			
			
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			siteURL = new URL(serviecApiInit.getServiceUrl());
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(serviecApiInit.getTimeOut());
			connection.connect();
			if (connection.getResponseCode() == 200){
			responseStatus.setServiceName("getServiceApiHelath");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("UP");
			}
			else{
			responseStatus.setServiceName("getServiceApiHelath");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("DOWN");
			}
		} catch (Exception e) {
			responseStatus.setServiceName("getServiceApiHelath");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("UNKNOWN");
		}
		return responseStatus;
	}

	public ResponseStatus getLdapHelath(LdapInit ldapInit) {
		//Path to your keystore where you registred the SSL certficate
		//String keystorePath = "D:\\Software\\java\\jdk1.8.0_05\\jre\\lib\\security\\cacerts";
		//System.setProperty("javax.net.ssl.keyStore", keystorePath);
		 
		// Password of your java keystore. Default value is : changeit
		//System.setProperty("javax.net.ssl.keyStorePassword", "Yodlee@123");
		
		ResponseStatus responseStatus=new ResponseStatus();
		//Hashtable<Object, Object> env = new Hashtable<Object, Object>();
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, ldapInit.getInitialContextfactory());
		env.put(Context.PROVIDER_URL, ldapInit.getUrl());
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, ldapInit.getSecurityAuth()+"@"+ldapInit.getPrincipal());
		//env.put(Context.REFERRAL, "follow");
		env.put(Context.SECURITY_CREDENTIALS, ldapInit.getSecurityCredential());
		
		try {
		    DirContext ctx = new InitialDirContext(env);
		    System.out.println("connected");
		    System.out.println(ctx.getEnvironment());
		    // do something useful with the context...
		    ctx.close();
		    responseStatus.setServiceName("getLdapHelath");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("UP");
		 
		} catch (AuthenticationNotSupportedException ex) {
			responseStatus.setServiceName("getLdapHelath");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("DOWN");
		    System.out.println("The authentication is not supported by the server");
		} catch (AuthenticationException ex) {
			responseStatus.setServiceName("getLdapHelath");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("DOWN");
		    System.out.println("incorrect password or username");
		} catch (NamingException ex) {
			responseStatus.setServiceName("getLdapHelath");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("DOWN");
		    System.out.println("error when trying to create the context");
		}catch(Exception e){
			responseStatus.setServiceName("getLdapHelath");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("UNKNOWN");
		}
		return responseStatus;
	}

	public ResponseStatus getActiveMqHelath(ActiveMqInit activeMqInit) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseStatus getJbossServerStartUp(
			JbossServerStartUpInit jbossServerStartUpInit) {
		String IN_PROGRESS_FILE_NAME = ".isdeploying";
		String FAILED_FILE_NAME = ".failed";
		String SUCCESS_FILE_NAME = ".deployed";
		ResponseStatus responseStatus=new ResponseStatus();
		String path=jbossServerStartUpInit.getPath();
		String fileLocation = System.getProperty("jboss.server.base.dir") +"/deployments/";
		 String warFileName = extract(path)+".war";
       File inprogress = new File(fileLocation+warFileName+IN_PROGRESS_FILE_NAME);
       File failed = new File(fileLocation+warFileName+FAILED_FILE_NAME);
       File sucess = new File(fileLocation+warFileName+SUCCESS_FILE_NAME);
       
		if (inprogress.exists()) {
			System.out.println("in progress file exists");
			responseStatus.setServiceName("getEmailServerHealth");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("IN PROGRESS");
		} else if (failed.exists()) {
			System.out.println("failed file exists");
			responseStatus.setServiceName("getEmailServerHealth");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("FAILED");

		} else if (sucess.exists()) {
			responseStatus.setServiceName("getEmailServerHealth");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("UP");
		}
		
		
		return null;
	}
		
	static String extract(String s) {
		return s.contains("?")?s.split("/")[1].substring(0,s.indexOf("?")-1):s.split("/")[1];
	}

	
	
	
	
	
	@Override
	public ResponseStatus getEmailServerHealth(EmailInit emailInit) {
		ResponseStatus responseStatus=new ResponseStatus();
		try{
			Message message = new MimeMessage(emailInit.getMySession());
	        message.setFrom(new InternetAddress(emailInit.getFrom()));
	        Address toAddress = new InternetAddress(emailInit.getTo());
	        message.addRecipient(Message.RecipientType.TO, toAddress);
	        message.setSubject(emailInit.getSubject());
	        message.setContent(emailInit.getBody(), "text/plain");
	        Transport.send(message);
	        responseStatus.setServiceName("getEmailServerHealth");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("UP");
		}catch(Exception e){
			responseStatus.setServiceName("getEmailServerHealth");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("DOWN");
		}
		return responseStatus;
	}

	@Override
	public ResponseStatus getKafkaServerHealth(KafkaInit kafkaInit) {
		ResponseStatus responseStatus=new ResponseStatus();
		try{
			/**
			 * ## START : Kafka configuration # In case of cluster have comma
			 * separated list # This again should be unique per environment #
			 * Reason being same cobrand can be tested across environments which
			 * might result in in-correct errors if this is shared.
			 * kafka.bootstrap.servers=blr-enggdev-20.corp.yodlee.com:9092 ##
			 * END : Kafka configurations
			 * 
			 * ## START : Site Processor configurations # application_id is
			 * unique across environment/stack format ->
			 * <environment>_site_aggregator, environment is pilot/dev,
			 * l1qa,l2qa,mrqa,psqa,stage,prod
			 * site_aggregator.application_id=DEV_SPS_Site
			 * 
			 * # site-events - this topic needs to be created in respective
			 * Kafka server and is unique across environment/stack format ->
			 * <environment>-site-event, environment is pilot/dev,
			 * l1qa,l2qa,mrqa,psqa,stage,prod
			 * kafka.topic.site-events=perftest-site-event
			 * 
			 * # site-stats - utilized by consumer streams to populate stats
			 * internally kafka.topic.site-stats=perftest-site-stats ## END :
			 * Site Processor configurations
			 * 
			 * ## START : Sum Info Processor configurations # application_id is
			 * unique across environment/stack format ->
			 * <environment>_sum_info_aggregator, environment is pilot/dev,
			 * l1qa,l2qa,mrqa,psqa,stage,prod
			 * sum_info_aggregator.application_id=DEV_SPS_SumInfo
			 * 
			 * # site-events - this topic needs to be created in respective
			 * Kafka server and is unique across environment/stack format ->
			 * <environment>-sum-info-event, environment is pilot/dev,
			 * l1qa,l2qa,mrqa,psqa,stage,prod
			 * kafka.topic.sum-info-events=perftest-sum-info-event
			 * 
			 * # sum-info-stats - utilized by consumer streams to populate stats
			 * internally kafka.topic.sum-info-stats=perftest-sum-info-stats ##
			 * END : Sum Info Processor configurations
			 * 
			 * ## START : Cob login Processor configurations # application_id is
			 * unique across environment/stack format ->
			 * <environment>_cob_login_aggregator, environment is pilot/dev,
			 * l1qa,l2qa,mrqa,psqa,stage,prod
			 * cob_login_aggregator.application_id=perftest_cob_login_aggregator
			 * 
			 * # cob-login-event - this topic needs to be created in respective
			 * Kafka server and is unique across environment/stack format ->
			 * <environment>-cob-login-event, environment is pilot/dev,
			 * l1qa,l2qa,mrqa,psqa,stage,prod
			 * kafka.topic.cob-login-events=perftest-cob-login-event
			 * 
			 * # cob-login-stats - utilized by consumer streams to populate
			 * stats internally
			 * kafka.topic.cob-login-stats=perftest-cob-login-stats ## END : Cob
			 * login Processor configurations
			 * 
			 * 
			 * # Kafka - START kafka.acks=all
			 * kafka.key.serializer=org.apache.kafka
			 * .common.serialization.StringSerializer
			 * kafka.value.serializer=org.
			 * apache.kafka.common.serialization.StringSerializer
			 * kafka.block.on.buffer.full=TRUE kafka.retries=3
			 * kafka.auto.commit.interval.ms=50 kafka.timeout.ms=3000 #how much
			 * to wait for other records before flushing the batch for network
			 * sending #linger.ms sets the maximum time to buffer data in
			 * asynchronous mode. For example, a #setting of 100 batches 100ms
			 * of messages to send at once. This improves throughput, #but the
			 * buffering adds message delivery latency. kafka.linger.ms=0
			 * #batch.size measures batch size in total bytes instead of the
			 * number of messages. #It controls how many bytes of data to
			 * collect before sending messages to the Kafka broker. #Set this as
			 * high as possible, without exceeding available memory. The default
			 * value is 16384. kafka.batch.size=16384 # we can control how much
			 * time it will block before sending throws a
			 * BufferExhaustedException kafka.max.block.ms=10
			 */
			
			Properties props = new Properties();
			props.put("bootstrap.servers", kafkaInit.getKafkaServers());
			props.put("acks", kafkaInit.getAcks());
			props.put("key.serializer", kafkaInit.getKeySerializer());
			props.put("value.serializer", kafkaInit.getKeySerializer());
			//when server down send the response with in 3 second
			props.put("timeout.ms",kafkaInit.getTimeOut()); 
			//for no data lose
			props.put("block.on.buffer.full", kafkaInit.isBlockBuffer());
			// maximum retries
			props.put("retries", kafkaInit.getRetries()); 
			// auto commit
			props.put("auto.commit.interval.ms", kafkaInit.getAutoCommit());
			//linger time to 0, you might end up with a little bit of batching under load since 
			//records are getting added to be sent faster than the send thread can dispatch
			//them. This setting is optimizing for minimal latency
			props.put("linger.ms", kafkaInit.getLingerMs());
			@SuppressWarnings("resource")
			Producer<String, String> producer = new KafkaProducer<String, String>(props);
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(kafkaInit.getTopic(), kafkaInit.getMessage());
			try {
				producer.send(record).get();
				responseStatus.setServiceName("getKafkaServerHealth");
				responseStatus.setServerDecription("Ip and port and other information");
				responseStatus.setServerStatus("UP");
			} catch (InterruptedException e) {
				responseStatus.setServiceName("getKafkaServerHealth");
				responseStatus.setServerDecription("Ip and port and other information");
				responseStatus.setServerStatus("DOWN");
			} catch (ExecutionException e) {
				responseStatus.setServiceName("getKafkaServerHealth");
				responseStatus.setServerDecription("Ip and port and other information");
				responseStatus.setServerStatus("DOWN");
			}
			
		}catch(Exception e){
			responseStatus.setServiceName("getKafkaServerHealth");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("UNKNOWN");
		}
		return responseStatus;
	}

	@Override
	public ResponseStatus getJvmHealthCheck(JvmHealthCheck jvmInit) {
		ResponseStatus responseStatus=new ResponseStatus();
		String NAME = "jvm.memory";
		long GC_PROFILE_SLICE_NUMBER = 5;
		long DEFAULT_PROFILE_TIME = 300; // 5 mins
		MemoryMXBean mxBean=null;
		GarbageCollectorMXBean fullGcBean;
		Queue<Long> gcCounterQueue = new LinkedList<Long>();
		long lastGcReading = 0;
		try {
		mxBean = ManagementFactory.getMemoryMXBean();
		List<GarbageCollectorMXBean> gcMBeans = ManagementFactory.getGarbageCollectorMXBeans();
		
		GarbageCollectorMXBean bean = null;
		for (GarbageCollectorMXBean gcBean : gcMBeans) {
			String name = gcBean.getName();
			// TODO:: move hard coded values to prop file ?
			if ("PS MarkSweep".equalsIgnoreCase(name) || "ConcurrentMarkSweep".equalsIgnoreCase(name) || "G1 Old Generation".equalsIgnoreCase(name)
					|| "MarkSweepCompact".equalsIgnoreCase(name)) {
				bean = gcBean;
				break;
			} else {
				System.out.println("Unable to classify GarbageCollectorMXBean [" + gcBean.getName() + "]");
			}
		}
		fullGcBean = bean;
		
			long heapMax = mxBean.getHeapMemoryUsage().getMax();
			long heapUsed = mxBean.getHeapMemoryUsage().getUsed();
			double heapRatio = roundDouble(100 * ((double) heapUsed / (double) heapMax));
			boolean isHeapHealthy = (heapRatio < jvmInit.getJvmHeapThreshold()) ? true : false;

			long nonHeapMax = mxBean.getNonHeapMemoryUsage().getMax();
			long nonHeapUsed = mxBean.getNonHeapMemoryUsage().getUsed();
			double nonHeapRatio = roundDouble(100 * ((double) nonHeapUsed / (double) nonHeapMax));
			boolean isNonHeapHealthy = (nonHeapRatio < jvmInit.getJvmPermGenThreshold()) ? true : false;

			long gcCount = 0;

			if (fullGcBean != null) {
				long gcLatestReading = fullGcBean.getCollectionCount();
				for (Long item : gcCounterQueue) {
					gcCount = gcCount + item;
				}
				gcCount = gcCount + (gcLatestReading - lastGcReading);
				
				lastGcReading=gcLatestReading;
			}
			boolean isGcCountHealthy = (gcCount < jvmInit.getJvmGCCountThreshold()) ? true : false;

			/*data.addResult("heap.used", heapUsed);
			data.addResult("heap.max", heapMax);
			data.addResult("heap.ratio", heapRatio);
			data.addResult("heap.threshold", window.getJvmHeapThreshold());
			data.addResult("heap.isHealthy", isHeapHealthy);

			data.addResult("perm.used", nonHeapUsed);
			data.addResult("perm.max", nonHeapMax);
			data.addResult("perm.ratio", nonHeapRatio);
			data.addResult("perm.threshold", window.getJvmPermGenThreshold());
			data.addResult("perm.isHealthy", isNonHeapHealthy);

			data.addResult("gc.count", gcCount);
			data.addResult("gc.threshold", window.getJvmGCCountThreshold());
			data.addResult("gc.profile.duration", window.getJvmGCCountProfileDuration());
			data.addResult("gc.isHealthy", isGcCountHealthy);

			data.setHealthy(isHeapHealthy && isNonHeapHealthy && isGcCountHealthy);*/
			
			responseStatus.setServiceName("getJvmServerHealth");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("UP");

		} catch (Exception e) {
			responseStatus.setServiceName("getJvmServerHealth");
			responseStatus.setServerDecription("Ip and port and other information");
			responseStatus.setServerStatus("DOWN");
			System.out.println("un expected error while running jvm health check: reason: " + e.getMessage());
			e.printStackTrace();
		}
		return responseStatus;
	}
	
	public static double roundDouble(double doubleValue) {
		return new BigDecimal(doubleValue).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	
	
	

}
