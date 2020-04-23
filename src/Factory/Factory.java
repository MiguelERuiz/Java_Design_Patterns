package Factory;

class Person
{
  public int id;
  public String name;

  public Person(int id, String name)
  {
    this.id = id;
    this.name = name;
  }
}

class PersonFactory
{
    private static int id = 0;
    public Person createPerson(String name)
    {
        Person p = new Person(id, name);
        id++;
        return p;
    }
}