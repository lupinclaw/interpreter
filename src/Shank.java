import java.io.File;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

//takes in a file name of a .shank file and lexes it.
public class Shank 
{
    public static void main(String[] args) throws Exception 
    {
        //validate input
        if (args.length > 1)
        {
            System.out.println("Error: input is not a valid filename!");
            System.exit(0);
        }

        System.out.println(args[0]);

        //read in all lines of the input file with the passed in file name 
        //and turn it into a list of string, with each string being a line from the file
        String filename = (args[0]);  //(add .shank extention to the passed filename) <-- not doing this anymore becuase grade on parser 2 said not to, lemmeknow if this changes tho cuz it wasnt brought up before
        File file = new File(filename); // create File object with that filename
        String path = file.getAbsolutePath(); // get its absolute path
        Path myPath = Paths.get(path); // make a Path object with the absolute path  
        List <String> lines = Files.readAllLines(myPath, StandardCharsets.UTF_8); // finally make a List of String, each string being a line from the file 

        //create a lexer object
        Lexer lexer = new Lexer();

        //loop to lex each line of input into tokens
        //throws error, showing what caused the error, if its a lexing error it shows what character caused the error and prints all current tokens
        try {
            for (String p_index : lines)
            { 
                lexer.lex(p_index);
            }
            lexer.indentationAdjust(0); //dedent back to zero
            lexer.eofState(); //throws if EOF in non-NONE state
        }
        catch(Exception p_exception)
        {
            System.out.println(p_exception.getMessage());
            System.out.println("Made: " + lexer.Tokens.size() + " tokens before error: ");
            System.out.println(lexer.Tokens);  // shows all tokens made before the error was thrown
            System.exit(0);
        }

        //print all tokens if whole file is sucessfully lexed
        System.out.println("Whole file succfeully lexed, Tokens are:");
        for(Token p_index : lexer.Tokens)
        {
            System.out.println(p_index.toString());
        }

        //parsing
        Parser parser = new Parser(lexer.Tokens);
        ProgramNode theProgram = parser.parse();
        System.out.println(theProgram.toString());
        //semantic analysis
        SemanticAnalysis semAnal = new SemanticAnalysis();
        semAnal.CheckAssignments(theProgram);
        //interpreting
        Interpreter interpreter = new Interpreter(theProgram);

    }   
    
    

    
}


