/**
 * Copyright 2014 Christoph Neumann
 */
package app

import java.io.File
import scala.sys.process._
import org.joda.time.{DateTime, DateTimeConstants, Duration, LocalDate}


object Main {
  def main(argv: Array[String]) {
    import format._

    if ( argv.length == 0 ) {
      System.err.println("usage: seeping ip_address")
      System.exit(1)
    }

    var host = argv(0)

    var now = new DateTime()
    var was = now
    println(date(now))

    var sec = 0
    var good = 0
    while ( true ) {
      val from = new DateTime().getMillis
      val code = (("ping -W 1 -c 1 "+ host) #> new File("/dev/null")) ! ProcessLogger(line => ())
      if (code == 0) { good += 1 }
      sec += 1

      if ( sec == 1 ) {
        print("%s |".format(time(now)))
      }

      print("%s".format(if (code == 0) "#" else "."))

      if ( sec >= 60 ) {
        println(summarize(good))
        sec = 0
        good = 0
        was = now
        now = new DateTime()
        if ( now.dayOfMonth != was.dayOfMonth ) {
          println(date(now))
        }
      }
      Thread.sleep(math.max(0, 1000 - (new DateTime().getMillis - from)))
    }
  }

  def summarize(good: Int): String = {
    val last_10 = Math.max(0,(good - 50))
    val first_50 = (Math.min(0,(good - 50))+50) / 10
    "|%s%s%s%s| %s".format("#" * first_50, "." * (5 - first_50), "%" * last_10, "." * (10 - last_10), good)
  }
}

object format {
  import org.joda.time.format.DateTimeFormat

  val date_format = DateTimeFormat.forPattern("EEE MMM dd yyyy")
  val time_format = DateTimeFormat.forPattern("HH:mm")

  def date(dt: DateTime): String = date_format.print(dt)
  def time(dt: DateTime): String = time_format.print(dt)
}
