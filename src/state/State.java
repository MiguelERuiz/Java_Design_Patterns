package state;

class CombinationLock
{
  private int [] combination;
  private String comb = "";
  public String status;

  public CombinationLock(int[] combination)
  {
    this.combination = combination;
    status = "LOCKED";
    for (int i : combination) {
      comb = comb + String.valueOf(i);
    }
  }


  public void enterDigit(int digit)
  {
    status = "LOCKED".equals(status) ? String.valueOf(digit) : status + String.valueOf(digit);
    int length = status.length();
    if (comb.length() == length && status.equals(comb)) {
      status = "OPEN";
    } else if (comb.length() == length && !status.equals(comb)) {
      status = "ERROR";
    }
  }
}

class Demo {
  public static void main(String[] args) {
    CombinationLock cl = new CombinationLock(new int [] {1, 2, 3, 4});
    System.out.println("LOCKED".equals(cl.status));
    cl.enterDigit(1);
    System.out.println("1".equals(cl.status));
    cl.enterDigit(2);
    System.out.println("12".equals(cl.status));
    cl.enterDigit(3);
    System.out.println("123".equals(cl.status));
    cl.enterDigit(5);
    System.out.println("ERROR".equals(cl.status));

    CombinationLock cl1 = new CombinationLock(new int [] {1, 2, 3, 4});
    System.out.println("LOCKED".equals(cl1.status));
    cl1.enterDigit(1);
    System.out.println("1".equals(cl1.status));
    cl1.enterDigit(2);
    System.out.println("12".equals(cl1.status));
    cl1.enterDigit(3);
    System.out.println("123".equals(cl1.status));
    cl1.enterDigit(4);
    System.out.println("OPEN".equals(cl1.status));
  }
}