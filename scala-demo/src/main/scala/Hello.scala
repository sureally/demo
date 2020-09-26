import org.slf4j.LoggerFactory

/**
 *
 * @author shu wj
 */
object Hello {
  protected lazy val logger = LoggerFactory.getLogger("LoggerMain")

  def main(args: Array[String]): Unit = {
    func(printHello _)
  }

  def printHello() {
    val msg = "word"
    logger.info(s"hello $msg")
  }

  def func(fun: () => Unit): Unit = {
    fun.apply()
    logger.info("func run finished")
  }
}
