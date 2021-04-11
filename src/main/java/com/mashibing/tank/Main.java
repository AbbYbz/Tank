package com.mashibing.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // 生成一个窗口
        TankFrame tf = new TankFrame();

        while(true){
            Thread.sleep(25);
            tf.repaint();
        }
    }
}
