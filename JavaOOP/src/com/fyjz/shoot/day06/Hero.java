package com.fyjz.shoot.day06;

import java.awt.image.BufferedImage;

/*
   英雄机
 */
public class Hero extends FlyingObject {

    private int doubleFire;//火力值
    static BufferedImage[] images;
    private int life;//生命
    static {
        images = new BufferedImage[2];
        for (int i = 0; i < images.length; i++) {
            images[i] = loadImage("hero"+i+".png");
        }
    }
    //构造方法
    public Hero(){
        super(15,15,110,157);
        this.doubleFire = 100;
        this.life = 3;
    }

    //移动行为
    //英雄机随着鼠标移动而移动
    public void step(){

    }
    public void setLife(int i){
        this.life = i;
    }
    private int heroIndex = 0;
    //根据不同状态获取不同图片
    @Override
    public BufferedImage getImage() {
        if(isLife()){
            BufferedImage image = images[heroIndex++ % images.length];
            return image;
        }else if(isDead()){
            state = REMOVE;
        }
        return null;
    }



    public void heroStep(int x,int y){
        this.x = x-width/2;
        this.y = y-height/2;
    }
    //获取英雄机子弹
    public Bullet[] getHeroBullet(){
        int xStep = this.width/4;
        if(doubleFire>0){
            Bullet[] bullets = new Bullet[2];
            //从英雄机的1/4处发射
            bullets[0] = new Bullet(this.x+xStep-12,this.y+5);
            //从英雄机的3/4处发射
            bullets[1] = new Bullet(this.x+3*xStep,this.y+5);
            //双倍火力
            this.doubleFire -= 2;
            return bullets;
        }else {
            //单倍火力
            Bullet[] bullets = new Bullet[1];
            bullets[0] = new Bullet(this.x+2*xStep-9,this.y-15);
            return bullets;
        }
    }

    //获取英雄机的生命值
    public int getLife(){
        return this.life;
    }
    //获取英雄机的火力值
    public int getFire(){
        return this.doubleFire;
    }

    //英雄机减生命值
    public void subLife() {
        this.life--;
    }

    public void clearDoubleFire() {
        this.doubleFire = 0;
    }
    @Override
    public boolean flyingObjectOut() {
        return false;
    }
}
