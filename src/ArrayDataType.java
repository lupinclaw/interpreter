public class ArrayDataType extends InterpreterDataType
{
    private InterpreterDataType m_ArrayValue[];

    public ArrayDataType (InterpreterDataType[] p_InputIDT)
    {
        m_ArrayValue = p_InputIDT;
    }

    public InterpreterDataType[] getValue()
    {
        return m_ArrayValue;
    }
    public InterpreterDataType getIndexValue(int p_index)
    {
        return m_ArrayValue[p_index];
    }

    public String ToString()
    {
        return m_ArrayValue.toString();
    }

    public void FromString(String p_Input)
    {}  
    
}
