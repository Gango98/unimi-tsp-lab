import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class InvokeUnknownMethod {

	public static void main(String[] args) {
		if (args.length < 2)
			return;

		final String className = args[0];
		final String methodName = args[1];

		// get classes of all params in args
		String[] methodParams = new String[args.length - 2];
		System.arraycopy(args, 2, methodParams, 0, args.length - 2);
		Class<?>[] methodParamsClasses = getMethodClasses(methodParams);

		try {
			// get target method
			Class<?> cls = Class.forName(className);
			Method method = cls.getMethod(methodName, methodParamsClasses);

			// parse args to objects
			Object[] objects = getMethodObjects(methodParamsClasses, methodParams);

			// Invoke target method
			Object temp = cls.getDeclaredConstructor().newInstance();
			Object result = method.invoke(temp, objects);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Class<?>[] getMethodClasses(String[] strings) {
		Class<?>[] classes = new Class<?>[strings.length];
		for (int i = 0; i < strings.length; i++) {
			if (Pattern.matches("^[+-]?\\d+$", strings[i]))
				classes[i] = int.class;
			else if (Pattern.matches("^[+-]?\\d+\\.\\d+$", strings[i]))
				classes[i] = double.class;
			else
				classes[i] = String.class;
		}
		return classes;
	}

	private static Object[] getMethodObjects(Class<?>[] classes, String[] strings) {
		Object[] objects = new Object[classes.length];
		for (int i = 0; i < classes.length; i++) {
			if (classes[i] == int.class)
				objects[i] = Integer.valueOf(strings[i]);
			else if (classes[i] == double.class)
				objects[i] = Double.valueOf(strings[i]);
			else
				objects[i] = strings[i];
		}
		return objects;
	}
}
