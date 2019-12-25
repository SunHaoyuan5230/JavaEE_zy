package com.yuan.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: SunHaoyuan
 * @Date: 2019/12/25 19:23
 * @Description:
 */
public class JMSProduce {
    public static final String MY_URL = "tcp://192.168.19.102:61616";
    public static final String QUEUE_NAME = "q1";

    public static void main(String[] args) throws JMSException {

        // JMS开发的基本步骤
        // 1：创建一个connection factory
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MY_URL);
        // 2：通过connection factory来创建JMS connection
        Connection connection = activeMQConnectionFactory.createConnection();
        // 3：启动JMS connection
        connection.start();
        // 4：通过connection创建JMS session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 5：创建JMS destination
        Queue queue = session.createQueue(QUEUE_NAME);
        // 6：创建JMS producer或者创建JMS message并设置destination
        MessageProducer producer = session.createProducer(queue);
        for (int i = 0; i <3; i++) {
            // 7：创建JMS consumer或者是注册一个JMS message listener
            TextMessage textMessage = session.createTextMessage("****msg----" + i);
            // 8：发送或者接受JMS message(s)
            producer.send(textMessage);
        }
        // 9：关闭所有的JMS资源
        // (connection, session, producer, consumer等)
        connection.close();
        session.close();
        producer.close();
        System.out.println("*****run is ok");

    }
}