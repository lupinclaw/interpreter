//class defining token objects
//used in Lexer.java

public class Token {
    public enum tokenType {IDENTIFIER, NUMBER, ENDOFLINE, WHILE, FOR, STRINGLITERAL, CHARACTERLITERAL, IF, THEN, ELSIF, FROM, INDENT, DEDENT, DEFINE, CONSTANTS, 
                            WRITE, VAR, INTEGER, READ, TO, ARRAY, BOOLEAN, VARIABLES, STARTPARAMS, ENDPARAMS, COMMA, ASSIGMENT, PLUS, NOT, AND, OR, MOD,
                            ELSE, EQUALS, MINUS, SUBTRACT, MULTIPLY, INTDIVITION, NOTEQUAL, GREATERTHAN, LESSTHEN, GREATEREQUALS, LESSEQUALS, STRING, 
                            CHARACTER, REAL, STARTINDEX, ENDINDEX, REPEAT, UNTIL, TYPEDECLAR, OF, LEFT, RIGHT, SUBSTRING, SQUAREROOT, GETRANDOM,
                            INTEGERTOREAL, REALTOINTEGER, START, END, SEMICOLON, TRUE, FALSE}

    //fields
    private tokenType m_TokenType;
    private String m_Value;

    //contructor(s)
    public Token(tokenType p_TokenType, String p_Value)
    {
        m_TokenType = p_TokenType;
        m_Value = p_Value;
    }

    public Token(tokenType p_TokenType)
    {
        m_TokenType = p_TokenType;
        m_Value = null;
    }

    //accessors
    public tokenType getTokenType()
    {
        return this.m_TokenType;
    }

    public String getTokenValue()
    {
        return this.m_Value;
    }

    //@overide
    //toString method
    public String toString()
    {
        if(this.m_Value == null)    
            return (this.m_TokenType + "");
        return (this.m_TokenType + "(" + this.m_Value + ")");
    }


}

