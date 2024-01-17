import java.util.*;

public class WhileNode extends StatementNode 
{
    //memebrs
    private BooleanCompareNode m_condition;
    private ArrayList<StatementNode> m_statements;

    //constructors
    public WhileNode(BooleanCompareNode p_condition, ArrayList<StatementNode> p_statements) 
    {
        m_condition = p_condition;
        m_statements = p_statements;
    }

    //getters
    public BooleanCompareNode getCondition() 
    {
        return m_condition;
    }
    public ArrayList<StatementNode> getStatements() 
    {
        return m_statements;
    }

    @Override
    public String toString() 
    {   
        String output = "WhileNode(Conditions: " + m_condition.toString() + " Statements: ";
        for(StatementNode state : m_statements)
        {
            output += state.toString();
        }
        output += ")";
        return output;
    }

}