public abstract class StatementNode extends Node{
    //value held my node
    private Node m_Value;
       
    //value accsessor
    public Node getValue()
    {
        return m_Value;
    }

    @Override
    public String toString() {
        return ("StatementNode(" + m_Value.toString() + ")");
    }
    
}
