import java.text.ParseException

import scala.collection.mutable.ListBuffer


class AndExpr @throws[ParseException]
(val stream: TokenStream) extends Expr {
  final private val children = ListBuffer[Expr]()
  do {
    children += new SubExpr(stream)
  } while ( {
    stream.consumeIf(TokenType.AND) != null
  })

  override def evaluate(data: Map[String, String]): Boolean = {
    for (child <- children) {
      if (!child.evaluate(data)) return false
    }
    true
  }
}