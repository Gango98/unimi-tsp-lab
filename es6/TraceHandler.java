import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TraceHandler implements InvocationHandler {
    private Object baseObject;

    public TraceHandler(Object base) {
        baseObject = base;
    }

    public Object invoke(Object proxy, Method m, Object[] args) {
        try {
            System.out.println("Object State - before: " + System.lineSeparator() + getBasebOjectState());
            Object result = m.invoke(baseObject, args);
            System.out.println("Object State - after: " + System.lineSeparator() + getBasebOjectState());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getBasebOjectState() {
        Class<?> cls = baseObject.getClass();
        Field[] fields = cls.getDeclaredFields();

        String objectStateString  = Arrays.stream(fields).map(f -> {
            return getFieldState(baseObject, f);
        }).collect(Collectors.joining(System.lineSeparator()));

        return objectStateString;
    }

    private String getFieldState(Object object, Field field) {
        try {
            field.setAccessible(true);
            Object fieldObj = field.get(baseObject);

            if (!fieldObj.getClass().isArray()) {
                return field.getName() + " = " + field.get(baseObject);
            } else {
                String arrayString = Arrays.stream((Object[]) fieldObj).map(f -> f.toString())
                        .collect(Collectors.joining(", "));
                return field.getName() + " = [" + arrayString + "]";
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return field.getName() + " = ";
        }
    }
}