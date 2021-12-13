import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) {
        TestingFieldsI t1 = new TestingFields(5, 10);

        TestingFieldsI t2 = (TestingFieldsI) Proxy.newProxyInstance(
                t1.getClass().getClassLoader(),
                t1.getClass().getInterfaces(),
                new TraceHandler(t1));

        System.out.println("Without proxy");
        t1.setAnswer(10);
        System.out.println("Message: " + t1.message());

        System.out.println("--------------");

        System.out.println("With proxy");
        t2.setAnswer(20);
        System.out.println("Message: " + t2.message());
    }
}
