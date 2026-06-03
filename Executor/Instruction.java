package MathEngine.Executor;

import MathEngine.Lexer.Value;

public class Instruction {
    InstructionType type;
    Value value;
    public Instruction(InstructionType type,Value v){
        this.type = type;
        this.value = v;
    }
}
