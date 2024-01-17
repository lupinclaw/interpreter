public class RealDataType extends InterpreterDataType
{
    private float m_RealValue;

    public RealDataType ()
    {
        //null constructor
    }
    public RealDataType (RealNode p_InputASTNode) //given ast node contrustor
    {
        m_RealValue = p_InputASTNode.getValue();
    }
    public RealDataType (float p_Input)
    {
        m_RealValue = p_Input;
    }
    public float getValue()
    {
        return m_RealValue;
    }

    public String ToString()
    {
        return Float.toString(m_RealValue);
    }

    public void FromString(String p_Input)
    {
        Float.parseFloat(p_Input);
    } 
    
}
