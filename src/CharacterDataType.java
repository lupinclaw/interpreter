public class CharacterDataType extends InterpreterDataType
{
    private char m_CharValue;

    public CharacterDataType ()
    {
        //null constructor
    }
    public CharacterDataType (CharNode p_InputASTNode) //given ast node contrustor
    {
        m_CharValue = p_InputASTNode.getValue();
    }
    public CharacterDataType (char p_Input)
    {
        m_CharValue = p_Input;
    }
    public char getValue()
    {
        return m_CharValue;
    }

    public String ToString()
    {
        return Character.toString(m_CharValue);
    }

    public void FromString(String p_Input)
    {
        m_CharValue = p_Input.charAt(0);
    }  
    
}
