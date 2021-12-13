# unimi-tsp-lab
Soluzioni agli esercizi del corso Tecniche Speciali di Programmazione 

# 1. Introspection
In order to discover how introspection works in Java, let's write the class `DumpMethods` whose `main()` method gets class name from the command line through args and outputs (prints) all the methods that can be called on an instance of the passed class. Note that, we are looking for the public interface of the class not for its declared methods.

# 2. Intercession
Let's write the class `InvokeUnknownMethod` that invoked a method whose identity is unknown at compile-time through reflection. Let's suppose that our program would like to invoke one of the methods of the class:

```
public class Calculator {
  public int add(int a, int b) { return a + b; }
  public int mul(int a, int b) { return a * b; }
  public double div(double a, double b) { return a / b; }
  public double neg(double a) { return -a; }
}
```

without knowing which one until the main is executed; i.e., the method name, the number and type of its arguments should be inferred from the input. An example of activation will be:

```
java InvokeUnknownMethod Calculator add 7 25
```

Hints, let's use the class `Pattern` to infer the type of the arguments.

# 3. Testing Fields
Let's write a program that analyses the state of a generic class. As a test case let's use the following class:

```
import java.util.Date;

public class TestingFields {
  private Double d[];
  private Date dd;
  public static final int i = 42;
  protected static String s = "testing ...";

  public TestingFields(int n, double val) {
    dd = new Date();
    d = new Double[n];
    for(int i=0; i<n; i++) d[i] = i*val;
  }
}
```

and one instance with n=7 and val=3.14. The state of the instance is defined as the collection of the values of all the instance fields (both static, non static, public and private).

Let's change (through reflection) the value of s to testing ... passed!!!.

# 4. Recognizing Elements.
Let's consider to have a collection of Java classes and two string arrays:
- The **first array** contains the names of the Java classes
- The **second array** contains the names of all the fields and all the methods declared in the classes whose names are stored in the first array

There is no relationship between the two arrays, i.e., we don't know whether and in which class are declared the names stored in the second array nor if they represent a field or a method.

Let's write a program that
1. checks that the names in the second array are all declared in one of the classes in the first array
2. outputs, for each name where it is declared, 
3. outputs whether it is a method or a field and
4. the full signature included types, visibility and scope.

# 5. Control Flow Graph
Let's write an application that can build the control flow graph of the calls of another application. A control flow graph is a directed cyclic graph whose nodes represent the application's classes and the edges represent a call from an instance of the class represented by the source node to an instance of the class represented by the destination node of the edge; the edge is labeled with the method name.

The code of the application under analysis is decorated so that all the needed data to build the control from graph are available from the annotations. In particular, each method will be annotated with the data about the methods it invokes and the pertaining classes.

\[STATIC\]. The application we have to write will receive from the command line all the names of the classes composing the application under analysis. The application will analyze the annotation of the application under analysis and it will build the control flow graph of all calls independently that these are effectively invoked.
\[DYNAMIC\]. The application we have to write will receive as an input the name of the class that contains the main() method of the application under analysis. It will launch the main files and collect all the annotations that are attached to the invoked methods. Once the execution of the program under analysis will end, the application will build the control flow graph accordingly with the data collected during the execution. Note that in this case the nodes of the control flow graph should represents instances and the graph shows only those calls effectively performed. Hints. in this case, both a premain and some techniques of bytecode engineering can be useful.
To define the needed annotations and decorate the application under analysis is part of the exercise.

As per the output you can either run a depth-first visit and output it on the terminal as a list of arcs, output a dot file (from GraphWiz) and process it with dot to generate a pic.

# 6. Proxy
Let's write a meta-object (through the Proxy class) to be linked to the instances of the class:

```
import java.util.Date;

public class TestingFields {
  private Double[] d;
  private Date dd;
  private int the_answer = 42;

  public TestingFields(int n, double val) {
    dd = new Date();
    d = new Double[n];
    for(int i=0; i<n; i++) d[i] = i*val;
  }
  
  public void setAnswer(int a) { the_answer = a; }
  public String message() { return "The answer is "+the_answer; }
}
```

that shows the object state before and after the invocation of each method.

# 7. NestedCalls
Let's write a meta-object (through the Proxy class) to link to the instances of the class:

```
public class NestedCalls implements NestedCallsI {
  private int i = 0;

  public int a() { return b(i++); }
  public int b(int a) { return (i<42)?c(b(a())):1; }
  public int c(int a) { return --a; }
}
```

that shows the nesting level at each method invocation. Hint. inheritance and polymorphism are your friends.

# 8. Class Loading
Let's extend the Java class loader so that:

It prints how many system―those from the packages java.*―and user-defined classes have been loaded. Obviously the main() method of the test class must load and use several classes so that the test could be considered meaningful. As an extension, let's consider to print also the name of the loaded classes. Note that you have to disable the pre-loading of some system classes.
It raises a RuntimeException when there is an attempt to use one of the classes defined into the java.lang and java.util packages. Note that the JVM pre-loads many of the classes from these packages and this can distort the final result of your program: let's find a way to take rid of such a behavior.

# 9. Byte Code Engineering
The standard StringBuilder class provides, among the others, the methods

StringBuilder append(char[] str) that appends the string passed as an argument to the StringBuilder and
StringBuilder insert(int offset, String str) that inserts the string passed as an argument into the StringBuilder at the specified position.
By using BCEL, inject in these methods the code needed to measure how long it takes to do the append/insert. Then write a main class that checks if the append method takes more than the insert method to append a given string to the end. Note, subclassing can be a viable option to solve the exercise (not the only one).

Repeat the exercise by using Javassist.