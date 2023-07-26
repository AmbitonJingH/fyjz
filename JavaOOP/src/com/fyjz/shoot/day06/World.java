package com.fyjz.shoot.day06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//JPanel 是 Java图形用户界面(GUI)工具包swing中的面板容器类
public class World extends JPanel {

    public static final int WIDTH = 450;//窗口的宽
    public static final int HEIGHT = 650;//窗口的高
    private Sky sky = new Sky();
    private Hero hero = new Hero();
    private Airplane airplane = new Airplane();
    private BigAirplane bigAirplane = new BigAirplane();
    private Helicopter helicopter = new Helicopter();
    private FlyingObject[] enemies={};
    private Bullet[] bullets = {};
    public static final int START = 0;
    public static final int PAUSE = 1;
    public static final int OVER = 2;
    public static final int VICTORY = 3;
    public static final int RUNNING = 4;
    private int state = START;//默认状态为启动
    public static BufferedImage start;
    public static BufferedImage over;
    public static BufferedImage pause;
    public static BufferedImage victory;

    static{
        start = FlyingObject.loadImage("start2.png");
        pause = FlyingObject.loadImage("pause.png");
        over = FlyingObject.loadImage("gameover.png");
        victory = FlyingObject.loadImage("victory.png");
    }

    private FlyingObject getOne(){
        Random random = new Random();
        int n = random.nextInt(20);
        if(n<7){
            return new Airplane();
        } else if (n<13) {
            return new BigAirplane();
        }else {
            return new Helicopter();
        }
    }
    //英雄机子弹入口
    private int bulletIndex = 1;
    private void bulletsAction(){
        if(bulletIndex++%30==0){
            Bullet[] heroBullet = hero.getHeroBullet();
            bullets = Arrays.copyOf(bullets,heroBullet.length+bullets.length);
            System.arraycopy(heroBullet,0,bullets,bullets.length-heroBullet.length,heroBullet.length);
            //System.out.println("子弹："+bullets.length);
        }
    }


    //游戏入口
    public void action(){
        //鼠标监听器
        MouseAdapter mouseAdapter = new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent e) {
                //鼠标移动事件
                int x = e.getX();
                int y = e.getY();
                hero.heroStep(x,y);
            }
            //鼠标点击事件
            public void mouseClicked(MouseEvent e){
                if(state==START){
                    //恢复游戏初始化
                    enemies = new FlyingObject[0];
                    bullets = new Bullet[0];
                    hero = new Hero();
                    sky = new Sky();
                    state=RUNNING;
                }
                if(state==OVER){
                    state=START;
                }
            }
            //鼠标移出事件
            public void mouseExited(MouseEvent e){
                if(state==RUNNING){
                    state=PAUSE;
                }
            }
            //鼠标移入事件
            public void mouseEntered(MouseEvent e){
                if(state==PAUSE){
                    state=RUNNING;
                }
            }
        };
        //处理鼠标滑动事件
        this.addMouseMotionListener(mouseAdapter);
        this.addMouseListener(mouseAdapter);
        //定时器
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(state == RUNNING) {
                    bulletsAction();
                    //敌机入场
                    enemiesAction();
                    //飞行物移动
                    flyStepAction();
                    //子弹碰撞敌机
                    heroBulletAndEnemiesBangAction();
                    //英雄机和敌机碰撞
                    heroAndEnemyBangAction();
                    //处理内存泄露
                    checkFlyObjectOutAction();
                    //游戏结束
                    checkGamenOverAction();
                }
                //调用paint()
                repaint();
            }


        }, 10, 10);

    }

    private void checkFlyObjectOutAction() {
        int liveEnemiesIndex = 0;
        FlyingObject[] flyingObjects = new FlyingObject[enemies.length];
        for (int i = 0; i < enemies.length; i++) {
            FlyingObject enemy = enemies[i];
            if(!enemy.flyingObjectOut()&&!enemy.isRemove()){
                flyingObjects[liveEnemiesIndex++] = enemy;
            }
        }
        enemies = Arrays.copyOf(flyingObjects,liveEnemiesIndex);
        //处理子弹内存泄露问题
        int liveBulletIndex = 0;
        Bullet[] liveBullets = new Bullet[bullets.length];
        for (int i = 0; i < bullets.length; i++) {
            Bullet bullet = bullets[i];
            if(!bullet.flyingObjectOut()&&!bullet.isRemove()){
                liveBullets[liveBulletIndex++] = bullet;
            }
        }
        bullets = Arrays.copyOf(liveBullets,liveBulletIndex);
    }

    private void checkGamenOverAction(){
        if(hero.getLife()<=0){
            state=OVER;

        }
    }
        private void heroAndEnemyBangAction() {
        for (int i = 0; i < enemies.length; i++) {
            FlyingObject enemy = enemies[i];
            if(hero.isLife()&&enemy.isLife()&&hero.hit(enemy)){
                enemy.goDie();
                //英雄机减少生命值
                hero.subLife();
                //清空双倍子弹数量
                hero.clearDoubleFire();
                if(hero.getLife()==0)
                    hero.goDie();
            }
        }
    }

    private void heroBulletAndEnemiesBangAction() {
        for (int i = 0; i < bullets.length; i++) {
            Bullet bullet = bullets[i];
            for (int j = 0; j < enemies.length; j++) {
                FlyingObject enemy = enemies[j];
                if(bullet.isLife()&&enemy.isLife()&&bullet.hit(enemy)){
                    bullet.goDie();
                    enemy.goDie();
                }
            }
        }
    }


    int enemiesIndex = 1;
    private void enemiesAction() {

        if(enemiesIndex++%40==0){
            FlyingObject one = getOne();
            enemies = Arrays.copyOf(enemies,enemies.length+1);
            enemies[enemies.length-1] = one;
        }
        System.out.println("长度"+enemies.length);
    }

    private void flyStepAction() {
        sky.step();
        //遍历敌机
        for (int i = 0; i < enemies.length; i++) {
            FlyingObject enemy = enemies[i];
            enemy.step();
        }
        //遍历英雄机子弹
        for (int i = 0; i < bullets.length; i++) {
            bullets[i].step();

        }
        System.out.println("子弹:"+bullets.length);
//        airplane.step();
//        bigAirplane.step();
//        helicopter.step();
//        bullet.step();
    }

    public void paint(Graphics graphics){
        sky.pointObject(graphics);
        hero.pointObject(graphics);
        //遍历敌机
        for (int i = 0; i < enemies.length; i++) {
            FlyingObject enemy = enemies[i];
            enemy.pointObject(graphics);
        }
        //遍历英雄机子弹
        for (int i = 0; i < bullets.length; i++) {
            bullets[i].pointObject(graphics);

        }
        Font font = new Font("宋体",Font.TYPE1_FONT,18);
        graphics.setFont(font);
        //设置画笔颜色
        graphics.setColor(Color.red);
        //画字符串
        graphics.drawString("生命值:"+hero.getLife(),10,20);
        graphics.drawString("双倍子弹数量:"+hero.getFire(),10,40);
        if(hero.getFire()>0){
            graphics.drawString("状态:双倍火力",10,60);
        }else {
            graphics.drawString("状态:单倍火力",10,60);
        }
        graphics.drawString("得分:",10,80);
        switch (state){
            case START:graphics.drawImage(start,0,0,null);break;
            case PAUSE:graphics.drawImage(pause,0,0,null);break;
            case OVER:graphics.drawImage(over,0,0,null);break;
            case VICTORY:graphics.drawImage(victory,0,0,null);break;
        }
//        bullet.pointObject(graphics);
//        airplane.pointObject(graphics);
//        bigAirplane.pointObject(graphics);
//        helicopter.pointObject(graphics);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();//创建窗口
        World world = new World();//面板
        //给窗口添加面板
        frame.add(world);
        frame.setSize(WIDTH,HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);//居中
        world.action();
    }


}
