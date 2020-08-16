package cn.lcy.tankwar.ui;

import cn.lcy.tankwar.img.Img;
import cn.lcy.tankwar.dto.GameDto;

import java.awt.*;

public class LandMine {

    /**
     * 游戏数据传输层
     */
    private GameDto dto;

    /**
     * 地雷的宽度
     */
    private int landMine_W = Img.landmine.getWidth(null);

    /**
     * 地雷的高度
     */
    private int landMine_H = Img.landmine.getHeight(null);

    /**
     * 地雷的X坐标
     */
    private int landMineX;

    /**
     * 地雷的Y坐标
     */
    private int landMineY;

    /**
     * 地雷是否摧毁
     */
    private boolean landMineDestroy = false;

    /**
     * 构造方法
     */
    public LandMine(int landMineX, int landMineY, GameDto dto) {
        this.landMineX = landMineX;
        this.landMineY = landMineY;
        this.dto = dto;
    }

    /**
     * 得到地雷是否已经摧毁
     */
    public boolean isLandMineDestroy() {
        return landMineDestroy;
    }

    /**
     * 设置地雷是否被摧毁
     */
    public void setLandMineDestroy(boolean landMineDestroy) {
        this.landMineDestroy = landMineDestroy;
    }

    /**
     * 碰撞检测
     */
    public Rectangle getRect() {
        return new Rectangle(landMineX, landMineY, landMine_W, landMine_H);
    }

    /**
     * 绘制地雷
     */
    public void drawLandMine(Graphics g) {
        if (this.landMineDestroy) {
            this.dto.getLandmines().remove(this);
            return;
        }
        g.drawImage(Img.landmine, landMineX, landMineY, null);
    }
}