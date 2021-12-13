import javassist.*;

public class StringEditor {
    public static void main(String[] args) {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("java.lang.String");
            CtField f = new CtField(CtClass.intType, "hiddenValue", cc);
            f.setModifiers(Modifier.PUBLIC);
            cc.addField(f);
            cc.writeFile(".");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
