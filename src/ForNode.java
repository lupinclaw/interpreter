import java.util.*;


public class ForNode extends StatementNode 
{
    //members
    private Node m_From;
    private Node m_To;
    private Token m_Indexer;
    private ArrayList<StatementNode> m_Statements;

    //constructrs
    public ForNode(Node p_from, Node p_to, ArrayList<StatementNode> p_statements) 
    {
        m_From = p_from;
        m_To = p_to;
        m_Statements = p_statements;
    }
    public ForNode(Node p_from, Node p_to, ArrayList<StatementNode> p_statements, Token p_indexer) 
    {
        m_From = p_from;
        m_To = p_to;
        m_Statements = p_statements;
        m_Indexer = p_indexer;
    }
    // Getters and setters
    public Node getFrom()
    {
        return m_From;
    }
    public Node getTo()
    {
        return m_To;
    }
    public String getIndexer()
    {
        return m_Indexer.getTokenValue();
    }
    public ArrayList<StatementNode> getStatements()
    {
        return m_Statements;
    }

    @Override
    public String toString() 
    {
        String output = "ForNode(" + m_Indexer.getTokenValue() + " from: " + m_From.toString() + " to: " + m_To.toString();
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
