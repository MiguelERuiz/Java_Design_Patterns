package decorator;

class Bird
{
    public int age;

    public String fly()
    {
        return age < 10 ? "flying" : "too old";
    }
}

class Lizard
{
    public int age;

    public String crawl()
    {
        return (age > 1) ? "crawling" : "too young";
    }
}

class Dragon
{
    private int age;
    private Bird bird = new Bird();
    private Lizard lizard = new Lizard();
    public void setAge(int age)
    {
        this.age = age;
        bird.age = age;
        lizard.age = age;
    }
    public String fly()
    {
        return Bird.fly();
    }
    public String crawl()
    {
        return lizard.crawl();
    }
}