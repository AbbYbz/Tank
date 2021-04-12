package adapter;

import java.io.*;

// 常见的Adapter类反而不是Adapter;   eg: WindowAdapter
public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("c:/");
        // InputStreamReader 相当于适配器
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();
        while (line!=null && !line.equals("")){
            System.out.println(line);
        }
        br.close();
    }
}
