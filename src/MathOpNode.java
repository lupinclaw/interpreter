//node which representaetns a math operation
public class MathOpNode extends Node
{
    //enum of math types and values
    public enum mathops {PLUS, MOD, MINUS, SUBTRACT, MULTIPLY, INTDIVITION}
    Node m_RightNode;
    Node m_LeftNode;
    mathops m_MathOpType;

    //contructor
    public MathOpNode( Node p_LeftNode, Node p_RightNode, mathops p_MathOpType)
    {
        m_MathOpType = p_MathOpType;
        m_LeftNode = p_LeftNode;
        m_RightNode = p_RightNode;
    }

    //string
    @Override
    public String toString()
    {
        String leftStr = "";
        String rightStr = "";
    
        if (m_LeftNode != null) 
        {
            leftStr = m_LeftNode.toString();
        }
        if (m_RightNode != null)
        {
            rightStr = m_RightNode.toString();
        }
        return ("MathOpNode(" + m_MathOpType + ","+ leftStr + "," + rightStr + ")");
    }
}
