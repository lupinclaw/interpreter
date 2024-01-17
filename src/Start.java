import java.util.ArrayList;

public class Start extends FunctionNode
{
    public Start()
    {
        super("Start");
    }

    public void execute(ArrayList<InterpreterDataType> p_Params) throws SyntaxErrorException
    {
        if(p_Params.size() == 2)
        {
            InterpreterDataType varReturnParam = p_Params.get(1);  //the var variable thats passed
            ArrayDataType passedArray = (ArrayDataType)p_Params.get(0); //the passed array
            varReturnParam = passedArray.getIndexValue(0); //the start
        }
        else
            throw new SyntaxErrorException("Error: invalid paramters passed to function: Start");  
    }
    
}
