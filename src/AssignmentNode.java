public class AssignmentNode extends StatementNode 
{
    //memebrs
    private VariableReferenceNode m_Target;
    private Node m_Value;

    //contructor
    public AssignmentNode(VariableReferenceNode p_Target, Node p_Value) 
    {
        m_Value = p_Value;
        m_Target = p_Target;
        if(m_Value.getClass().getName() == "MathOpNode")
            if(((MathOpNode)m_Value).m_MathOpType == null)
            {
                m_Value = ((MathOpNode)m_Value).m_LeftNode;
            }
    }
    //value accsessor
    public VariableReferenceNode getTarget() 
    {
        return m_Target;
    }
    public Node getValue() 
    {
        return m_Value;
    }

    @Override
    public String toString() 
    {
        if(m_Target != null)
        {
            return ("AssignmentNode(" + m_Target.toString() + " := " + m_Value.toString() + ")");
        }
        return null;
    }
}