import java.text.ParseException

class InnerExpr @throws[ParseException]
(val stream: TokenStream) extends Expr {
  final private var key: String = _
  final private var operator: String = _
  final private var value: String = _
  val token: Token = stream.consume(TokenType.KEY)
  this.key = token.data
  if (stream.consumeIf(TokenType.EQUALS) != null) {
    this.operator = "="
    this.value = stream.consume(TokenType.VALUE).data
  }
  else {
    this.operator = "!="
    this.value = stream.consume(TokenType.VALUE).data
  }

  override def evaluate(data: Map[String, String]): Boolean = if (operator == "=") data.get(key).contains(value)
  else  !data.get(key).contains(value)
}