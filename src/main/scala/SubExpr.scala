import java.text.ParseException

class SubExpr @throws[ParseException]
(val stream: TokenStream) extends Expr {
  final private var child: Expr = _
  if (stream.consumeIf(TokenType.LEFT_PAREN) != null) {
    child = new OrExpr(stream)
    stream.consume(TokenType.RIGHT_PAREN)
  }
  else child = new InnerExpr(stream)

  override def evaluate(data: Map[String, String]): Boolean = child.evaluate(data)
}