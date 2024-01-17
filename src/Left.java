import java.util.ArrayList;

public class Left extends FunctionNode
{
    public Left()
    {
        super("Left");
    }

    public void execute(ArrayList<InterpreterDataType> p_Params) throws SyntaxErrorException
    {
        if(p_Params.get(0).getClass().getName() == "StringDataType" && p_Params.get(1).getClass().getName() == "IntegerDataType" && p_Params.get(2).getClass().getName() == "StringDataType")
        {
            String substring = ((StringDataType)p_Params.get(0)).getValue();
            int index = ((IntegerDataType)p_Params.get(1)).getValue();
            p_Params.get(2).FromString(substring.substring(0,index+1)); //plus one to include the index
        }
        else
            throw new SyntaxErrorException("Error: invalid params for function: Right");
    }

}
