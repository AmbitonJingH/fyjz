package com.fyjz.shoot.day06;
/*
 * @author  AmbitionJingH
 * @date  2023/7/25 11:46
 * @version 1.0
 */

import java.util.Timer;
import java.util.TimerTask;

public class DemoTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            //5000ms 5000毫秒后开始启动定时器
            //2000ms 每隔2000毫秒执行一次run()
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
                System.out.println("图片移动");
                System.out.println("画图片");
            }
        },5000,2000);
    }
}
