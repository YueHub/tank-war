package com.naruto.tankwar.ui;

import com.naruto.tankwar.dto.GameDto;
import com.naruto.tankwar.img.Img;

import java.awt.*;
import java.util.ArrayList;

public class Bullet {

    /**
     * 游戏数据传输层
     */
    private GameDto dto;

    /**
     * 炮弹是否摧毁
     */
    private boolean bulletDestroy = false;

    /**
     * 炮弹的X坐标
     */
    private int bulletX;

    /**
     * 炮弹的Y坐标
     */
    private int bulletY;

    /**
     * 方向枚举
     */
    Tank.Direction dir;

    /**
     * 是玩家的还是机器人的bullet
     */
    private boolean playerb;

    /**
     * 是不是boss的bullet
     */
    private boolean bossb;

    /**
     * 开火方式的ID
     */
    private int fireID;

    /**
     * 炮弹的宽度
     */
    public static int BULLET_W;

    /**
     * 炮弹的高度
     */
    public static int BULLET_H;

    /**
     * 炮弹X方向移动的速度
     */
    private static final int XSPEED = 30;

    /**
     * 炮弹Y方向移动的速度
     */
    private static final int YSPEED = 30;

    /**
     * 构造方法
     */
    public Bullet(int bulletX, int bulletY, GameDto dto, Tank.Direction dir, boolean playerb, boolean bossb) {
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.dto = dto;
        this.dir = dir;
        this.playerb = playerb;
        this.bossb = bossb;
        this.initBullet();
    }

    /**
     * 初始化炮弹
     */
    public void initBullet() {
        //得到炮弹的ID
        fireID = this.dto.getFireID();
        //得到此时炮弹的长和宽
        BULLET_W = Img.BULLET[fireID].getWidth(null);
        BULLET_H = Img.BULLET[fireID].getHeight(null);
    }

    /**
     * 设置炮弹是否为玩家的炮弹
     */
    public void setPlayerb(boolean playerb) {
        this.playerb = playerb;
    }

    /**
     * 返回炮弹的X坐标
     */
    public int getBulletX() {
        return bulletX;
    }

    /**
     * 设置炮弹的X坐标
     */
    public void setBulletX(int bulletX) {
        this.bulletX = bulletX;
    }

    /**
     * 返回炮弹的Y坐标
     */
    public int getBulletY() {
        return bulletY;
    }

    /**
     * 设置炮弹的Y坐标
     */
    public void setBulletY(int bulletY) {
        this.bulletY = bulletY;
    }

    /**
     * 得到炮弹是否还存在
     */
    public boolean isbulletDestroy() {
        return bulletDestroy;
    }

    /**
     * 得到的炮弹的矩形（用于碰撞检测）
     */
    public Rectangle getRect() {
        return new Rectangle(bulletX, bulletY, BULLET_W, BULLET_H);
    }

    /**
     * 子弹打到了坦克上
     */
    public boolean hitTank(Tank tank) {
        if (this.getRect().intersects(tank.getRect()) && !this.isbulletDestroy() && tank.isPlayer() != this.playerb) {
            //传入爆炸点的X坐标
            this.dto.getBoom().setBoomX(tank.getTankX());
            //传入爆炸点的Y坐标
            this.dto.getBoom().setBoomY(tank.getTankY());
            //设置炮弹为摧毁状态
            this.bulletDestroy = true;
            //设置爆炸为需要
            this.dto.getBoom().setLive(true);
            //坦克掉血
            tank.reduceExp();
            //如果坦克血量小于0  坦克的生命数减一
            if (tank.getExp() <= 0) {
                tank.setLife(tank.getLife() - 1);
                tank.setTankX(400);
                tank.setTankY(600);
                tank.setExp(100);
            }
            if (tank.getLife() == 0) {
                tank.setTankDestroy(true);
                this.dto.getTanks().remove(tank);
            }
            return true;
        }
        return false;
    }

    /**
     * 炮弹打到了坦克集合上
     */
    public boolean hitTanks(ArrayList<Tank> tanks) {
        for (int i = 0; i < tanks.size(); i++) {
            if (this.hitTank(tanks.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 炮弹打到一个墙壁上（用于下面的调用）
     */
    public boolean hitWall(Wall wall) {
        if (this.getRect().intersects(wall.getRect())) {
            //墙壁掉血
            wall.reduceExp();
            this.bulletDestroy = true;
            return true;
        }
        return false;
    }

    /**
     * 炮弹打到了墙壁上
     */
    public boolean hitWalls(ArrayList<Wall> walls) {
        for (int i = 0; i < walls.size(); i++) {
            if (this.hitWall(walls.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 移动炮弹的方法（参考于马士兵）
     */
    public void moveBullet() {
        if (this.dto.isPause()) {
            return;
        }
        switch (dir) {
            case L:
                bulletX -= XSPEED;
                break;
            case U:
                bulletY -= YSPEED;
                break;
            case R:
                bulletX += XSPEED;
                break;
            case D:
                bulletY += YSPEED;
                break;
            case STOP:
                break;
        }
        //增加100 以免大炮弹在边界无法发射
        if (bulletX < -100 || bulletY < -100 || bulletX > 1300 || bulletY > 800) {
            bulletDestroy = true;
        }

    }

    /**
     * 绘制炮弹
     */
    public void drawBullet(Graphics g) {
        //如果炮弹已经摧毁那就不绘制
        if (bulletDestroy) {
            this.dto.getBullet().remove(this);
            return;
        }
        //如果是玩家炮弹那么就可以有各种炮弹
        if (this.playerb) {
            g.drawImage(Img.BULLET[fireID], bulletX, bulletY, null);
        }
        //如果是boss的炮弹  则有最后一种炮击
        if (this.bossb) {
            g.drawImage(Img.BULLET[3], bulletX, bulletY, null);
        }
        //如果是普通机器人的炮弹   则只能有第一种炮弹
        if (!this.playerb && !this.bossb) {
            g.drawImage(Img.BULLET[0], bulletX, bulletY, null);
        }
        moveBullet();
    }
}