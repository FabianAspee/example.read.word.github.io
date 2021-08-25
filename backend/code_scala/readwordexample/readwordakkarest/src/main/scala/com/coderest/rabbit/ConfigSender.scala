package com.coderest.rabbit

object ConfigSender
{

  def convertAndSend(typeSend: String,routingKey :String, message:String):Unit=
  {
    val connection = ConnectionRabbit.connection;
    val channel = connection.createChannel();
    channel.exchangeDeclare(typeSend, "direct", true);
    channel.queueDeclare(ConnectionRabbit.QUEUE_NAME, true, false,  false,  null).getQueue
    channel.basicPublish(typeSend, "direct", null, message.getBytes());

  }
}
