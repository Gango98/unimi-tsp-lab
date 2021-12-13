import java.util.ArrayList;
public class ClassLoaderMain {
    public static void main(String[] args){
        A a = new A();
        B b = new B();

        a.SayX();
        a.x = b.GetSqaureN();
        a.SayX();
    }
}
