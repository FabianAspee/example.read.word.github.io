package com.coderest.rabbit

object Sender {
  def sendViaDirectExchange(message: String):Unit =
    ConfigSender.convertAndSend(ConnectionRabbit.DIRECT_EXCHANGE_NAME, "", message)
}
