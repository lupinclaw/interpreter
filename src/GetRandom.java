import java.util.ArrayList;
import java.util.Random;

public class GetRandom extends FunctionNode
{
    public GetRandom()
    {
        super("GetRandom");
    }

    public void execute(ArrayList<InterpreterDataType> p_Params) throws SyntaxErrorException
    {
        IntegerDataType varReturnParam  = null;
        for(InterpreterDataType index : p_Params)
        {
            if(index.getClass().getName() == "IntegerDataType")
                varReturnParam = (IntegerDataType)index;
        }
        if(varReturnParam != null)
        {
            Random rand = new Random();
            varReturnParam = new IntegerDataType(rand.nextInt());
        }
        else
            throw new SyntaxErrorException("Error: GetRandom");
        
    }

}
