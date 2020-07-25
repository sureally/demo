import org.slf4j.LoggerFactory

/**
 *
 * @author shu wj
 */
object Hello {
  protected lazy val logger = LoggerFactory.getLogger("LoggerMain")

  def main(args: Array[String]): Unit = {
    logger.info("hello")
    logger.info("hello")
  }

}
