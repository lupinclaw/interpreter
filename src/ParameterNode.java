import java.util.*;

public class ParameterNode extends Node 
{
    private VariableReferenceNode m_VariableReference;
    private Node m_Expression;

    public ParameterNode(VariableReferenceNode p_variableReference) 
    {
        m_VariableReference = p_variableReference;
    }
    public ParameterNode(Node p_expression) 
    {
        m_Expression = p_expression;
    }
    public VariableReferenceNode getVariableRef()
    {
        return m_VariableReference;
    }

    @Override
    public String toString() 
    {
        String output = "ParameterNode(";
        if (m_VariableReference != null) 
        {
            output += m_VariableReference.toString();
        }
        if (m_Expression != null) 
        {
            output += m_Expression.toString();
        }
        output += ")";
        return output;
    }
}