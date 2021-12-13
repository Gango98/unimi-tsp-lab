import javassist.*;

public class Main {
    public static void main(String[] args) {
        try {
            Translator t = new MyTranslator();
            ClassPool pool = ClassPool.getDefault();
            Loader cl = new Loader();
            cl.addTranslator(pool, t);
            cl.run("MyApp", args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
