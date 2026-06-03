package MathEngine.Lexer;

public class Value {
        public double number;
        public char operator;
        public String identifier;
        Value(double n){
            this.number = n;
        }
        Value(char op){
            this.operator = op;
        }
        Value(String word,double val){
            this.identifier = word;
            this.number = val;
        }
}
