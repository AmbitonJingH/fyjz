package com.fyjz.shoot.day06;

import java.awt.image.BufferedImage;

/**
 * 大敌机
 */
public class BigAirplane extends FlyingObject {

    private int speed;    //速度
    static BufferedImage[] deadImages;
    static BufferedImage liveImage;
    static {
        //加载大敌机存活图片
        liveImage = loadImage("bigAriplane.png");
        //加载大敌机死亡图片
        deadImages = new BufferedImage[6];
        for (int i = 0; i < deadImages.length; i++) {
            deadImages[i] = loadImage("boms"+i+".png");
        }
    }
    //构造方法
    public BigAirplane(){
        super(115,103);
        this.speed=2;
    }

    //移动行为
    public void step(){
        this.y += speed;
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
