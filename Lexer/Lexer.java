package MathEngine.Lexer;
import java.util.regex.Pattern;


public class Lexer {
    public static boolean isChar(char s){
        if((s.charAt(i) > 'a' && s.charAt(i) < 'A')) return true;
        return false;
    }
    public static boolean isDigit(char s){
        if(Pattern.matches("\\d",Character.toString(s))) return true;
        return false;
    }
    public LexerNode line_lexer(String s){
        int n = s.length();
        int i = 0;
        LexerNode lexer_output = null;
        LexerNode tail = null;
        while(i < n){
            LexerNode node = new LexerNode();
            if(i < n && s.charAt(i) == ' '){
                i++;
                continue;
            }
            if(isDigit(s.charAt(i))){
                double number = 0.0;
                while(i < n && isDigit(s.charAt(i))){
                    number = number * 10 + Integer.parseInt(Character.toString(s.charAt(i)));
                    i++;
                }
                if(i < n && s.charAt(i) == '.'){
                    double fract = 0.1;
                    i++;
                    while(i < n && isDigit(s.charAt(i))){
                        number += fract*Integer.parseInt(Character.toString(s.charAt(i)));
                        fract /= 10;
                        i++;
                    }
                }
                Value v = new Value(number);
                node.type = Token.NUMBER;
                node.value = v;
            }
            else if(i < n && s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '/' || s.charAt(i) == '*' || s.charAt(i) == '^' || s.charAt(i) == '%'){
                char op = s.charAt(i);
                Value v = new Value(op);
                node.type = Token.OPERATOR;
                node.value = v;
                i++;
            }
            else if(i < n && s.charAt(i) == '('){
                char op = s.charAt(i);
                Value v = new Value(op);
                node.type = Token.L_PAREN;
                node.value = v;
                i++;
            }
            else if(i < n && s.charAt(i) == ')'){
                char op = s.charAt(i);
                Value v = new Value(op);
                node.type = Token.R_PAREN;
                node.value = v;
                i++;
            }else if(i < n && isChar(s)){
                String identifierName = "";
                while(i < n && isChar(s)){
                    identifierName += s;
                    i++;
                }
                Value v = new Value(identifierName);
                node.type = Token.IDENTIFIER;
            }
            else{
                throw new java.lang.RuntimeException("Invalid token found");
            }
            if(lexer_output == null){
                lexer_output = node;
                tail = node;
            }else{
                tail.next = node;
                tail = node;
            }
        }
        return lexer_output;
    }
}
