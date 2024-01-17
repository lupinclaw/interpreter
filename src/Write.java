import java.util.ArrayList;

public class Write extends FunctionNode
{
    public Write()
    {
        super("Write");
    }

    public void execute(ArrayList<InterpreterDataType> p_Params) throws SyntaxErrorException
    {
        for(InterpreterDataType index : p_Params)
        {
            System.out.println(index.toString());
        }
    }

    @Override
    public boolean isVariadic ()
    {
        return true;
    }
    
}
