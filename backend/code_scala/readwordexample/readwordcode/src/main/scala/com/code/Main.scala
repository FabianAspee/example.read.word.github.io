package com.code

import com.code.factory.SubscriberFactory

object Main extends App{
  def print(string: String):Unit={

  }
  SubscriberFactory.getReadDirectory(print).readDirectory()
}
