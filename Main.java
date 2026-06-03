package MathEngine;
import java.util.Scanner;
import MathEngine.Executor.Executor;
import MathEngine.Lexer.*;
import MathEngine.Parser.AstNode;
import MathEngine.Parser.Parser;

class Main{
    public static void main(String args[]){
        Style style = new Style();
        style.printTitle();
        Scanner inputScanner = new Scanner(System.in);
        String input = "";
        Lexer lexer = new Lexer();
        Parser parser;
        while(!input.equals("exit")){
            System.out.print(">>> ");
            input = inputScanner.nextLine();
            if(input.equals("exit")) break;
            try{
                LexerNode lexer_output = lexer.line_lexer(input);
                parser = new Parser(lexer_output);
                AstNode ast = parser.parse();
                Executor exec = new Executor();
                exec.build_bytecode(ast);
                double answer = exec.execute_instructions();
                System.out.println(answer);
            }catch(RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
        inputScanner.close();
    }
}