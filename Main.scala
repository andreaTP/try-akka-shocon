import akka.actor.ActorSystem
import com.typesafe.config._

object Repro {

  def main(args: Array[String]): Unit = {

    // org.akkajs.shocon.setVerboseLog()

    println("Before Configuration")
    val beforeConfig = System.currentTimeMillis()

    val config: Config =
      ConfigFactory.load()

    val afterConfig = System.currentTimeMillis()
    val deltaConfig = afterConfig - beforeConfig
    println(s"After Configuration, took $deltaConfig ms")

    println("Before ActorSystem")
    val before = System.currentTimeMillis()

    val system = ActorSystem("repro-as", config)
    
    val after = System.currentTimeMillis()
    val delta = after - before
    println(s"After ActorSystem, took $delta ms")

    system.terminate()
  }
}
