/**
 * 
 */
package com.kartik.Ihealth;

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

/**
 * @author kmandal
 *
 */
public interface IHealth {
	/**
	 * 
	 * @param mongoInit
	 * @return
	 */
	ResponseStatus getMongoHelath(MongoInit mongoInit);
	/**
	 * 
	 * @param datasorceInit
	 * @return
	 */
	ResponseStatus getDataSourceHelath(DataSourceInit datasorceInit);
	/**
	 * 
	 * @param jmssorceInit
	 * @return
	 */
	ResponseStatus getJmsHelath(JmsSourceInit jmssorceInit);
	/**
	 * 
	 * @param serviecApiInit
	 * @return
	 */
	ResponseStatus getServiceApiHelath(ServiecApiInit serviecApiInit);
	/**
	 * 
	 * @param ldapInit
	 * @return
	 */
	ResponseStatus getLdapHelath(LdapInit ldapInit);
	/**
	 * 
	 * @param activeMqInit
	 * @return
	 */
	ResponseStatus getActiveMqHelath(ActiveMqInit activeMqInit);
	/**
	 * 
	 * @param jbossServerStartUpInit
	 * @return
	 */
	ResponseStatus getJbossServerStartUp(JbossServerStartUpInit jbossServerStartUpInit);
	/**
	 * 
	 * @param emailInit
	 * @return
	 */
	ResponseStatus getEmailServerHealth(EmailInit emailInit);
	/**
	 * 
	 * @param kafkaInit
	 * @return
	 */
	ResponseStatus getKafkaServerHealth(KafkaInit kafkaInit);
	/**
	 * 
	 * @param jvmInit
	 * @return
	 */
	ResponseStatus getJvmHealthCheck(JvmHealthCheck jvmInit);

}
