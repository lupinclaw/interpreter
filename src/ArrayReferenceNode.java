
public class ArrayReferenceNode extends VariableReferenceNode 
{
    private Node m_Index;

    public ArrayReferenceNode(String p_Name, Node p_Index) 
    {
        super(p_Name);
        m_Index = p_Index;   
    }

    public Node getIndex() 
    {
        return m_Index;
    }

    @Override
    public String toString() 
    {
        String output = "ArrayReferenceNode("+ getName() + "[";
        output = (output + m_Index.toString());
        output += "])";
        return output;
    }
}