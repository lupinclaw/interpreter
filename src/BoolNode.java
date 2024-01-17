public class BoolNode extends Node{
    //value held my node
    private boolean m_Value;
    
    //contructor
    public BoolNode(boolean p_value)
    {
        m_Value = p_value;
    }
    
    //value accsessor
    public boolean getValue()
    {
        return m_Value;
    }

    @Override
    public String toString() {
        return ("BoolNode("+m_Value+")");
    }
}
