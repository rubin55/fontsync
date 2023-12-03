
import scopt.OParser
import os.size
import scalanative.unsafe.*

import coulomb.*
import coulomb.syntax.*
import cats.syntax.either.*
import io.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.scalayaml.*
import io.circe.scalayaml.syntax.*
import coulomb.units.si.*

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
    // Test c function calls.
    val exit = system(c"ls -l > test.txt")
    println(s"Hello, native Scala world, I got ${add3(3)} from a native C function.. and exit from some command was: $exit")

    // Test scopt.
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

    OParser.parse(parser1, args, Config()) match {
      case Some(config) =>
      // do something
      case _ =>
      // arguments are bad, error message will have been displayed
    }

    // Test os-lib. Note github.com/com-lihaoyi/os-lib/issues/197
    val temp = os.temp()
    val size = os.size(temp)
    println(s"Temp file $temp is $size")

    // Test coulomb.
    val a = 9.8.withUnit[Meter / (Second ^ 2)]
    println(s"Coulomb says: ${a.show}")

    // Test circe.
    sealed trait Foo
    case class Bar(xs: Vector[String]) extends Foo
    case class Qux(i: Int, d: Option[Double]) extends Foo
    val foo: Foo = Qux(13, Some(14.0))
    val json = foo.asJson.noSpaces
    println(json)

    // Test circe-scala-yaml
    val json2 = io.circe.jawn.parse("""{"foo":"bar"}""").valueOr(throw _)
    println(json2.asYaml.spaces2)

    // Test scala-xml
    val book: scala.xml.Elem = <book id="b20234">Magic of scala-xml</book>
    val id = book \@ "id"
    println(s"Book $id text is: ${book.text}")
  }
}
