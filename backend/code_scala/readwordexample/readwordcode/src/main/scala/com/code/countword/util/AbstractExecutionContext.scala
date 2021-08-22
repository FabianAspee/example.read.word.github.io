package com.code.countword.util

import scala.concurrent.ExecutionContextExecutor

abstract class AbstractExecutionContext {
  implicit protected val execution: ExecutionContextExecutor = Execution.executionContext
}
