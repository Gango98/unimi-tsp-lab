import javassist.*;

public class Test {
    public static void main(String[] args) throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("MyPoint");
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("{ System.out.println(\"MyPoint.say():\"); }");
        Class c = cc.toClass();
        //MyPoint h = (MyPoint)c.getDeclaredConstructor().newInstance();
        //h.say();
    }
}