import java.text.ParseException

import scala.collection.mutable.ListBuffer


class OrExpr @throws[ParseException]
(val stream: TokenStream) extends Expr {
  final private val children = ListBuffer[Expr]()
  do {
    children+=new AndExpr(stream)
  } while ( {
    stream.consumeIf(TokenType.OR) != null
  })

  override def evaluate(data: Map[String, String]): Boolean = {
    for (child <- children) {
      if (child.evaluate(data)) return true
    }
    false
  }
}