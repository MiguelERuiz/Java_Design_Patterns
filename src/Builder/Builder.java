package Builder;

import java.util.ArrayList;

class ClassElement {
  String className;
  ArrayList<Attribute> attributes = new ArrayList<>();

  public ClassElement() {}

  public ClassElement (String className) {
    this.className = className;
  }
}

class Attribute {
  public String type;
  public String name;

  public Attribute() {}

  public Attribute(String type, String name) {
    this.type = type;
    this.name = name;
  }
}

class CodeBuilder {
  private String className;
  private ClassElement classElement = new ClassElement(className);

  public CodeBuilder(String className) {
    this.className = className;
  }

  public CodeBuilder addField(String name, String type) {
    Attribute attr = new Attribute(type, name);
    this.classElement.attributes.add(attr);
    return this;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("public class %s\n{\n", this.className));

    for (Attribute attribute : this.classElement.attributes) {
      sb.append(String.format("  public %s %s;\n", attribute.type, attribute.name));
    }
    sb.append("}");
    return sb.toString();
  }
}


class Demo {
  public static void main(String[] args) {
    CodeBuilder cb = new CodeBuilder("person").addField("name", "String").addField("age", "int");
    System.out.println(cb);
  }
}