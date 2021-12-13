import javassist.*;

public class ByteCodeEngineeringMain {
    public static void main(String[] args) {
        ClassPool pool = ClassPool.getDefault();
        try {

            // StringBuilder
            CtClass cc = pool.get("java.lang.StringBuilder");
            CtClass ccParam = pool.get("java.lang.CharSequence");

            CtMethod method = cc.getDeclaredMethod("append", new CtClass[] { ccParam });
            System.out.println("Method found: " + method);

            method.insertBefore("{ System.out.println(\"modified\"); }");

            cc.writeFile();

        } catch (Throwable e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder("start");

        char[] appendCharSequence = { 'a', 'p', 'p', 'e', 'n', 'd' };
        sb.append(appendCharSequence);

        System.out.println("StringBuilder: " + sb.toString());
    }

}
