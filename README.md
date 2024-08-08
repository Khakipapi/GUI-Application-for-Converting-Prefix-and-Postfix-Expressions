# Expression Converter GUI

## Overview

This Java program is a graphical user interface (GUI) application designed for converting expressions between prefix and postfix notations. It was developed as part of an academic project for the CMSC 350 Data Structures and Analysis course at the University of Maryland Global Campus (UMGC).

## Features

- Convert expressions from prefix to postfix notation.
- Convert expressions from postfix to prefix notation.
- User-friendly GUI with input and output fields.
- Error handling with informative messages for incorrect expressions.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- An Integrated Development Environment (IDE) such as IntelliJ IDEA, Eclipse, or NetBeans

### Running the Program

1. **Open the project in your IDE:**
   - Import the project as a Java project.

2. **Run the program:**
   - Locate the `Main.java` file.
   - Run the `Main` class to start the application.

## Usage

1. **Enter an expression:**
   - Input the expression in the provided text field.

2. **Convert the expression:**
   - Click the "Prefix to Postfix" button to convert a prefix expression to postfix.
   - Click the "Postfix to Prefix" button to convert a postfix expression to prefix.

3. **View the result:**
   - The converted expression will be displayed in the output text field.

## Error Handling

The application includes robust error handling to ensure that users are informed of any issues with their input expressions. Possible error messages include:
- Syntax errors in the expression.
- Popping an empty stack.
- Items left in the stack after conversion.

## Example

### Prefix to Postfix Conversion
- **Input:** `*+AB-CD`
- **Output:** `AB+CD-*`

### Postfix to Prefix Conversion
- **Input:** `AB+CD-*`
- **Output:** `*+AB-CD`

## PrePost Class

Below is the implementation of the `PrePost` class, which handles the conversion from prefix to postfix notation:

```java
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.*;

public class PrePost {
    public static String prefixToPostfix(String expression) throws SyntaxError, IOException {
        if (!expression.equals("")){
            List<String> arrayExp = tokenizeExpression(expression);
            Stack<String> operandStack = new Stack<>();

            // Reverse the list
            Collections.reverse(arrayExp);

            // Check for operands
            for (String token : arrayExp) {
                if (!isOperator(token)){
                    operandStack.push(token + " ");
                } else {
                    try {
                        String operandOne = operandStack.pop();
                        String operandTwo = operandStack.pop();
                        String innerExpression = operandOne + operandTwo + token + " ";
                        operandStack.push(innerExpression);
                    } catch (EmptyStackException ex){
                        throw new SyntaxError("Error: calling pop on an empty stack! Please check expression.");
                    }
                }
            }
            String result = operandStack.pop();

            // Check if stack is empty.
            if (operandStack.empty()){
                return result;
            } else { // else throw new exception
                throw new SyntaxError("Error: Stack is not empty. Please check expression.");
            }

        } else { // Will add empty string to stack without this
            throw new SyntaxError("Please enter something!");
        }
    }

    static List<String> tokenizeExpression(String expression) throws IOException {
        StreamTokenizer tokenizeExpression = new StreamTokenizer(new StringReader(expression));

        // Treat the following as normal chars
        tokenizeExpression.ordinaryChar('-');
        tokenizeExpression.ordinaryChar('/');
        List<String> tokenList = new ArrayList<>(); // Can also store as objects and cast

        // Match tokens until end of stream
        while (tokenizeExpression.nextToken() != StreamTokenizer.TT_EOF) {
            // Number
            if (tokenizeExpression.ttype == StreamTokenizer.TT_NUMBER) {
                tokenList.add(String.valueOf((int) tokenizeExpression.nval));
            // If for some reason there are words
            } else if (tokenizeExpression.ttype == StreamTokenizer.TT_WORD) {
                tokenList.add(tokenizeExpression.sval);
            } else { // Operator
                tokenList.add(String.valueOf((char) tokenizeExpression.ttype));
            }
        }
        return tokenList;
    }

    static boolean isOperator(String term){
        switch (term.charAt(0)) { // Also looks at the first operand in inner expressions
            case '+':
            case '-':
            case '/':
            case '*':
            case '^':
                return true;
        }
        return false;
    } // End of isOperator method.
}

class SyntaxError extends Exception {
    public SyntaxError() {
        super();
    }

    public SyntaxError(String message) {
        super(message);
    }
}
```

## PostPre Class

Below is the implementation of the `PostPre` class, which handles the conversion from postfix to prefix notation:

```java
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class PostPre {

    public static String postfixToPrefix(String expression) throws IOException, SyntaxError {
        if (!expression.equals("")) {
            // Tokenize the string and create stack
            List<String> expressionToArray = PrePost.tokenizeExpression(expression);
            Stack<String> operandStack = new Stack<>();

            // Loop through the expression array and first check for operands
            for (String token : expressionToArray) {
                if (!PrePost.isOperator(token)) {
                    operandStack.push(token + " ");
                } else {
                    try {
                        String operand2 = operandStack.pop();
                        String operand1 = operandStack.pop();
                        String temp = token + " " + operand1 + operand2;
                        operandStack.push(temp);
                    } catch (EmptyStackException ex) {
                        throw new SyntaxError("Trying to call pop on an empty stack! Please check expression.");
                    }
                }
            }
            String result = operandStack.pop();

            // Check if stack is empty. Then return result
            if (operandStack.empty()) {
                return result;
            } else {
                throw new SyntaxError("Error: Stack is not empty. Please check expression.");
            }
        } else { // Will add empty string to stack without this
            throw new SyntaxError("Please enter an expression.");
        }
    }
}
```

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure your code follows best practices and includes appropriate documentation.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgements

- University of Maryland Global Campus (UMGC)
- CMSC 350 Data Structures and Analysis course
