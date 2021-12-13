import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

	private static String[] classNames = new String[] { "A", "B" };
	private static String[] classFieldsAndMethods = new String[] { "d", "dd", "i", "s", "add", "mul", "div", "neg", "addDoubles" };

	public static void main(String[] args) {
		System.out.println("Test 1. All fields and methods are declared in selected classes?");
		testOne();

		System.out.println("\nTest 2: Where is declared each field and method?");
		testTwo();

		System.out.println("\nTest 3: Is each name a field or a method?");
		testThree();

		System.out.println("\nTest 4: Full signatures of each field and method?");
		testFour();
	}

	private static void testOne() {
		boolean status = Arrays.stream(classFieldsAndMethods).allMatch(s -> {
			return Arrays.stream(classNames).anyMatch(className -> {
				try {
					Class<?> cls = Class.forName(className);

					Method[] methods = cls.getDeclaredMethods();
					boolean isInMethods = Arrays.stream(methods).anyMatch(m -> m.getName().equals(s));

					Field[] fields = cls.getDeclaredFields();
					boolean isInFields = Arrays.stream(fields).anyMatch(f -> f.getName().equals(s));

					return (isInMethods || isInFields);
				} catch (Exception e) {
					return false;
				}
			});
		});

		System.out.println(status);
	}

	private static void testTwo() {
		Arrays.stream(classFieldsAndMethods).forEach(s -> {
			Arrays.stream(classNames).forEach(className -> {
				try {
					Class<?> cls = Class.forName(className);

					Method[] methods = cls.getDeclaredMethods();
					boolean isInMethods = Arrays.stream(methods).anyMatch(m -> m.getName().equals(s));

					Field[] fields = cls.getDeclaredFields();
					boolean isInFields = Arrays.stream(fields).anyMatch(f -> f.getName().equals(s));

					if (isInMethods || isInFields)
						System.out.println(s + " is in class " + className);
				} catch (Exception e) {

				}
			});
		});
	}

	private static void testThree() {
		Arrays.stream(classFieldsAndMethods).forEach(s -> {
			Arrays.stream(classNames).forEach(className -> {
				try {
					Class<?> cls = Class.forName(className);

					Method[] methods = cls.getDeclaredMethods();
					boolean isInMethods = Arrays.stream(methods).anyMatch(m -> m.getName().equals(s));

					Field[] fields = cls.getDeclaredFields();
					boolean isInFields = Arrays.stream(fields).anyMatch(f -> f.getName().equals(s));

					if (isInMethods) {
						System.out.println(s + " is a method");
					} else if (isInFields) {
						System.out.println(s + " is a field");
					}
				} catch (Exception e) {

				}
			});
		});
	}

	private static void testFour() {
		Arrays.stream(classFieldsAndMethods).forEach(s -> {
			Arrays.stream(classNames).forEach(className -> {
				try {
					Class<?> cls = Class.forName(className);

					Method[] methods = cls.getDeclaredMethods();
					Arrays.stream(methods).forEach(m -> {
						if (m.getName().equals(s)) {
							System.out.println(s + " signature : " + getMethodSignature(m));
						}
					});

					Field[] fields = cls.getDeclaredFields();
					Arrays.stream(fields).forEach(f -> {
						if (f.getName().equals(s)) {
							System.out.println(s + " signature : " + getFieldSignature(f));
						}
					});

				} catch (Exception e) {

				}
			});
		});
	}

	private static String getMethodSignature(Method m) {
		String modifiers = Modifier.toString(m.getModifiers());
		String returnType = classNameToString(m.getReturnType());
		String name = m.getName();
		String params = paramsToString(m.getParameterTypes());
		String exceptions = exceptionsToString(m.getExceptionTypes());

		String signature = String.format("%s %s %s(%s)", modifiers, returnType, name, params);

		if (exceptions != "")
			signature += " throws " + exceptions;

		return signature;
	}

	private static String getFieldSignature(Field f) {
		String modifiers = Modifier.toString(f.getModifiers());
		String returnType = classNameToString(f.getType());
		String name = f.getName();

		String signature = String.format("%s %s %s", modifiers, returnType, name);

		return signature;
	}

	private static String classNameToString(Class<?> cls) {
		if (cls.isArray())
			return cls.getComponentType().getName() + "[]";
		else
			return cls.getName();
	}

	private static String paramsToString(Class<?>[] classes) {
		return IntStream.range(0, classes.length).boxed().map(i -> {
			return classNameToString(classes[i]) + " arg" + i;
		}).collect(Collectors.joining(", "));
	}

	private static String exceptionsToString(Class<?>[] classes) {
		return Arrays.stream(classes).map(c -> classNameToString(c)).collect(Collectors.joining(", "));
	}
}
