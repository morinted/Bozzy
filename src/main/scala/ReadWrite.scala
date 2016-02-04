import java.io._
import scala.io.Source

/**
 * Created by Erica on 16-02-03.
 */

class ReadWrite {

  def read(file: String) {
    for (line <- Source.fromFile(file).getLines()) {
      println(line)
    }
  }

  def write(fileName: String, content: String) {

    val file = new File(fileName)
    val pw = new PrintWriter(file)
    pw.write(content)
    pw.close()


  }
}
