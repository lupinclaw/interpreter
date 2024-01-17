public class CharNode extends Node{
     //value held my node
     private char m_Value;
     private String m_Name;
     
     //contructor
     public CharNode(char p_value, String p_Name)
     {
         m_Value = p_value;
         m_Name = p_Name;
     }
    
    //field accsessor
    public char getValue()
    {
        return m_Value;
    }
    public String getName()
    {
        return m_Name;
    }

    @Override
    public String toString() {
        return ("CharNode("+m_Value+")");
    }
    
}
