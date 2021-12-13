import java.util.Date;

public class B {
  private Double d[];
  private Date dd;
  public static final int i = 42;
  protected static String s = "testing ...";

  public B(int n, double val) {
    dd = new Date();
    d = new Double[n];
    for (int i = 0; i < n; i++)
      d[i] = i * val;
  }

  public static double addDoubles(double a, double b) throws ClassCastException, ClassNotFoundException {
    return a + b;
  }
}