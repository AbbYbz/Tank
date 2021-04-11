package com.mashibing.tank.Decorator;

import com.mashibing.tank.GameObject;

import java.awt.*;

public class RectDecorator extends GODecorator{

    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 在原先的基础上 添加新修饰
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(super.go.x,super.go.y,super.go.getWidth()+2,super.go.getHeight()+2);
        g.setColor(c);
    }

}
