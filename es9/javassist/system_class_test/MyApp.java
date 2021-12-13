import tets.Point;

public class MyApp {
    public static void main(String[] args) {

        Point p = new Point();

        StringBuilder sb = new StringBuilder("start");

        sb.insert(5, " insert");
        
        char[] c = {' ', 'a', 'p', 'p', 'e', 'n', 'd'};
        sb.append(c);

        System.out.println(sb.toString());

    }   
}