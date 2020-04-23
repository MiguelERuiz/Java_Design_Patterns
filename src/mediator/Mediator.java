package mediator;
import java.util.ArrayList;
import java.util.List;

class Participant
{
  public int value = 0;
  private Mediator mediator;
  public Participant(Mediator mediator)
  {
    this.mediator = mediator;
    this.mediator.list.add(this);
  }

  public void say(int n)
  {
    this.mediator.broadcast(this, n);
  }
}

class Mediator
{
  public List<Participant> list = new ArrayList<>();

  public void broadcast(Participant p, int n) {
    for (Participant participant : list) {
      if(!p.equals(participant)) {
        participant.value = n;
      }
    }
  }
}


class Demo {

  public static void main(String[] args) {
    Mediator mediator = new Mediator();
    Participant p1 = new Participant(mediator);
    Participant p2 = new Participant(mediator);

    p1.say(3);
    System.out.println(3 == p2.value);
    System.out.println(0 == p1.value);
    p2.say(2);
    System.out.println(3 == p2.value);
    System.out.println(2 == p1.value);
  }
}