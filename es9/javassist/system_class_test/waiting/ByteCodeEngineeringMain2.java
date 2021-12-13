import javassist.*;

public class ByteCodeEngineeringMain2 {
    public static void main(String[] args) {
        ClassPool pool = ClassPool.getDefault();
        try {

            // StringBuilder
            CtClass cc = pool.get("MyPoint");

            CtMethod method = cc.getDeclaredMethod("say");
            System.out.println("Method found: " + method);

            method.insertBefore("{ System.out.println(\"modified\"); }");

        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }

        MyPoint p = new MyPoint(4, 2);
        p.say();
    }

}
