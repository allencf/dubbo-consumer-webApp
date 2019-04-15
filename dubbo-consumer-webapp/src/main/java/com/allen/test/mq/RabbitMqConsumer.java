package com.allen.test.mq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.*;



public class RabbitMqConsumer {
	
	private static final String QUEUE_NAME = "shopDataEventSubscribe_GBT";
	
	private static final String EXCHANGE_NAME = "amq.direct";
	
	
	public static void sendMessage () throws Exception{
		  ConnectionFactory connectionFactory = new ConnectionFactory();
	        //在本地机器创建socket连接
	        connectionFactory.setHost("10.60.34.84:5672");
	        //建立socket连接
	        Connection connection = connectionFactory.newConnection();

	        //创建Channel，含有处理信息的大部分API
	        Channel channel = connection.createChannel();
	        //声明一个Queue，用来存放消息
	        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	        //消息内容
	        String message = "hello, little qute rabbitmq!";
	        //发布消息
	        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
	        //发布消息成功提示信息
	        System.out.println("RABBITMQ客户端成功发送信息：" +  message);

	        //关闭连接
	        channel.close();
	        connection.close();
	}
	
	
	public static void consumeMessage() throws Exception {
		 ConnectionFactory connectionFactory = new ConnectionFactory();
	        //在本地机器创建socket连接
	        connectionFactory.setHost("localhost");
	        //建立socket连接
	        Connection connection = connectionFactory.newConnection();

	        /* 创建Channel，含有处理信息的大部分API */
	        Channel channel = connection.createChannel();
	        //声明一个Queue，用来获取消息。QUEUE_NAME需要与Producer端相同
	        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

	        //从队列中异步获取消息，DefaultConsumer会设置一个回调来缓存消息。
	        Consumer consumer = new DefaultConsumer(channel) {
	            @Override
	            public void handleDelivery(String consumerTag, Envelope envelope,
	                                       AMQP.BasicProperties properties, byte[] body)
	                   {
	                try {
	                	String message = new String(body, "UTF-8");
		                System.out.println("Consumer获取消息：" + message );
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }
	        };
	        channel.basicConsume(QUEUE_NAME, true, consumer);
	}
	
	
	public static void consumeDirectMessage () {
		try {
			ConnectionFactory connectionFactory = new ConnectionFactory();
	        connectionFactory.setHost("10.60.34.84");
	        connectionFactory.setPort(5672);
	        connectionFactory.setVirtualHost("SOA_PROVIDER");
	        connectionFactory.setUsername("admin");
	        connectionFactory.setPassword("admin@hqyg");
	        Connection connection = connectionFactory.newConnection();
	       // connection.
	        Channel channel = connection.createChannel();

	        //channel.
	        channel.exchangeDeclare(EXCHANGE_NAME, "direct" ,true);
	        //channel.exchangeDeclare(QUEUE_NAME, type, durable);
	        String queueName = channel.queueDeclare().getQueue();
	        channel.queueBind(queueName, EXCHANGE_NAME, queueName);

	        System.out.println(" ---【开始接收消息，退出请按CTRL+C】---");
	        //RabbitMQConsumer_Topic consumer = new RabbitMQConsumer_Topic();
	       /* QueueingConsumer consumer = new QueueingConsumer(channel);
	        channel.basicConsume(queueName, true, consumer);

	        while (true){
	            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	            String message = new String(delivery.getBody());
	            String routingKey =  delivery.getEnvelope().getRoutingKey();
	            System.out.println(" Consumer1接收消息： '" + routingKey + "':'" + message + "'");
	        }*/
	        
	        //从队列中异步获取消息，DefaultConsumer会设置一个回调来缓存消息。
	        
	        Consumer consumer = new DefaultConsumer(channel) {
	            @Override
	            public void handleDelivery(String consumerTag, Envelope envelope,
	                                       AMQP.BasicProperties properties, byte[] body)
	                   {
	                try {
	                	String message = new String(body, "UTF-8");
		                System.out.println("Consumer获取消息：" + message );
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }
	        };
	        
	        channel.basicConsume(QUEUE_NAME, true, consumer);
	        
	        
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	public static void main(String[] args) {
		try {
			consumeDirectMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
