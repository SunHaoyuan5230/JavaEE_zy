package com.yuan.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: SunHaoyuan
 * @Date: 2019/12/25 19:24
 * @Description:
 */
public class JMSConsumer {
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
        MessageConsumer consumer = session.createConsumer(queue);
        // 7：创建JMS consumer或者是注册一个JMS message listener
         /*
        异步非阻塞方式(监听器onMessage())
        订阅者或接收者通过MessageConsumer的setMessageListener(MessageListener listener)注册一个消息监听器，
        当消息到达之后，系统自动调用监听器MessageListener的onMessage(Message message)方法。*/
        consumer.setMessageListener(message -> {
            if (null != message && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("***接受到消息： "+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}