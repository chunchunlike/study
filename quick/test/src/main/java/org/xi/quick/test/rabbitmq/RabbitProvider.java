package org.xi.quick.test.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitProvider {
    //队列名称
    private final static String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws Exception {
        //创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.10.47");
        factory.setUsername("crmadmin");
        factory.setPassword("crmadmin123");
        factory.setVirtualHost("crm_host");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));

        channel.close();
        connection.close();
    }
}
