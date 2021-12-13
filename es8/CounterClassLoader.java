import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CounterClassLoader extends ClassLoader {
    ArrayList<String> loadedSystemClass = new ArrayList<>();
    ArrayList<String> loadedUserClass = new ArrayList<>();

    public CounterClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> loadedClass;
        if (!name.startsWith("java"))
            loadedClass = getClass(name);
        else
            loadedClass = super.loadClass(name);

        if (loadedClass != null) {
            if (!name.startsWith("java")){
                loadedUserClass.add(name);
                System.out.println("Loaded user class #" + loadedUserClass.size() + ": '" + name + "'");
            }
            else{
                loadedSystemClass.add(name);
                System.out.println("Loaded system class #" + loadedSystemClass.size() + ": '" + name + "'");
            }
        }

        return loadedClass;
    }

    private Class<?> getClass(String name) throws ClassNotFoundException {
        String file = name.replace('.', File.separatorChar) + ".class";

        try {
            byte[] b = loadClassFileData(file);
            Class<?> c = defineClass(name, b, 0, b.length);
            resolveClass(c);
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private byte[] loadClassFileData(String name) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
        int size = stream.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }
}
