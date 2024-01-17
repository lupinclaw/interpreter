public class BooleanDataType extends InterpreterDataType
{
    private boolean m_BoolValue;

    public BooleanDataType ()
    {
        //null constructor
    }
    public BooleanDataType (BoolNode p_InputASTNode) //given ast node contrustor
    {
        m_BoolValue = p_InputASTNode.getValue();
    }
    public BooleanDataType (boolean p_Input)
    {
        m_BoolValue = p_Input;
    }
    public boolean getValue()
    {
        return m_BoolValue;
    }

    public String ToString()
    {
        return Boolean.toString(m_BoolValue);
    }

    public void FromString(String p_Input)
    {
        Boolean.parseBoolean(p_Input);
    } 
    
}
