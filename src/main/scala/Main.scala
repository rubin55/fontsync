
import scopt.OParser

import scalanative.unsafe.*

@extern
private object other {
  def add3(in: CLongLong): CLongLong = extern
}

@extern
private object libc {
  def system(command: CString): CInt = extern
}

import other._
import libc._

case class Config(
  foo: Int = -1,
  xyz: Boolean = false,
  libName: String = "",
  maxCount: Int = -1,
  verbose: Boolean = false,
  debug: Boolean = false,
  mode: String = "",
  keepalive: Boolean = false,
  kwargs: Map[String, String] = Map())


object Main {
  def main(args: Array[String]): Unit = {
    // Verify that c function calls work.
    val exit = system(c"ls -l > test.txt")
    println(s"Hello, native Scala world, I got ${add3(3)} from a native C function.. and exit from some command was: $exit")

    val builder = OParser.builder[Config]
    val parser1 = {
      import builder._
      OParser.sequence(
        programName("scopt"),
        head("scopt", "4.x"),
        // option -f, --foo
        opt[Int]('f', "foo")
          .action((x, c) => c.copy(foo = x))
          .text("foo is an integer property"),
        // more options here...
      )
    }

    // OParser.parse returns Option[Config]
    OParser.parse(parser1, args, Config()) match {
      case Some(config) =>
      // do something
      case _ =>
      // arguments are bad, error message will have been displayed
    }
  }
}
