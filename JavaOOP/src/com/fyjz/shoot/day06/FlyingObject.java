package com.fyjz.shoot.day06;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class FlyingObject {

    protected int x;    //x坐标
    protected int y;    //y坐标
    protected int width;   //宽度
    protected int height;   //高度
    public static final int LIFE=1;//存活状态
    public static final int DEAD=2;//死亡状态
    public static final int REMOVE=3;//移除状态
    public int state = LIFE;//默认存活状态

    public FlyingObject(int width,int height){
        Random random = new Random();
        this.x = random.nextInt((World.WIDTH-this.width)-100);
        this.height = height;
        this.y = -height;
        this.width = width;


    }

    public FlyingObject(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    //飞行物移动的抽象方法
    public abstract void step();

    public static BufferedImage loadImage(String imageName){
        try {
            //使用ImageIO提供的静态方法read()去根据路径读取图片，返回图片类型
            BufferedImage image = ImageIO.read(FlyingObject.class.getResource("/"+imageName));
            return image;
        }catch (Exception e){
            throw new RuntimeException("图片加载失败");
        }
    }
    //判断是否存活
    public boolean isLife(){
        return state == LIFE;
    }
    //判断是否死亡
    public boolean isDead(){
        return state == DEAD;
    }
    //判断是否移除
    public boolean isRemove(){
        return state == REMOVE;
    }
    //获取图片
    public abstract BufferedImage getImage();
    //画图片（根据坐标画图）
    //Graphics:画笔
    public void pointObject(Graphics graphics){
        graphics.drawImage(getImage(),this.x,this.y,null);

    }

    //飞行物碰撞
    public boolean hit(FlyingObject other){
        int x = other.x;
        int y = other.y;
        int x1 = this.x - other.width;
        int y1 = this.y - other.height;
        int x2 = this.x + this.width;
        int y2 = this.y + this.height;
        return (x1<=x&&x<=x2)&&(y1<=y&&y<=y2);
    }
    //飞行物爆照
    public void goDie(){
        state = DEAD;
    }
    //判断飞行物是否越界
    public abstract boolean flyingObjectOut();
}
