public class StringNode extends Node{
    //value held my node
    private String m_Value;
    private String m_Name;
    
    //contructor
    public StringNode(String p_value, String p_Name)
    {
        m_Value = p_value;
        m_Name = p_Name;
    }
    
    //field accsessor
    public String getValue()
    {
        return m_Value;
    }
    public String getName()
    {
        return m_Name;
    }

    @Override
    public String toString() {
        return ("StringNode("+m_Value+")");
    }
    
}
