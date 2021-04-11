package com.mashibing.tank.Decorator;

import com.mashibing.tank.GameObject;

import java.awt.*;

public abstract class GODecorator extends GameObject {

    GameObject go;

    public GODecorator(GameObject go){
        this.go = go;
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
