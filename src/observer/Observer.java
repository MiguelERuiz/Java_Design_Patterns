package observer;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Game
{
    public List<Rat> rats = new ArrayList<>();

    public void updateAttack() {
      int sum = rats.size();
      for (Rat rat : rats) {
        rat.attack = sum;
      }
    }
}

class Rat implements Closeable
{
  private Game game;
  public int attack = 1;

  public Rat(Game game)
  {
    this.game = game;
    game.rats.add(this);
    game.updateAttack();
  }

  @Override
  public void close() throws IOException
  {
    game.rats.remove(this);
    game.updateAttack();
  }
}

class Demo
{
  public static void main(String[] args) throws Exception {
    Game game = new Game();
    Rat r1 = new Rat(game);
    Rat r2 = new Rat(game);
    System.out.println(2 == r1.attack);
    System.out.println(2 == r2.attack);
    r1.close();
    System.out.println(1 == r2.attack);
    r2.close();
  }
}