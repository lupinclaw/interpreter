public class VariableNode extends Node{
    //value held my node
    private String m_Name;
    private String m_Type;
    private boolean m_Changeable;
    private Node m_value;
    private int m_from = 0;
    private int m_to = 0;
    private float m_realfrom = 0;
    private float m_realto = 0;
    
    //contructor(s)
    public VariableNode(String p_Name, String p_Type, boolean p_Changeable)
    {
        m_Name = p_Name;
        m_Type = p_Type;
        m_Changeable = p_Changeable;
    }
    public VariableNode(String p_Name, String p_Type, boolean p_Changeable, Node p_value)
    {
        m_Name = p_Name;
        m_Type = p_Type;
        m_Changeable = p_Changeable;
        m_value = p_value;
    }
    public VariableNode(String p_Name, String p_Type, boolean p_Changeable, int p_from, int p_to)
    {
        m_Name = p_Name;
        m_Type = p_Type;
        m_Changeable = p_Changeable;
        m_from = p_from;
        m_to = p_to;
    }
    public VariableNode(String p_Name, String p_Type, boolean p_Changeable, float p_from, float p_to)
    {
        m_Name = p_Name;
        m_Type = p_Type;
        m_Changeable = p_Changeable;
        m_realfrom = p_from;
        m_realto = p_to;
    }
    
    //field accsessors
    public String getName()
    {
        return m_Name;
    }
    public String getType()
    {
        return m_Type;
    }
    public boolean getChangable()
    {
        return m_Changeable;
    }
    public Node getValue()
    {
        return m_value;
    }

    //type set/cast
    public void setType(String p_type)
    {
        this.m_Type = p_type;
    }
    
    @Override
    public String toString() {
        if(m_from != 0 && m_to != 0 || m_realfrom != 0 && m_realto != 0 )
            return ("VariableNode(Type: " + m_Type + " Changable: " + m_Changeable + " Name: "+ m_Name + " from: " + m_from + " to " + m_to + ")");
        if(m_realfrom != 0 && m_realto != 0 )
            return ("VariableNode(Type: " + m_Type + " Changable: " + m_Changeable + " Name: "+ m_Name + " from: " + m_realfrom + " to " + m_realto + ")");
        if(m_value == null )
            return ("VariableNode(Type: " + m_Type + " Changable: " + m_Changeable + " Name: "+ m_Name +")");
        return ("VariableNode(Type: " + m_Type + " Changable: " + m_Changeable + " Name: "+ m_Name + " Value: " + m_value +")");
    }
    
}
