# Math Shell 

## What ?

An early-stage light-weight REPL interpreted shell written in java that runs mathematical operations.

## Why ?

To solve complex math problems with your keyboard, offering all major mathematical operations ranging from basic calculations, commonly used functions like the log() function, variables and constants and even matrix computations (soon). Acts as a simpler and intuitive alternative to the calculator application as of now but I aim to transform it into a much powerful tool.

## How ?

Written in **Java** using Interpreter theory, this application runs using the classic interpreter pipeline starting from the Lexer to the Recursive Parser and then the Executor. The Executor uses the AST (Abstract Syntax Tree) from the Parser, transforms it into **Bytecode** 🚀and executes it linearly to avoid the cost of tree walking.

## Build Instructions
- Make a directory named "MathShell"
  ```bash
  mkdir MathShell
  cd MathShell
  ```
- Clone it using git inside this folder and run:
  ```bash
  javac Main.java Lexer/*.java Parser/*.java Executor/*.java
  java Main.java
  ```
## Features

- Basic  mathematical operations such as **addition, subtraction ,multiplication,division,modulus and exponents**
- Live Execution, since it is a REPL (Read-Evaluate-Print-Loop) application.
- Press **Enter** to evaluate the expression you write
- The REPL also provides basic error messages for users to understand their mistake
- Type 'exit' to exit the REPL

## Future Plans

- Add multiple core mathematical constants, functions and operations
- Matrix and set theory incorporation
- Variables and Constants
 
## Author
Manoj K M  
Written with Java ☕ and Curiosity



