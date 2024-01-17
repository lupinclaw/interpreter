public class BooleanCompareNode extends Node
{
    //memebers
    public enum boolOperation {EQUAL, NOTEQUAL, LESSTHAN, GREATERTHAN, LESSTHANEQUAL, GREATERTHANEQUAL}
    private Node m_LeftSide;
    private Node m_RightSide;
    private boolOperation m_Operation;

    //constructor
    public BooleanCompareNode(Node p_LeftSide, Node p_RightSide, boolOperation p_Operation) 
    {
        this.m_LeftSide = p_LeftSide;
        this.m_RightSide = p_RightSide;
        this.m_Operation = p_Operation;
    }

    //accsesors
    public Node getLeft() 
    {
        return m_LeftSide;
    }
    public Node getRight() 
    {
        return m_RightSide;
    }
    public boolOperation getOperator() 
    {
        return m_Operation;
    }

    @Override
    public String toString() 
    {
        return ("BooleanCompareNode(" + m_LeftSide.toString() + "," + m_Operation.toString() + "," + m_RightSide.toString() + ")");
    }
    
}
