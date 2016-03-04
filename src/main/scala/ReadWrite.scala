import java.io._
import scala.io.Source

/**
 * Created by Erica on 16-02-03.
 */

class ReadWrite {

  def read(file: String) {
    Source.fromFile(file).getLines()
  }

  def write(fileName: String, content: String) {
    val file = new File(fileName)
    val pw = new PrintWriter(file)
    pw.write(content)
    pw.close()
  }

  def append(fileName: String, content: String) {

    val file = new File(fileName)
    val pw = new FileWriter(file, true)
    pw.write(content)
    pw.close()

  }


}
