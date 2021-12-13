import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DumpMethods {

	public static void main(String[] args) {
		if (args.length != 1)
			return;

		String className = args[0];
		try {
			Class<?> c = Class.forName(className);
			Method[] methods = c.getMethods();

			Arrays.stream(methods).forEach(m -> {
				System.out.println(methodToString(m));
			});
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	private static String methodToString(Method m) {
		Class<?>[] paramsTypes = m.getParameterTypes();

		String paramsString = IntStream.range(0, m.getParameterCount()).boxed().map(i -> {
			return classNameToString(paramsTypes[i]) + " arg" + i;
		}).collect(Collectors.joining(", "));

		String methodString = String.format("%s %s(%s)",
				classNameToString(m.getReturnType()), m.getName(),
				paramsString);

		return methodString;
	}

	private static String classNameToString(Class<?> cls) {
		if (cls.isArray())
			return cls.getComponentType().getName() + "[]";
		else
			return cls.getName();
	}

}
