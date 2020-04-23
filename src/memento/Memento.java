package memento;
import java.util.ArrayList;
import java.util.List;

class Token
{
  public int value = 0;

  public Token(int value)
  {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Token: { " +
      value + " }";
  }
}

class Memento
{
  public List<Token> tokens = new ArrayList<>();

  public Memento (List<Token> tokens)
  {
    for (Token token : tokens) {
      this.tokens.add(token);
    }
  }

  @Override
  public String toString() {
    return tokens.toString();
  }
}

class TokenMachine
{
  public List<Token> tokens = new ArrayList<>();

  public Memento addToken(int value)
  {
    return addToken(new Token(value));
  }

  public Memento addToken(Token token)
  {
    Token t = new Token(token.value);
    tokens.add(t);
    return new Memento(tokens);
  }

  public void revert(Memento m)
  {
    tokens = m.tokens;
  }

  @Override
  public String toString() {
    return tokens.toString();
  }
}

class Demo {

  public static void main(String[] args) {
    TokenMachine tm = new TokenMachine();
    Memento m1 = tm.addToken(1);
    System.out.println(tm);
    Memento m2 = tm.addToken(new Token(3));
    System.out.println(tm);
    tm.revert(m1);
    System.out.println(tm);
  }
}