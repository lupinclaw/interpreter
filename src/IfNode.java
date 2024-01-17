import java.util.*;

public class IfNode extends StatementNode 
{
    private BooleanCompareNode m_Condition;
    private ArrayList<StatementNode> m_Statements;
    private IfNode m_ElseIfNode;

    public IfNode(BooleanCompareNode p_condition, ArrayList<StatementNode> p_Statements, IfNode p_ElseIfNode) 
    {
        m_Condition = p_condition;
        m_Statements = p_Statements;
        m_ElseIfNode = p_ElseIfNode;
    }

    public ArrayList<StatementNode> getStatements() 
    {
        return m_Statements;
    }
    public IfNode getElseIfNode() 
    {
        return m_ElseIfNode;
    }
    public BooleanCompareNode getCondition() 
    {
        return m_Condition;
    }
    public void setStatements(ArrayList<StatementNode> p_Statements) 
    {
        m_Statements = p_Statements;
    }
    public void setElseIfNode(IfNode p_ElseIfNode) 
    {
        m_ElseIfNode = p_ElseIfNode;
    }

    @Override  
    public String toString() {
        String output = "IfNode(" + m_Condition.toString() + ":";
        for (StatementNode node : m_Statements) 
        {
            output += node.toString() + ",";
        }
        if (m_ElseIfNode != null) 
        {
            output += m_ElseIfNode.toString();
        }
        output += ")";
        return output;
    }
}
