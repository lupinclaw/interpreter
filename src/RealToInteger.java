import java.util.ArrayList;

public class RealToInteger extends FunctionNode
{

    public RealToInteger()
    {
        super("RealToInteger");
    }

    public void execute(ArrayList<InterpreterDataType> p_Params) throws SyntaxErrorException
    {
        RealDataType passedVal = null;
        IntegerDataType varReturnParam = null;
        for(InterpreterDataType index : p_Params)
        {
            if(index.getClass().getName() == "RealDataType")
                passedVal = (RealDataType)index;
            if(index.getClass().getName() == "IntegerDataType")
                varReturnParam = (IntegerDataType)index;
        }
        if(passedVal != null)
        {
            varReturnParam = new IntegerDataType((int)passedVal.getValue());
        }
        else
            throw new SyntaxErrorException("Must pass a type: real, to RealToInteger method");
        
    }

}
