import java.util.ArrayList;

//represents a parsed 'function' witha  node 
public class FunctionNode extends Node{

//value held my node
private String m_Name;
private ArrayList<StatementNode> m_Statements;
private Node[] m_Parameters;
private Node[] m_Constants;
private Node[] m_Variables;
    
//contructor
public FunctionNode(String p_Name, Node[] p_Parameters, Node[] p_Constants, Node[] p_Variables, ArrayList<StatementNode> p_Statements)
{
    m_Name = p_Name;
    m_Statements = p_Statements;
    m_Parameters = p_Parameters;
    m_Constants = p_Constants;
    m_Variables = p_Variables;
}
public FunctionNode(String p_Name) //for the built in subs
{
    m_Name = p_Name;
}

//value accsessors
public String getName()
{
    return m_Name;
}
public ArrayList<StatementNode> getStatments()
{
    return m_Statements;
}
public Node[] getParameters()
{
    return m_Parameters;
}
public Node[] getConstants()
{
    return m_Constants;
}
public Node[] getVariables()
{
    return m_Variables;
}

//to string
@Override
public String toString() 
{
    String output = ("\n\tFunctionNode(\n\t\tName: " + m_Name + ",\n\t\tParams: ");

    if(m_Parameters != null)
    {
        for(int i = 0 ; i < m_Parameters.length ; i ++)
        {
            if(m_Parameters[i] != null)
                output += (" " + m_Parameters[i].toString());
        }
    }

    output += ",\n\t\tConstants: ";

    if(m_Constants != null)
    {
        for(int i = 0 ; i < m_Constants.length ; i ++)
        {
            if(m_Parameters[i] != null)
                output += (" " + m_Constants[i].toString());
        }
    }

    output += ",\n\t\tVariables: ";
    
    if(m_Parameters != null)
    {
        for(int i = 0 ; i < m_Variables.length ; i ++)
        {
            if(m_Parameters[i] != null)
                output += (" " + m_Variables[i].toString());
        }
    }

    output += ",\n\t\tStatements:";

    if(m_Statements != null)
    {
        for(StatementNode index : m_Statements)
        {
            output += (" " + index.toString() + ",");
        }
    }

    return (output + "\n\t)");

}

public boolean isVariadic ()
{
    return false;
}

}



 