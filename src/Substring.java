import java.util.ArrayList;

public class Substring extends FunctionNode
{

    public Substring()
    {
        super("Substring");
    }

    public void execute(ArrayList<InterpreterDataType> p_Params) throws SyntaxErrorException
    {
        if(p_Params.get(0).getClass().getName() == "StringDataType" && p_Params.get(1).getClass().getName() == "IntegerDataType" && p_Params.get(2).getClass().getName() == "IntegerDataType"&& p_Params.get(3).getClass().getName() == "StringDataType")
        {
            String substring = ((StringDataType)p_Params.get(0)).getValue();
            int index = ((IntegerDataType)p_Params.get(1)).getValue();
            int lengthOfSub = ((IntegerDataType)p_Params.get(2)).getValue();
            p_Params.get(2).FromString(substring.substring(index, (index + lengthOfSub))); //right side is exlusive so we dont need to minus 1 from index+length iirc
        }
        else
            throw new SyntaxErrorException("Error: invalid params for function: Right");
    }
    
}
