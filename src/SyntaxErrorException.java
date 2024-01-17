//defines a syntax error for my parser and lexer
public class SyntaxErrorException extends Exception
{
    public SyntaxErrorException()
    { 
        super(); 
    }
    public SyntaxErrorException(String message)
    {
        super(message);
    }
}
 