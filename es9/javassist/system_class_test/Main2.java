import javassist.*;

public class Main2 {
    public static void main(String[] args) throws Throwable {
        Translator t = new MyTranslator();
        ClassPool pool = ClassPool.getDefault();
        Loader cl = new Loader();
        cl.addTranslator(pool, t);
        cl.run("MyApp", args);
    }
}