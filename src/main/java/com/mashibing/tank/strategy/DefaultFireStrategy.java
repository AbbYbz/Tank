package com.mashibing.tank.strategy;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Decorator.RectDecorator;
import com.mashibing.tank.GameModel;
import com.mashibing.tank.Tank;

public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank t) {
        int bX = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        // bug: Bullet加了两遍
        GameModel.getInstance().add(new RectDecorator(new Bullet(bX, by, t.dir, t.group)));
    }
}
