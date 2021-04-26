import TokenType.TokenType

class Token(val `type`: TokenType, val start: Int // start position in input (for error reporting)
            , val data: String // payload
           ) {
  override def toString: String = `type` + "[" + data + "]"
}