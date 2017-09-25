package org.xi.quick.test.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class RabbitConsumer {
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

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Customer Received '" + message + "'");
            }
        };

        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
