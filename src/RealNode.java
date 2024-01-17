public class RealNode extends Node
{
    //value held my node
    private float m_Value;
    
    //contructor
    public RealNode(float p_value)
    {
        m_Value = p_value;
    }
    
    //value accsessor
    public float getValue()
    {
        return m_Value;
    }

    @Override
    public String toString() {
        return ("RealNode("+m_Value+")");
    }
}
