package bridge;

interface Renderer {
  public String whatToRenderAs();
}

class VectorRenderer implements Renderer {
  @Override
  public String whatToRenderAs() {
    return "lines";
  }
}

class RasterRenderer implements Renderer {
  @Override
  public String whatToRenderAs() {
    return "pixels";
  }
}

abstract class Shape
{
  protected Renderer renderer;
  public abstract String getName();

  public Shape(Renderer renderer) {
    this.renderer = renderer;
  }

  @Override
  public String toString() {
    return String.format("Drawing %s as %s", getName(), renderer.whatToRenderAs());
  }
}

class Triangle extends Shape
{
  protected Renderer renderer;
  public Triangle(Renderer renderer) {
    super(renderer);
  }

  @Override
  public String getName() {
    return "Bridge.Triangle";
  }
}

class Square extends Shape
{
  protected Renderer renderer;
  public Square(Renderer renderer) {
    super(renderer);
  }

  @Override
  public String getName() {
    return "Adapter.Bridge.Square";
  }
}

class BridgeDemo {
  public static void main(String[] args) {
    System.out.println(new Triangle(new RasterRenderer()).toString());
  }
}