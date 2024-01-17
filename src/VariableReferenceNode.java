//node calss def for when we need to store a reference variable

public class VariableReferenceNode extends Node
{
    //memebrs
    private String m_Name;
    private Node m_isArray = null;

    //contrcutors
    public VariableReferenceNode(String p_Name)
    {
        this.m_Name = p_Name;
    }

    public VariableReferenceNode(String p_Name, Node p_arrayNode)
    {
        this.m_Name = p_Name;
        this.m_isArray = p_arrayNode;
    }

    //accesors
    public String getName() {
        return m_Name;
    }

    public Node getArrayIndexExpression() {
        return m_isArray;
    }


    public String toString()
    {
        if (m_isArray != null)
        {
            return ("ReferenceVariableNode(" + m_Name + "[" + m_isArray.toString() + "])");
        }
        else
        {
            return ("ReferenceVariableNode(" + m_Name + ")");
        }

    }



    
}
