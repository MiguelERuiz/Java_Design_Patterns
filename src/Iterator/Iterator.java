import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Node<T>
{
  public T value;
  public Node<T> left, right, parent;

  public Node(T value)
  {
    this.value = value;
  }

  public Node(T value, Node<T> left, Node<T> right)
  {
    this.value = value;
    this.left = left;
    this.right = right;

    left.parent = right.parent = this;
  }

  private void treeTraverse(Node<T> node, List<Node<T>> list) {
    list.add(node);
    if (node.left != null) treeTraverse(node.left, list);
    if (node.right != null) treeTraverse(node.right, list);
  }

  public Iterator<Node<T>> preOrder()
  {
    List<Node<T>> list = new ArrayList<>();
    treeTraverse(this, list);
    return list.iterator();
  }
}

class Demo {
  public static void main(String[] args) {
      Node<Character> node = new Node<>('a',
              new Node<>('b',
                      new Node<>('c'),
                      new Node<>('d')),
              new Node<>('e'));
      StringBuilder sb = new StringBuilder();
      Iterator<Node<Character>> it = node.preOrder();
      while (it.hasNext())
      {
          sb.append(it.next().value);
      }
      System.out.println(sb);
  }
}