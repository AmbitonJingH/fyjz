package com.fyjz.shoot.day06;

import java.awt.image.BufferedImage;

/**
 * 英雄机子弹
 */
public class Bullet extends FlyingObject {

    private int speed;    //速度
    static BufferedImage image;
    static {
        image = loadImage("bullet.png");
    }
    //构造方法
    //子弹的x,y坐标随英雄机移动而移动，所以传递参数x,y
    public Bullet(int x,int y){
        super(x,y,18,45);
        this.speed=3;
    }
    //移动行为
    public void step(){
        this.y -= speed;
    }

    @Override
    public BufferedImage getImage() {
        if(isLife()){
            return image;
        }else if(isDead()){
            state = REMOVE;
        }
        return null;
    }


}
