package MathEngine.Lexer;

public class LexerNode {
    public Token type;
    public Value value;
    public LexerNode next;
    LexerNode(){
        type = null;
        value = null;
    }
    LexerNode(Token type,Value value){
        this.type = type;
        this.value = value;
    }
    public void display(LexerNode node){
        LexerNode current = node;
        while(current != null){
            if(current.type == Token.NUMBER){
                System.out.println(current.value.number + "->");
            }else{
                System.out.println(current.value.operator + "->");
            }
            current = current.next;
        }
    }
}
