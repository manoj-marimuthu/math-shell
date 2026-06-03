package MathEngine.Executor;

import MathEngine.Lexer.Token;
import MathEngine.Parser.AstNode;

import java.util.ArrayList;
import java.util.Stack;

public class Executor {
    public Executor(){}
    ArrayList<Instruction> s = new ArrayList<>();
    Stack<Double> operand_stack = new Stack<>();
    public Instruction convert_to_instruction(AstNode node){
        InstructionType it = null;
        if(node.type == Token.NUMBER){
            it = InstructionType.PUSH;
        }else if(node.type == Token.OPERATOR){
            if(node.value.operator == '+'){
                it = InstructionType.ADD;
            }else if(node.value.operator == '-'){
                it = InstructionType.SUB;
            }else if(node.value.operator == '*'){
                it = InstructionType.MUL;
            }else if(node.value.operator == '/'){
                it = InstructionType.DIV;
            }else if(node.value.operator == '%'){
                it = InstructionType.MOD;
            }else{
                it = InstructionType.POW;
            }
        }
        if(it != null){
            Instruction i = new Instruction(it,node.value);
            return i;
        }else{
            return null;
        }
    }
    public void build_bytecode(AstNode ast){
        if(ast.left != null) build_bytecode(ast.left);
        if(ast.right != null) build_bytecode(ast.right);
        if(ast != null){
            Instruction astInstruction = convert_to_instruction(ast);
            s.add(astInstruction);
        }
    }
    public double execute_instructions(){
       try{
        for(Instruction ins : s){
                if(ins.type == InstructionType.PUSH){
                    operand_stack.push(ins.value.number);
                }else if(ins.type == InstructionType.ADD){
                    double n1 = operand_stack.pop();
                    double n2 = operand_stack.pop();
                    operand_stack.push(n2 + n1);
                }
                else if(ins.type == InstructionType.SUB){
                    double n1 = operand_stack.pop();
                    double n2 = operand_stack.pop();
                    operand_stack.push(n2 - n1);
                }
                else if(ins.type == InstructionType.MUL){
                    double n1 = operand_stack.pop();
                    double n2 = operand_stack.pop();
                    operand_stack.push(n2 * n1);
                }
                else if(ins.type == InstructionType.DIV){
                    double n1 = operand_stack.pop();
                    double n2 = operand_stack.pop();
                    operand_stack.push(n2 / n1);
                }
                else if(ins.type == InstructionType.MOD){
                    double n1 = operand_stack.pop();
                    double n2 = operand_stack.pop();
                    operand_stack.push(n2 % n1);
                }else if(ins.type == InstructionType.POW){
                    double n1 = operand_stack.pop();
                    double n2 = operand_stack.pop();
                    operand_stack.push(Math.pow(n2, n1));
                }
            }
        }
        catch(Exception e){
            throw new java.lang.RuntimeException("Invalid expression detected by Executor");
        }
        return operand_stack.pop();
    }
}
