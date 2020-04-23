package ChainOfResponsability;

import java.util.ArrayList;
import java.util.List;

abstract class Creature
{
    public abstract int getAttack();
    public abstract int getDefense();
}

class Goblin extends Creature
{
    private Game game;
    public Goblin(Game game)
    {
        this.game = game;
    }

    @Override
    public int getAttack()
    {
        int result = 1;
        for (Creature c : game.creatures) {
            if (c instanceof GoblinKing) {
                result += 1;
            }
        }
        return result;
    }

    @Override
    public int getDefense()
    {
        int result = 0;
        for (Creature c : game.creatures) {
            result += 1;
        }
        return result;
    }
}

class GoblinKing extends Goblin
{
    public GoblinKing(Game game)
    {
        super(game);
    }
}

enum Statistic
{
    ATTACK, DEFENSE
}

class Game
{
    public List<Creature> creatures = new ArrayList<>();
}