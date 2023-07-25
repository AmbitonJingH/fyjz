package com.fyjz.shoot.day06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
            System.out.println("子弹："+bullets.length);
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
        };
        //处理鼠标滑动事件
        this.addMouseMotionListener(mouseAdapter);
        this.addMouseListener(mouseAdapter);
        //定时器
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                bulletsAction();
                //敌机入场
                enemiesAction();
                //飞行物移动
                flyStepAction();
                //调用paint()
                repaint();
            }


        }, 10, 20);

    }

    int enemiesIndex = 1;
    private void enemiesAction() {

        if(enemiesIndex++%40==0){
            FlyingObject one = getOne();
            enemies = Arrays.copyOf(enemies,enemies.length+1);
            enemies[enemies.length-1] = one;
        }
       // System.out.println("长度"+enemies.length);
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
