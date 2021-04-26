import java.text.ParseException
import java.util.regex.Pattern

import scala.collection.mutable.ListBuffer
import scala.util.control._

object Main extends App {

  private val TOKENS = Pattern.compile("(\\s+)|(AND)|(OR)|(=)|(!=)|(\\()|(\\))|(\\w+)|\'([^\']+)\'")

  @throws[ParseException]
  def tokenize(input: String) = {
    val matcher = TOKENS.matcher(input)
    val tokens = ListBuffer[Token]()
    var offset = 0
    val types = TokenType.values.toList
    while ( {
      offset != input.length
    }) {
      if (!matcher.find || matcher.start != offset) throw new ParseException("Unexpected token at " + offset, offset)
      val loop = new Breaks
      loop.breakable {
        for (i <- types.indices) {
          if (matcher.group(i + 1) != null) {
            if (types(i) ne TokenType.WHITESPACE) tokens += new Token(types(i), offset, matcher.group(i + 1))
            loop.break() //todo: break is not supported

          }
        }
      }
      offset = matcher.`end`
    }
    tokens+=new Token(TokenType.EOF, input.length, "")
    new TokenStream(tokens)
  }

  @throws[ParseException]
  def parse(stream: TokenStream): OrExpr = {
    val expr = new OrExpr(stream)
    stream.consume(TokenType.EOF) // ensure that we parsed the whole input
    expr
  }

  try {
    val query = "Name = 'Ambikesh' AND (Designation = 'Software Developer' OR Designation = 'Manager')"
    val json = Map[String,String]("Name" -> "Ambikesh", "Designation"->"Software Developer")
    val expr: Expr = parse(tokenize(query))
    System.out.println(expr.evaluate(json)) // true
  } catch {
    case e: Exception =>
      System.out.println(e.getLocalizedMessage)
  }
}
