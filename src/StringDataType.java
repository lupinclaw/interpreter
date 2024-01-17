public class StringDataType extends InterpreterDataType
{
    private String m_StringValue;

    public StringDataType ()
    {
        //null constructor
    }
    public StringDataType (StringNode p_InputASTNode) //given ast node contrustor
    {
        m_StringValue = p_InputASTNode.getValue();
    }
    public StringDataType (String p_Input)
    {
        m_StringValue = p_Input;
    }
    public String getValue()
    {
        return m_StringValue;
    }

    public String ToString()
    {
        return m_StringValue;
    }

    public void FromString(String p_Input)
    {
        m_StringValue = p_Input;
    }  
}
