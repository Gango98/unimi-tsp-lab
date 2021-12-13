import java.lang.reflect.Field;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		TestingFields t = new TestingFields(7, 3.14);
		
		System.out.println("BEFORE reflection:\n" + getObjectState(t));
		
		setSThroughReflection(t, "testing ... passed!!!");
		
		System.out.println("AFTER reflection:\n" + getObjectState(t));
	}
	
	private static void setSThroughReflection(TestingFields object, String value) {
		try {
			Field sField = object.getClass().getDeclaredField("s");
			// sField.setAccessible(true);
			sField.set(object, "Testing... Passed!");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static String getObjectState(Object obj) {
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();

        String objectStateString  = Arrays.stream(fields).map(f -> {
            return getFieldState(obj, f);
        }).collect(Collectors.joining(System.lineSeparator()));

        return objectStateString;
    }

    private static String getFieldState(Object object, Field field) {
        try {
            field.setAccessible(true);
            Object fieldObj = field.get(object);

            if (!fieldObj.getClass().isArray()) {
                return field.getName() + " = " + field.get(object);
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
