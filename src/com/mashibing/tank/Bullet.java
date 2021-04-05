package com.mashibing.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 20;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    Rectangle rect = new Rectangle();

    private int x,y;
    private Dir dir;
    TankFrame tf = null;

    private boolean liveing = true;
    private Group group = Group.BAD;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf= tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        tf.bullets.add(this);
    }

    public void paint(Graphics g){
        if(!liveing){
            tf.bullets.remove(this);
        }

        switch(dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y, null);
                break;
        }
        
        move();
    }

    private void move() {

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        // update rect
        rect.x = this.x;
        rect.y = this.y;

        if (x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT) liveing = false;

    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) return;

        // TODO: 用一个rect来记录子弹位置
        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);

        if (rect.intersects(tank.rect)){
            tank.die();
            this.die();

            int eX = tank.getX()+Tank.WIDTH/2-Explode.WIDTH/2;
            int eY = tank.getY()+Tank.HEIGHT/2-Explode.HEIGHT/2;
            tf.explodes.add(new Explode(eX,eY,tf));
        }
    }

    private void die() {
        this.liveing=false;
    }
}
