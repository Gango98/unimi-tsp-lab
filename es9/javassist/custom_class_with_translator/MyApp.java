public class MyApp {
    public static void main(String[] args) {

        MyStringBuilder sb = new MyStringBuilder("start");
        System.out.println(sb.toString());

        sb.append("append");
        System.out.println(sb.toString());

        sb.insert(3, "insert");
        System.out.println(sb.toString());      
    }   
}