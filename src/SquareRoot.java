import java.util.ArrayList;
import java.lang.Math;

public class SquareRoot extends FunctionNode
{
    public SquareRoot()
    {
        super("SquareRoot");
    }

    public void execute (ArrayList<InterpreterDataType> p_Params) throws SyntaxErrorException
    {
        RealDataType varParamValue = (RealDataType)p_Params.get(1), passedValue = (RealDataType)p_Params.get(0);
        varParamValue = new RealDataType((float) Math.sqrt(passedValue.getValue()));
    }
    
}
