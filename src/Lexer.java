import java.util.*;


//lexer class, accessed in Shank.java
public class Lexer
{
    //declarations
    ArrayList<Token> Tokens = new ArrayList<Token>();
    public enum state {NONE, IDENTIFIER, NUMBER, NUMBERDOT, COMMENT, CHARACTERLITERAL, STRINGLITERAL, OPERATORS}
    state m_CurrentState = state.NONE;
    String[] m_CurrentInput = new String[1];
    String m_Accumulator = "";
    int m_LineNumber = 1, m_IndentationLevel;
    HashMap<String, Token.tokenType> m_KnownWords = new HashMap<String, Token.tokenType>();

    //contructor to fill the hashmat
    public Lexer ()
    {
        fillKnownWords();
    }
    
    //lex method performs lexical analysis of a line of text represented as a String
    //and tokenizes if it contains valid tokens such as NUMBER, IDENTIFIER or ENDOFLINE otherwise
    //it throws an error
    public void lex (String p_LineOfRawInput) throws Exception
    {
        int spacesCounted = 0;
        char[] inputAsCharArray = p_LineOfRawInput.toCharArray();
        int indexOfCurrentChar = 0;

        if(inputAsCharArray.length == 0) //skip empty lines before we index a char array with no elements
        {
            Tokens.add(new Token(Token.tokenType.ENDOFLINE));
            m_LineNumber++;
            return; 
        }

        //check indents by counting whitespaces
        if(!(m_CurrentState == state.COMMENT))
        {
            char currentChar = inputAsCharArray[indexOfCurrentChar];
            while(Character.isWhitespace(currentChar))
            {
                spacesCounted++;
                currentChar = inputAsCharArray[++indexOfCurrentChar];
            }
            indentationAdjust(spacesCounted/4);
        }

        //lexing logic, state mechine
        // loops thru each char in the string
        for (char c : p_LineOfRawInput.toCharArray())
        {
            //switches on each state to determine what to do with the current char
            switch(m_CurrentState)
            {
                case NONE:
                    if (Character.isLetter(c))
                    {
                        m_Accumulator += c;
                        m_CurrentState = state.IDENTIFIER;
                        break;
                    }
                    else if(c == '.')
                    {
                        m_CurrentState = state.NUMBERDOT;
                        m_Accumulator += ".";
                        break;
                    }
                    else if (Character.isDigit(c))
                    {
                        m_CurrentState = state.NUMBER;
                        m_Accumulator += c;
                        break;
                    }
                    else if (c  == '{')
                    {
                        m_CurrentState = state.COMMENT;
                        break;
                    }
                    else if (c  == '"')
                    {
                        m_CurrentState = state.STRINGLITERAL;
                        break;
                    }
                    else if (c  == '(' || c == ')')
                    {
                        makeParamToken(c);
                        break;
                    }
                    else if (c  == '\'')
                    {
                        m_CurrentState = state.CHARACTERLITERAL;
                        break;
                    }
                    else if (c  == ',')
                    {
                        Tokens.add(new Token(Token.tokenType.COMMA));
                        break;
                    }
                    else if (c  == ';')
                    {
                        Tokens.add(new Token(Token.tokenType.SEMICOLON));
                        break;
                    }
                    else if (c  == '[' || c == ']')
                    {
                        makeIndexToken(c);
                        break;
                    }
                    else if (c  == '/' || c  == '-'  || c  == '*' || c  == '+' || c  == '='  || c  == '<' || c  == '>' || c  == ':' )
                    {
                        m_CurrentState = state.OPERATORS;
                        m_Accumulator += c;
                        break;
                    }
                    else if (c  == ' ')
                    {
                        break;
                    }

                    else
                        throw new SyntaxErrorException("Syntax Error: lexed unexcepted character:\"" + c + "\", on line: " + m_LineNumber);

                case IDENTIFIER: 
                    if(Character.isLetterOrDigit(c))
                    {
                        m_Accumulator += c;
                        break;
                    }
                    else if(c == ' ')
                    {
                        if (m_KnownWords.containsKey(m_Accumulator))
                        {
                            Tokens.add(new Token(m_KnownWords.get(m_Accumulator)));
                            m_Accumulator = "";
                            m_CurrentState = state.NONE;
                            break;
                        }
                        else 
                        {
                            Tokens.add(new Token(Token.tokenType.IDENTIFIER, m_Accumulator));
                            m_Accumulator = "";
                            m_CurrentState = state.NONE;
                            break;
                        }
                    }
                    else if (m_Accumulator != "")
                    {  
                        if (m_KnownWords.containsKey(m_Accumulator))
                        {
                            Tokens.add(new Token(m_KnownWords.get(m_Accumulator)));
                            m_Accumulator = "";
                            m_CurrentState = state.NONE;
                            noneStateLogic(c);
                            break;
                        }
                        else
                        {
                            Tokens.add(new Token(Token.tokenType.IDENTIFIER, m_Accumulator));
                            m_Accumulator = "";
                            m_CurrentState = state.NONE;
                            noneStateLogic(c);
                            break;
                        }
                    }
                    else
                    {
                        m_Accumulator = "";
                        m_CurrentState = state.NONE;
                        break;
                    }
                case NUMBER://letters after read in while in NUMBER or NUMBERDOT state are treated as 
                    if (Character.isDigit(c))  //unexpected characters.
                    {                          
                        m_Accumulator += c;
                        break;
                    }
                    else if(c  == ' ')
                    { 
                        Tokens.add(new Token(Token.tokenType.INTEGER, m_Accumulator));
                        m_Accumulator = "";
                        m_CurrentState = state.NONE;
                        break;
                    }
                    else if(c == '.')
                    {
                        m_CurrentState = state.NUMBERDOT;
                        m_Accumulator += ".";   
                        break;
                    }
                    else
                    {   
                        Tokens.add(new Token(Token.tokenType.INTEGER, m_Accumulator));
                        m_Accumulator = "";
                        m_CurrentState = state.NONE;
                        noneStateLogic(c);
                        break;
                    }
                    
                case NUMBERDOT:  // '.' or letters are treated as unexpected charaters while reading in a float
                    if (Character.isDigit(c))
                    {
                        m_Accumulator += c;
                        break;
                    }
                    else if(c  == ' ')
                    {
                        Tokens.add(new Token(Token.tokenType.REAL, m_Accumulator));
                        m_Accumulator = "";
                        m_CurrentState = state.NONE;
                        break;
                    }
                    else
                    {
                        Tokens.add(new Token(Token.tokenType.REAL, m_Accumulator));
                        m_Accumulator = "";
                        m_CurrentState = state.NONE;
                        noneStateLogic(c);
                        break;
                    }

                case STRINGLITERAL: 
                    if (!(c == '"'))
                    {
                        m_Accumulator += c;
                        if(indexOfCurrentChar == (inputAsCharArray.length - 1)) //if end of line without ever closing
                        {
                            throw new SyntaxErrorException("Syntax Error: Unterminated String on line: " + m_LineNumber + " Expected: \" but reached end of line ");
                        }
                        break;
                    }
                    else if(c  == '"')
                    {
                        Tokens.add(new Token(Token.tokenType.STRINGLITERAL, m_Accumulator));
                        m_Accumulator = "";
                        m_CurrentState = state.NONE;
                        break;
                    }
                    else
                    {
                        throw new SyntaxErrorException("Syntax Error: Unterminated String on line: " + m_LineNumber + " Expected: \" got: " + c);
                    }
                case CHARACTERLITERAL:  
                    if (m_Accumulator == "")
                    {
                        m_Accumulator += c;
                        break;
                    }
                    else if(c  == '\'')
                    {
                        Tokens.add(new Token(Token.tokenType.CHARACTERLITERAL, m_Accumulator));
                        m_Accumulator = "";
                        m_CurrentState = state.NONE;
                        break;
                    }
                    else
                    {
                        throw new SyntaxErrorException("Syntax Error: Unterminated Character literal on line: " + m_LineNumber + " Expected: ' got: " + c);
                    }
                case COMMENT: 
                    if (c  == '}')
                    {
                        m_Accumulator = "";
                        m_CurrentState = state.NONE;
                        break;
                    }
                    break;
                case OPERATORS:
                    if (m_Accumulator.charAt(0) == ':' && c == '=')
                    {
                        Tokens.add(new Token(Token.tokenType.ASSIGMENT));
                        m_Accumulator = "";
                        m_CurrentState = state.NONE;
                        break;
                    }
                    else if (m_Accumulator.charAt(0) == '>' && c == '=' )
                    {
                        m_Accumulator += c;
                        Tokens.add(new Token(Token.tokenType.GREATEREQUALS));
                        m_Accumulator = "";
                        m_CurrentState = state.NONE;
                        break;
                    }
                    else if (m_Accumulator.charAt(0) == '<' && c == '=')
                    {
                        m_Accumulator += c;
                        Tokens.add(new Token(Token.tokenType.LESSEQUALS));
                        m_Accumulator = "";
                        m_CurrentState = state.NONE;
                        break;
                    }
                    else if (m_Accumulator.charAt(0) == '<' && c == '>')
                    {
                        m_Accumulator += c;
                        Tokens.add(new Token(Token.tokenType.NOTEQUAL));
                        m_Accumulator = "";
                        m_CurrentState = state.NONE;
                        break;
                    }
                    else
                    {
                        if(m_Accumulator.charAt(0) == '=')
                        {
                            Tokens.add(new Token(Token.tokenType.EQUALS));
                            if(c != ' ')
                            {
                                m_Accumulator = "";
                                noneStateLogic(c);
                                break;
                            }
                            else
                            {
                                m_Accumulator = "";
                                m_CurrentState = state.NONE;
                                break;
                            }
                            
                        }
                        if(m_Accumulator.charAt(0) == '/')
                        {
                            Tokens.add(new Token(Token.tokenType.INTDIVITION));
                            if(c != ' ')
                            {
                                m_Accumulator = "";
                                noneStateLogic(c);
                                break;
                            }
                            else
                            {
                                m_Accumulator = "";
                                m_CurrentState = state.NONE;
                                break;
                            }
                            
                        }
                        if(m_Accumulator.charAt(0) == '-')
                        {
                            Tokens.add(new Token(Token.tokenType.MINUS));
                            if(c != ' ')
                            {
                                m_Accumulator = "";
                                noneStateLogic(c);
                                break;
                            }
                            else
                            {
                                m_Accumulator = "";
                                m_CurrentState = state.NONE;
                                break;
                            }
                        }
                        if(m_Accumulator.charAt(0) == '+')
                        {
                            Tokens.add(new Token(Token.tokenType.PLUS));
                            if(c != ' ')
                            {
                                m_Accumulator = "";
                                noneStateLogic(c);
                                break;
                            }
                            else
                            {
                                m_Accumulator = "";
                                m_CurrentState = state.NONE;
                                break;
                            }
                        }
                        if(m_Accumulator.charAt(0) == '>')
                        {
                            Tokens.add(new Token(Token.tokenType.GREATERTHAN));
                            if(c != ' ')
                            {
                                m_Accumulator = "";
                                noneStateLogic(c);
                                break;
                            }
                            else
                            {
                                m_Accumulator = "";
                                m_CurrentState = state.NONE;
                                break;
                            }
                        }
                        if(m_Accumulator.charAt(0) == '<')
                        {
                            Tokens.add(new Token(Token.tokenType.LESSTHEN));
                            if(c != ' ')
                            {
                                m_Accumulator = "";
                                noneStateLogic(c);
                                break;
                            }
                            else
                            {
                                m_Accumulator = "";
                                m_CurrentState = state.NONE;
                                break;
                            }
                        }
                        if(m_Accumulator.charAt(0) == '*')
                        {
                            Tokens.add(new Token(Token.tokenType.MULTIPLY));
                            if(c != ' ')
                            {
                                m_Accumulator = "";
                                noneStateLogic(c);
                                break;
                            }
                            else
                            {
                                m_Accumulator = "";
                                m_CurrentState = state.NONE;
                                break;
                            }
                        }
                        if(m_Accumulator.charAt(0) == ':')
                        {
                            Tokens.add(new Token(Token.tokenType.TYPEDECLAR));
                            if(c != ' ')
                            {
                                m_Accumulator = "";
                                noneStateLogic(c);
                                break;
                            }
                            else
                            {
                                m_Accumulator = "";
                                m_CurrentState = state.NONE;
                                break;
                            }
                        }
                        throw new SyntaxErrorException("Syntax Error on line: " + m_LineNumber + " invalid operator: " + m_Accumulator + c);
                    }                      
                    
                default:
                    throw new Exception("Error: state failer");
                    
                        
            }

            indexOfCurrentChar++; //end of for loop increment index (for methods like peek that need a index) 
        }
        if (m_Accumulator != "")  //if we are at the end of a line of all text that never hit a space to emit a token, we add that to Tokens here
        {
            if(m_CurrentState == state.NUMBERDOT)
                Tokens.add(new Token(Token.tokenType.REAL, m_Accumulator));
            if(m_CurrentState == state.NUMBER)
                Tokens.add(new Token(Token.tokenType.INTEGER, m_Accumulator));
            if(m_CurrentState == state.IDENTIFIER)
            {
                if (m_KnownWords.containsKey(m_Accumulator))
                {
                    Tokens.add(new Token(m_KnownWords.get(m_Accumulator)));
                    m_Accumulator = "";
                    m_CurrentState = state.NONE;
                }
                else
                {
                    Tokens.add(new Token(Token.tokenType.IDENTIFIER, m_Accumulator));
                    m_Accumulator = "";
                    m_CurrentState = state.NONE;
                }
            }
        }
        if(m_CurrentState != state.COMMENT)
        {
            m_Accumulator = "";
            m_CurrentState = state.NONE;
        }

        Tokens.add(new Token(Token.tokenType.ENDOFLINE)); //after iterating thru the passed string for tokens we append a ENDOFLINE token to Arraylist Tokens
        m_LineNumber++; //increment the line number at the end of a lex call 

    }


    // i couldnt figure out how to repeat my switch with the same char like when hitting
    // a unexpected char in NUMBER state where u emit a token but then want to switch on the same char
    // without consuming it but everytime i break, the for loop goes to the next char so i externalized the 
    // logic so i can effectively go back to the NONE case without consuming the char

    private void noneStateLogic (char p_InputChar) throws Exception
    {
        if (Character.isLetter(p_InputChar))
        {
            m_Accumulator += p_InputChar;
            m_CurrentState = state.IDENTIFIER;
        }
        else if(p_InputChar == '.')
        {
            m_CurrentState = state.NUMBERDOT;
            m_Accumulator += ".";
        }
        else if (p_InputChar  == '(' || p_InputChar == ')')
        {
            makeParamToken(p_InputChar);
        }
        else if (Character.isDigit(p_InputChar))
        {
            m_CurrentState = state.NUMBER;
            m_Accumulator += p_InputChar;
        }
        else if (p_InputChar  == '{')
        {
            m_CurrentState = state.COMMENT;
        }
        else if (p_InputChar  == '[' || p_InputChar == ']')
        {
            makeIndexToken(p_InputChar);
        }
        else if (p_InputChar  == '"')
        {
            m_CurrentState = state.STRINGLITERAL;
        }
        else if (p_InputChar  == '\'')
        {
            m_CurrentState = state.CHARACTERLITERAL;
        }
        else if (p_InputChar  == ',')
        {
            Tokens.add(new Token(Token.tokenType.COMMA));
        }
        else if (p_InputChar  == ';')
        {
            Tokens.add(new Token(Token.tokenType.SEMICOLON));
        }
        else if (p_InputChar  == '/' || p_InputChar  == '-'  || p_InputChar  == '*' || p_InputChar  == '+' || p_InputChar  == '='  || p_InputChar  == '<' || p_InputChar  == '>' || p_InputChar  == ':' )
        {
            m_CurrentState = state.OPERATORS;
            m_Accumulator += p_InputChar;
        } 
        else if (p_InputChar  == ' '){}                       
        else
            throw new SyntaxErrorException("Syntax Error: lexed unexcepted character:\"" + p_InputChar + "\", on line: " + m_LineNumber);
    }

    private void makeParamToken (char p_InputChar) // makes current param token if its opening or closing
    {
        if (p_InputChar  == ')')
        {
            Tokens.add(new Token(Token.tokenType.ENDPARAMS));
        }
        if (p_InputChar  == '(')
        {
            Tokens.add(new Token(Token.tokenType.STARTPARAMS));
        }
        m_Accumulator = "";
        m_CurrentState = state.NONE;

    }

    private void makeIndexToken (char p_InputChar) // makes current index token if its opening or closing
    {
        if (p_InputChar  == ']')
        {
            Tokens.add(new Token(Token.tokenType.ENDINDEX));
        }
        if (p_InputChar  == '[')
        {
            Tokens.add(new Token(Token.tokenType.STARTINDEX));
        }
        m_Accumulator = "";
        m_CurrentState = state.NONE;
    }

    //makes proper number of indent or dedent tokens
    public void indentationAdjust(int p_CurrentLineIndents)
    {
        int indentDifference = p_CurrentLineIndents - m_IndentationLevel;
        while(indentDifference > 0)
        {
            Tokens.add(new Token(Token.tokenType.INDENT));
            indentDifference--;
            m_IndentationLevel++;
        }
        while(indentDifference < 0)
        {
            Tokens.add(new Token(Token.tokenType.DEDENT));
            indentDifference++;
            m_IndentationLevel--;
        }
    }

    //fill map with keywords
    private void fillKnownWords() //commenting out built in functions so parser will parse them as identifiers and call parse functionNode on them
    {
        m_KnownWords.put("while", Token.tokenType.WHILE);
        m_KnownWords.put("for", Token.tokenType.FOR);
        m_KnownWords.put("from", Token.tokenType.FROM);
        m_KnownWords.put("if", Token.tokenType.IF);
        m_KnownWords.put("elsif", Token.tokenType.ELSIF);
        m_KnownWords.put("define", Token.tokenType.DEFINE);
        m_KnownWords.put("variables", Token.tokenType.VARIABLES);  
        m_KnownWords.put("var", Token.tokenType.VAR);
        m_KnownWords.put("integer", Token.tokenType.INTEGER);
        //m_KnownWords.put("write", Token.tokenType.WRITE);
        //m_KnownWords.put("read", Token.tokenType.READ);
        m_KnownWords.put("to", Token.tokenType.TO);
        m_KnownWords.put("not", Token.tokenType.NOT);
        m_KnownWords.put("and", Token.tokenType.AND);
        m_KnownWords.put("or", Token.tokenType.OR);
        m_KnownWords.put("constants", Token.tokenType.CONSTANTS);
        m_KnownWords.put("then", Token.tokenType.THEN);
        m_KnownWords.put("mod", Token.tokenType.MOD);
        m_KnownWords.put("array", Token.tokenType.ARRAY);
        m_KnownWords.put("boolean", Token.tokenType.BOOLEAN);
        m_KnownWords.put("else", Token.tokenType.ELSE);
        m_KnownWords.put("real", Token.tokenType.REAL);
        m_KnownWords.put("string", Token.tokenType.STRING);
        m_KnownWords.put("character", Token.tokenType.CHARACTER);
        m_KnownWords.put("repeat", Token.tokenType.REPEAT);
        m_KnownWords.put("until", Token.tokenType.UNTIL);
        m_KnownWords.put("of", Token.tokenType.OF);
        m_KnownWords.put("left", Token.tokenType.LEFT);
        m_KnownWords.put("right", Token.tokenType.RIGHT);
        //m_KnownWords.put("subString", Token.tokenType.SUBSTRING);
        //m_KnownWords.put("squareRoot", Token.tokenType.SQUAREROOT);
        //m_KnownWords.put("getRandom", Token.tokenType.GETRANDOM);
        //m_KnownWords.put("integerToReal", Token.tokenType.INTEGERTOREAL);
        //m_KnownWords.put("realToInteger", Token.tokenType.REALTOINTEGER);
        //m_KnownWords.put("true", Token.tokenType.INTEGERTOREAL);
        //m_KnownWords.put("false", Token.tokenType.REALTOINTEGER);
        //m_KnownWords.put("start", Token.tokenType.START);
        //m_KnownWords.put("end", Token.tokenType.END);
        
    }

    //called at end of file after last lex call to make sure the last state is none otherwise it throws
    public void eofState() throws Exception
    {
        if(m_CurrentState == state.COMMENT) throw new SyntaxErrorException("Syntax Error: Comment never terminated on line: " + m_LineNumber);
    }

}
