package interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

enum Type {
    MINUS,
    PLUS,
    INTEGER,
    VARIABLE
}

enum Operation {
    ADDITION,
    SUBSTRACTION,
    NONE
}

class Token {
    public Type type;
    public String text;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }
}

class Lexer {
    private List<Token> tokens = new ArrayList<>();
    // \d|[a-z]+|\+|\-
    public List<Token> lex(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            switch(expression.charAt(i)) {
                case '+':
                    tokens.add(new Token(Type.PLUS, "+"));
                    break;
                case '-':
                    tokens.add(new Token(Type.MINUS, "-"));
                    break;
                default:
                    StringBuilder sb = new StringBuilder("" + expression.charAt(i));
                    if (Character.isDigit(expression.charAt(i))) {
                        for (int j = i+1; j < expression.length(); j++) {
                            if (Character.isDigit(expression.charAt(j))) {
                                sb.append(expression.charAt(j));
                                ++i;
                            } else {
                                tokens.add(new Token(Type.INTEGER, sb.toString()));
                                break;
                            }
                        }
                        if (i == expression.length()-1) {
                            tokens.add(new Token(Type.INTEGER, sb.toString()));
                            break;
                        }
                    } else {
                        for (int j = i+1; j < expression.length(); j++) {
                            if (Character.isAlphabetic(expression.charAt(j))) {
                                sb.append(expression.charAt(j));
                                ++i;
                            } else {
                                tokens.add(new Token(Type.VARIABLE, sb.toString()));
                                break;
                            }
                        }
                        if (i == expression.length()-1) {
                            tokens.add(new Token(Type.VARIABLE, sb.toString()));
                            break;
                        }
                    }
            }
        }
        return tokens;
    }

    @Override
    public String toString() {
        return "Lexer{" +
                "tokens=" + tokens +
                '}';
    }
}



class ExpressionProcessor
{
    public Map<Character, Integer> variables = new HashMap<>();

    public int calculate(String expression) {
        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.lex(expression);
        Integer result = 0;
        boolean notFoundVariable = false;
        Iterator<Token> it = tokens.iterator();
        Token token;
        Operation nextOperation = Operation.NONE;
        while (it.hasNext() && !notFoundVariable) {
            token = it.next();
            switch (token.type) {
                case INTEGER:
                    if (nextOperation == Operation.SUBSTRACTION) {
                        result = result - Integer.parseInt(token.text);
                    } else {
                        result = result + Integer.parseInt(token.text);
                    }
                    break;
                case PLUS:
                    nextOperation = Operation.ADDITION;
                    break;
                case MINUS:
                    nextOperation = Operation.SUBSTRACTION;
                    break;
                case VARIABLE:
                    if (token.text.length()>1) {
                        notFoundVariable = true;
                        result = 0;
                        break;
                    }
                    Integer value = variables.get(token.text.charAt(0));
                    if (value == null) {
                        notFoundVariable = true;
                        result = 0;
                        break;
                    }
                    if (nextOperation == Operation.SUBSTRACTION) {
                        result = result - value;
                    } else {
                        result = result + value;
                    }
                    break;
                default:
                    break;
            }
        }
        return result.intValue();
    }
}

class Demo {
    public static void main(String[] args) {
        ExpressionProcessor ep = new ExpressionProcessor();
        ep.variables.put('x', 3);
        System.out.println(-1 == ep.calculate("3-4"));
        System.out.println(6 == ep.calculate("1+2+x"));
        System.out.println(0 == ep.calculate("1+2+xy"));
        System.out.println(0 == ep.calculate("1+y"));
    }
}