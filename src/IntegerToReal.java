import java.util.ArrayList;

public class IntegerToReal extends FunctionNode
{
    public IntegerToReal()
    {
        super("IntegerToReal");
    }

    public void execute(ArrayList<InterpreterDataType> p_Params) throws SyntaxErrorException
    {
        RealDataType varReturnParam = null;
        IntegerDataType passedVal  = null;
        for(InterpreterDataType index : p_Params)
        {
            if(index.getClass().getName() == "RealDataType")
                varReturnParam = (RealDataType)index;
            if(index.getClass().getName() == "IntegerDataType")
                passedVal = (IntegerDataType)index;
        }
        if(passedVal != null)
        {
            varReturnParam = new RealDataType((float)passedVal.getValue());
        }
        else
            throw new SyntaxErrorException("Must pass a type: integer, to IntegerToReal method");
        
    }
    
}
