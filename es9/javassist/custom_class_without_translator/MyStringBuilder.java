public class MyStringBuilder {
    String data;

    public MyStringBuilder(){
        data = "";
    }

    public MyStringBuilder(String str){
        data = str;        
    }

    public void append(String str){
        data += str;
    }

    public void insert(int offset, String str){
        String pre = data.substring(0, offset);
        String post = data.substring(offset, data.length());
        data = pre + str + post;
    }

    public String toString(){
        return data;
    }
}
