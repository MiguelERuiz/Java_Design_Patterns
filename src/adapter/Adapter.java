package adapter;

class Square
{
  public int side;

  public Square(int side)
  {
    this.side = side;
  }
}

interface Rectangle
{
  int getWidth();
  int getHeight();

  default int getArea()
  {
    return getWidth() * getHeight();
  }
}

class SquareToRectangleAdapter implements Rectangle
{

    private int width;
    private int height;

    public SquareToRectangleAdapter(Square square)
    {
        height = square.side;
        width = square.side;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}