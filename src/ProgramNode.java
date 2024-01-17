//makes a node which represents a whole program
import java.util.*;

public class ProgramNode extends Node{
    //value held my node
    HashMap<String, FunctionNode> m_Functions = new HashMap<String, FunctionNode>();
    
    //contructor(s)
    public ProgramNode(HashMap<String, FunctionNode> p_Functions)
    {
        m_Functions = p_Functions;
        insertBuiltIns();
    }

    public ProgramNode()
    {
        insertBuiltIns();
    }

    //add to the hashmap
    public void addFunctionNode(FunctionNode p_FunctionNode)
    {
        m_Functions.put(p_FunctionNode.getName(), p_FunctionNode);
    }
    
    //value accsessor
    public HashMap<String, FunctionNode> getfunctions()
    {
        return m_Functions;
    }

    //checks to see if a function is defined within our program, used in parser
    public boolean hasFunction(String p_FunctionName)
    {
        if(m_Functions.containsKey(p_FunctionName))
            return true;
        return false;
    }

    //returns defined function node
    public FunctionNode getFunction(String p_FunctionName)
    {
        if(m_Functions.containsKey(p_FunctionName))
            return m_Functions.get(p_FunctionName);
        return null;
    }

    @Override
    public String toString() 
    {
        return ("ProgramNode("+ m_Functions.values() +")");
    }

    public void insertBuiltIns()
    {
        addFunctionNode(new Read());
        addFunctionNode(new Write());
        addFunctionNode(new Left());
        addFunctionNode(new Right());
        addFunctionNode(new Substring());
        addFunctionNode(new SquareRoot());
        addFunctionNode(new GetRandom());
        addFunctionNode(new IntegerToReal());
        addFunctionNode(new RealToInteger());
        addFunctionNode(new Start());
        addFunctionNode(new End());
    }
    
}
