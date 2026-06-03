package MathEngine.Parser;

import MathEngine.Lexer.Token;
import MathEngine.Lexer.Value;

public class AstNode {
    public Value value;
    public Token type;
    public AstNode left;
    public AstNode right;
    public AstNode(Value v,Token t){
        this.value = v;
        this.type = t;
        this.left = null;
        this.right = null;   
    }
}
