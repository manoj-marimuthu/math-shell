package MathEngine.Parser;

import MathEngine.Lexer.LexerNode;
import MathEngine.Lexer.Token;


public class Parser {
    LexerNode current;
    public Parser(LexerNode node){
        this.current = node;
    }
    public void consume(){
        if(current != null){
            this.current = this.current.next;
        }
    }
    public AstNode parseAtom(){
        AstNode node = null;
        if(this.current != null && this.current.type == Token.NUMBER){
            node = new AstNode(current.value,current.type);
            consume();
            return node;
        }
        else if(this.current != null && this.current.type == Token.L_PAREN){
            consume();
            node = parseAddSub();
            if(this.current.type == Token.R_PAREN){
                consume();
            }else{
                throw new java.lang.RuntimeException("Parenthesis is not closed correctly");
            }
            return node;
        }
        else{
            throw new java.lang.RuntimeException("Invalid token found by Parser");
        }
    }
    public AstNode parseExp(){
        AstNode lhs = parseAtom();
        while(this.current != null && this.current.type == Token.OPERATOR && this.current.value.operator == '^'){
            AstNode opNode = new AstNode(this.current.value, this.current.type);
            consume();
            AstNode rhs = parseAtom();
            opNode.left = lhs;
            opNode.right = rhs;
            lhs = opNode;
        }
        return lhs;
    }
    public AstNode parseMulDiv(){
        AstNode lhs = parseExp();
        while(this.current != null && this.current.type == Token.OPERATOR && (this.current.value.operator == '*' || this.current.value.operator == '/' || this.current.value.operator == '%') && this.current.type != Token.R_PAREN){
            AstNode opNode = new AstNode(this.current.value, this.current.type);
            consume();
            AstNode rhs = parseExp();
            opNode.left = lhs;
            opNode.right = rhs;
            lhs = opNode;
        }
        return lhs;
    }
    public AstNode parseAddSub(){
        AstNode lhs = parseMulDiv();
        while(this.current != null && this.current.type == Token.OPERATOR && (this.current.value.operator == '+' || this.current.value.operator == '-') && this.current.type != Token.R_PAREN){
            AstNode opNode = new AstNode(this.current.value, this.current.type);
            consume();
            AstNode rhs = parseMulDiv();
            opNode.left = lhs;
            opNode.right = rhs;
            lhs = opNode;
        }
        return lhs;
    }
    public AstNode parse(){
        AstNode ast = parseAddSub();
        return ast;
    }    
}
