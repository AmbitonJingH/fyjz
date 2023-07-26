package com.fyjz.shoot.day06;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
    天空
 */
public class Sky extends FlyingObject {

    private int speed;    //速度
    private int y1;
    static BufferedImage image;
    static {
        image = loadImage("bg1.png");
    }
    //构造方法
    public Sky(){
        super(0,0, World.WIDTH, World.HEIGHT);
        this.speed=1;
        this.y1 = -this.height;

    }
    //移动行为
    public void step(){

        this.y+=this.speed;
        this.y1+=this.speed;
        if(this.y>= World.HEIGHT){
            this.y = -this.height;
        }
        if(this.y1>= World.HEIGHT){
            this.y1 = -this.height;
        }
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }
    public void pointObject(Graphics graphics){
        graphics.drawImage(getImage(),this.x,this.y,null);
        graphics.drawImage(getImage(),this.x,this.y1,null);
    }

    @Override
    public boolean flyingObjectOut() {
        return false;
    }
}
