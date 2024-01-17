import java.util.ArrayList;
import java.util.Scanner;

public class Read extends FunctionNode
{
    public Read()
    {
        super("Read");
    }

    public void execute(ArrayList<InterpreterDataType> p_Params) throws SyntaxErrorException
    {
        Scanner notScanF = new Scanner(System.in);
        String[] delimitedInput = notScanF.nextLine().split(" "); // input delimted by space as per doc
        
        for(int i = 0; i < p_Params.size(); i++) //what happens when number of vars != number of delimited inputs?
        {
            p_Params.get(i).FromString(delimitedInput[i]); 
        }
    }

    @Override
    public boolean isVariadic ()
    {
        return true;
    }

}
