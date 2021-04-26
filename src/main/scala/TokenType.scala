object TokenType extends Enumeration {
  type TokenType = Value
  val WHITESPACE, AND, OR, EQUALS, NOT_EQUALS, LEFT_PAREN, RIGHT_PAREN, KEY, VALUE, EOF = Value
}