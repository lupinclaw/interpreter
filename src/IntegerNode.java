public class IntegerNode extends Node
{
    //value held my node
    private int m_Value;

     //contructor
     public IntegerNode(int p_value)
     {
         m_Value = p_value;
     }
    
    //value accsessor
    public int getValue()
    {
        return (m_Value);
    }

    @Override
    public String toString() {
        return ("IntegerNode("+m_Value+")");
    }



}
