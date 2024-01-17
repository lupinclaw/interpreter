import java.util.*;

public class RepeatNode extends StatementNode {
    private BooleanCompareNode m_Condition;
    private ArrayList<StatementNode> m_Statements;

    public RepeatNode(BooleanCompareNode p_condition, ArrayList<StatementNode> p_statements) {
        m_Condition = p_condition;
        m_Statements = p_statements;
    }

    public BooleanCompareNode getCondition() 
    {
        return m_Condition;
    }
    public ArrayList<StatementNode> getStatements() 
    {
        return m_Statements;
    }

    @Override
    public String toString() 
    {
        String output = "RepeatNode(";
        if (m_Statements != null) 
        {
            boolean first = true;
            for (StatementNode node : m_Statements) {
                if (!first) {
                    output += ", ";
                }
                output += node.toString();
                first = false;
            }
        }
        output += ")";
        return output;
    }
}