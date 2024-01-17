public class IntegerDataType extends InterpreterDataType 
{
    private int m_IntValue;

    public IntegerDataType ()
    {
        //null constructor
    }
    public IntegerDataType (IntegerNode p_InputASTNode) //given ast node contrustor
    {
        m_IntValue = p_InputASTNode.getValue();
    }
    public IntegerDataType (int p_Input)
    {
        m_IntValue = p_Input;
    }
    public int getValue()
    {
        return m_IntValue;
    }


    public String ToString()
    {
        return Integer.toString(m_IntValue);
    }

    public void FromString(String p_Input)
    {
        Integer.parseInt(p_Input);
    } 

    
}
