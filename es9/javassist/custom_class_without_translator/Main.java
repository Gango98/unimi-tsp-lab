import javassist.*;

public class Main {
    public static void main(String[] args) {

        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass cc = pool.get("MyStringBuilder");

            // ###### APPEND ######
            CtMethod appendMethod = cc.getDeclaredMethod("append");

            appendMethod.addLocalVariable("startTime", CtClass.longType);
            appendMethod.addLocalVariable("endTime", CtClass.longType);
            appendMethod.addLocalVariable("execTime", CtClass.longType);

            appendMethod.insertBefore("System.out.println(\"### Append started ###\");"
                    + "startTime = System.nanoTime();");
            appendMethod.insertAfter("endTime = System.nanoTime();"
                    + "execTime = endTime - startTime;"
                    + "System.out.println(\"### Append finished in \" + execTime/1000000 + \"ms ###\");");

            // ###### INSERT ######
            CtMethod insertMethod = cc.getDeclaredMethod("insert");

            insertMethod.addLocalVariable("startTime", CtClass.longType);
            insertMethod.addLocalVariable("endTime", CtClass.longType);
            insertMethod.addLocalVariable("execTime", CtClass.longType);

            insertMethod.insertBefore("System.out.println(\"### Insert started ###\");"
                    + "startTime = System.nanoTime();");
            insertMethod.insertAfter("endTime = System.nanoTime();"
                    + "execTime = endTime - startTime;"
                    + "System.out.println(\"### Insert finished in \" + execTime/1000000 + \"ms ###\");");

            cc.writeFile();

        } catch (Throwable e) {
            e.printStackTrace();
        }

        MyStringBuilder sb = new MyStringBuilder("start");
        System.out.println(sb.toString());

        sb.append("append");
        System.out.println(sb.toString());

        sb.insert(3, "insert");
        System.out.println(sb.toString());
    }
}
