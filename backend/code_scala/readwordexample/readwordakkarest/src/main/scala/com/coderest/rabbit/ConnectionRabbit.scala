package com.coderest.rabbit

import com.rabbitmq.client.{Channel, Connection, ConnectionFactory}

object ConnectionRabbit {
  val factory = new ConnectionFactory
  factory.setHost("localhost")
  factory.setPort(5672)
  val connection: Connection = factory.newConnection
  val channel: Channel = connection.createChannel

  val DIRECT_EXCHANGE_NAME: String = "demo-direct-exchange"
  val QUEUE_NAME: String = "demo-rabbitmq";
}
