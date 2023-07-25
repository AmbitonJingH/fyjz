package com.fyjz.shoot.day06;

import java.awt.image.BufferedImage;

/**
 * 直升机
 */
public class Helicopter extends FlyingObject {

    private int xSpeed;    //速度
    private int ySpeed;
    static BufferedImage[] deadImages;
    static BufferedImage liveImage;
    static {
        //加载大敌机存活图片
        liveImage = loadImage("helicopter.png");
        //加载大敌机死亡图片
        deadImages = new BufferedImage[6];
        for (int i = 0; i < deadImages.length; i++) {
            deadImages[i] = loadImage("boms"+i+".png");
        }
    }
    //构造方法
    public Helicopter(){
        super(100,113);

        this.xSpeed=2;
        this.ySpeed=2;
    }

    //移动行为
    public void step(){

        this.y += ySpeed;
        this.x += xSpeed;
        if(this.x<=0||this.x>= World.WIDTH-this.width)
            this.xSpeed *=-1;
    }
    private int bomsIndex = 0;
    //根据不同状态获取不同图片
    @Override
    public BufferedImage getImage() {
        if(isLife()){
            return liveImage;
        }else if (isDead()){
            BufferedImage image = deadImages[bomsIndex++];
            if(bomsIndex == deadImages.length){
                state = REMOVE;
            }
            return image;
        }
        return null;
    }
}
