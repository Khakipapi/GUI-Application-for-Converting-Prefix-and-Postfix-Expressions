import java.io.IOException;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class PostPre {

    public static String postfixToPrefix(String expression) throws IOException, SyntaxError {
        if (!expression.equals("")){
//tokenize the string and create stack
            List<String> expressionToArray = PrePost.tokenizeExpression(expression);
            Stack<String> operandStack = new Stack<>();
//loop through the expression array and first check for operands
            for (String token:expressionToArray) {
                if (!PrePost.isOperator(token)){
                    operandStack.push(token + " ");
                }else {
                    try {
                        String operand2 = operandStack.pop();
                        String operand1 = operandStack.pop();
                        String temp = token + " " + operand1 + operand2;
                        operandStack.push(temp);
                    }catch (EmptyStackException ex){
                        throw new SyntaxError("Trying to call pop on an empty stack! Please check expression.");
                    }
                }
            }
            String result = operandStack.pop();
//check if stack is empty. Then return result
            if (operandStack.empty()){
                return result;
            }else {
                throw new SyntaxError("Error: Stack is not empty. Please check expression.");
            }
        } else { //will add empty string to stack without this
            throw new SyntaxError("Please enter an expression.");
        }
    }
}
