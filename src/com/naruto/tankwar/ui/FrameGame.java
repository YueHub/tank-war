package com.naruto.tankwar.ui;

import com.naruto.tankwar.dto.GameDto;

import javax.swing.*;

public class FrameGame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 游戏数据传输层
     */
    private GameDto dto;

    /**
     * 游戏界面
     */
    private PanelGame panelgame;

    /**
     * 游戏线程
     */
    private Thread gameThread;

    /**
     * 构造方法
     */
    public FrameGame(PanelGame panelgame, GameDto dto) {
        this.dto = dto;
        this.panelgame = panelgame;
        //设置窗口名称
        this.setTitle("java版坦克大战");
        //设置关闭按钮
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口不可改变大小
        this.setResizable(false);
        //设置窗口位置和大小
        this.setBounds(50, 10, 1200 + 6, 660 + 28);
        //设置默认Panel（关联Panel）
        this.setContentPane(panelgame);
        //设置窗口可见
        this.setVisible(true);
        //得到panelgame的宽度和高度
        GameDto.panelGame_W = panelgame.getWidth();
        GameDto.panelGame_H = panelgame.getHeight();

        //加载机器人坦克
        this.loadTankComputer();

        //加载地图
        this.loadMap();

        //启动刷新不断画面的线程
        this.reFreshThread();
    }

    /**
     * 加载机器人坦克
     */
    public void loadTankComputer() {
        for (int i = 0; i < 4; i++) {
            this.dto.getTanks().add(new Tank(this.dto, false, false, 420 + i * 60, 0, Tank.Direction.D));
        }
    }

    /**
     * 加载游戏地图
     */
    public void loadMap() {
        //创建出一张游戏地图对象
        this.dto.setMap(new Map(this.dto));
        this.dto.getMap().creatMap();
    }

    /**
     * 刷新线程
     */
    public void reFreshThread() {
        //启动制动刷新画面的线程
        this.gameThread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                        panelgame.repaint();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        this.gameThread.start();
    }
}