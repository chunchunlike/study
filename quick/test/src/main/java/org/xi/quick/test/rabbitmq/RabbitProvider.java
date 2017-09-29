package org.xi.quick.test.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitProvider {

    //队列名称
    private final static String QUEUE_NAME = "queue_test";

    public static void main(String[] args) throws Exception {

        //创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.20.73");
        factory.setUsername("xi");
        factory.setPassword("rarexi213");
        factory.setVirtualHost("/xi");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for(int i = 0; i < 1000; i++) {
            String message = "message" + i;
            System.out.println("provider send" + message);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            Thread.sleep(1000);
        }

        channel.close();
        connection.close();
    }
}
