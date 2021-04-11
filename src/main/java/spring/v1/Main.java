package spring.v1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

// 需要配置spring 配置文件
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app.xml");
        Tank t = (Tank) context.getBean("tank");
        t.move();
    }
}
