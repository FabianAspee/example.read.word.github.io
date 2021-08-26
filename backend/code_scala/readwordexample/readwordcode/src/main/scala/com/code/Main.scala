package com.code

import com.readwordcode2.factory.SubscriberFactory

object Main extends App{
  def print(string: String):Unit={

  }
  SubscriberFactory.getReadDirectory(print).readDirectory()
}
