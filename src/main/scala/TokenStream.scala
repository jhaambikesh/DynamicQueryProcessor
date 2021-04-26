import java.text.ParseException

import TokenType.TokenType

import scala.collection.mutable.ListBuffer


class TokenStream(val tokens: ListBuffer[Token]) {
  var offset = 0

  // consume next token of given type (throw exception if type differs)
  @throws[ParseException]
  def consume(`type`: TokenType): Token = {
    val token = tokens(offset)
    offset += 1
    if (token.`type` ne `type`) throw new ParseException("Unexpected token at " + token.start + ": " + token + " (was looking for " + `type` + ")", token.start)
    token
  }

  // consume token of given type (return null and don't advance if type differs)
  def consumeIf(`type`: TokenType): Token = {
    val token = tokens(offset)
    if (token.`type` eq `type`) {
      offset += 1
      return token
    }
    null
  }

  override def toString: String = tokens.toString
}