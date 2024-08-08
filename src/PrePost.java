import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.*;

public class PrePost {
    public static String prefixToPostfix(String expression) throws SyntaxError, IOException {
        if(!expression.equals("")){
            List<String> arrayExp = tokenizeExpression(expression);
            Stack<String> operandStack = new Stack<>();

//reverse the list
            Collections.reverse(arrayExp);
// check for operands
            for (String token:arrayExp) {
                if (!isOperator(token)){
                    operandStack.push(token + " ");
                }else {
                    try {
                        String operandOne = operandStack.pop();
                        String operandTwo = operandStack.pop();
                        String innerExpression = operandOne + operandTwo + token + " ";
                        operandStack.push(innerExpression);
                    }catch (EmptyStackException ex){
                        throw new SyntaxError("Error: calling pop on an empty stack! Please check expression.");
                    }
                }
            }
            String result = operandStack.pop();
//check if stack is empty.
            if (operandStack.empty()){
                return result;
            }else { // else throw new exception
                throw new SyntaxError("Error: Stack is not empty. Please check expression.");
            }

        }else { //will add empty string to stack without this
            throw new SyntaxError("Please enter something!");
        }
    }
    static List<String> tokenizeExpression(String expression) throws IOException {
        StreamTokenizer tokenizeExpression = new StreamTokenizer(new StringReader(expression));
//treat the following as normal chars
        tokenizeExpression.ordinaryChar('-');
        tokenizeExpression.ordinaryChar('/');
        List<String> tokenList = new ArrayList<>();// can also store as objects and cast
// match tokens until end of stream
        while (tokenizeExpression.nextToken() != StreamTokenizer.TT_EOF){
//number
            if (tokenizeExpression.ttype == StreamTokenizer.TT_NUMBER){
                tokenList.add(String.valueOf((int)tokenizeExpression.nval));
//if for some reason there are words
            }else if(tokenizeExpression.ttype == StreamTokenizer.TT_WORD){
                tokenList.add(tokenizeExpression.sval);
            }else{ //operator
                tokenList.add(String.valueOf((char) tokenizeExpression.ttype));
            }
        }
        return tokenList;
    }
    static boolean isOperator(String term){
        switch (term.charAt(0)){ //also looks at the first operand in inner expressions
            case '+':
            case '-':
            case '/':
            case '*':
            case '^':
                return true;
        } return false;
    }//end of isOperator method.


}

class SyntaxError extends Exception {
    public SyntaxError() {
        super();
    }
    public SyntaxError(String message) {

        super(message);

    }

}