import java.util.*;

public class FunctionCallNode extends StatementNode {
    private String m_Name;
    private ArrayList<ParameterNode> m_Parameters;

    public FunctionCallNode(String p_Name, ArrayList<ParameterNode> p_Parameters)
    {
        m_Name = p_Name;
        m_Parameters = p_Parameters;
    }
    public String getName()
    {
        return m_Name;
    }
    public ArrayList<ParameterNode> getParams()
    {
        return m_Parameters;
    }

    @Override
    public String toString() 
    {
        String output = m_Name + "(";
        if (m_Parameters != null) 
        {
            boolean first = true;
            for (ParameterNode node : m_Parameters) {
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
