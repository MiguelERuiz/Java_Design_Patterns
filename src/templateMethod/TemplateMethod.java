package templateMethod;

class Creature
{
  public int attack, health;

  public Creature(int attack, int health)
  {
    this.attack = attack;
    this.health = health;
  }
}

abstract class CardGame
{
  public Creature [] creatures;

  public CardGame(Creature[] creatures)
  {
    this.creatures = creatures;
  }

  // returns -1 if no clear winner (both alive or both dead)
  public int combat(int creature1, int creature2)
  {
    Creature first = creatures[creature1];
    Creature second = creatures[creature2];
    hit(first, second);
    hit(second, first);
    // todo: determine who won and return either creature1 or creature2
    int winner = first.health <= 0 && second.health > 0 ? creature2 : second.health <= 0 && first.health > 0 ? creature1 : -1;
    return winner;
  }

  // attacker hits other creature
  protected abstract void hit(Creature attacker, Creature other);
}

class TemporaryCardDamageGame extends CardGame
{

  public TemporaryCardDamageGame(Creature [] creatures) {

    super(creatures);
  }

  @Override
  protected void hit(Creature attacker, Creature other) {
    other.health = other.health - attacker.attack;
    if (other.health > 0) {
      other.health = other.health + attacker.attack;
    }
  }
}

class PermanentCardDamageGame extends CardGame
{

  public PermanentCardDamageGame(Creature [] creatures) {
    super(creatures);
  }

  @Override
  protected void hit(Creature attacker, Creature other) {
    other.health = other.health - attacker.attack;
  }
}

class Demo {
  public static void main(String[] args) {
    Creature [] creatures = {new Creature(1, 2), new Creature(1, 3)};
    CardGame cg = new TemporaryCardDamageGame(creatures);
    System.out.println("Temporary");
    int winner = cg.combat(0, 1);
    System.out.println(winner);
    winner = cg.combat(1, 0);
    System.out.println(winner);

    System.out.println("Permanent");
    cg = new PermanentCardDamageGame(creatures);
    winner = cg.combat(0, 1);
    System.out.println(winner);
    winner = cg.combat(1, 0);
    System.out.println(winner);

    System.out.println("New warriors");
    Creature [] creatures1 = {new Creature(2, 2), new Creature(2, 2)};
    cg = new TemporaryCardDamageGame(creatures1);
    System.out.println("Temporary");
    winner = cg.combat(0, 1);
    System.out.println(winner);
    winner = cg.combat(1, 0);
    System.out.println(winner);

    System.out.println("Permanent");
    cg = new PermanentCardDamageGame(creatures1);
    winner = cg.combat(0, 1);
    System.out.println(winner);
    winner = cg.combat(1, 0);
    System.out.println(winner);
  }
}