package com.naruto.tankwar.ui;

import com.naruto.tankwar.dto.GameDto;
import com.naruto.tankwar.img.Img;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Tank {

    /**
     * 用来给内部类访问
     */
    private Tank thistank = this;

    /**
     * 内部类（用来绘制游戏的主要部分）
     */
    private paintMain paintMian;

    /**
     * 游戏数据
     */
    private GameDto dto;

    /**
     * 坦克的X坐标
     */
    private int tankX;

    /**
     * 坦克的Y坐标
     */
    private int tankY;

    /**
     * 坦克的移动速度
     */
    private static final int SPEED = 15;

    /**
     * 普通坦克的宽度
     */
    private static final int TANK_W = 60;

    /**
     * 普通坦克的高度
     */
    private static final int TANK_H = 60;

    /**
     * 坦克boss的宽度
     */
    private static final int BOSS_W = 180;

    /**
     * 坦克boss的高度
     */
    private static final int BOSS_H = 180;

    /**
     * 坦克的X移动步伐
     */
    private int xMove;

    /**
     * 坦克的Y移动步伐
     */
    private int yMove;

    /**
     * 坦克前一步的X坐标
     */
    private int oldX;

    /**
     * 坦克前一步的Y坐标
     */
    private int oldY;

    /**
     * 坦克的血量
     */
    private double exp;

    /**
     * 坦克是玩家控制还是计算机控制
     */
    private boolean player;

    /**
     * 是否是boss
     */
    private boolean boss;

    /**
     * 坦克是否被摧毁
     */
    private boolean tankDestroy;

    /**
     * 四个方向
     */
    private boolean bL = false, bU = false, bR = false, bD = false;

    /**
     * 枚举八个方向
     */
    public static enum Direction {L, U, R, D, STOP}

    ;

    /**
     * 初始化方向
     */
    private Direction dir = Direction.STOP;

    /**
     * 初始化方向
     */
    private Direction pdDir = Direction.U;

    /**
     * 得到一个随机数产生器
     */
    private static Random random = new Random();

    /**
     * 在每一个方法机器人坦克会走3到15之间的脚步
     */
    private int step = random.nextInt(12) + 3;

    /**
     * 坦克的ID号
     */
    private int tankID;

    /**
     * 坦克的种类
     */
    private int tankKind;


    /**
     * 坦克的生命数
     */
    private int life;

    /**
     * 构造方法
     */
    public Tank(GameDto dto, boolean player, boolean boss, int tankX, int tankY, Direction dir) {
        this.dto = dto;
        //初始化坦克的血量
        this.exp = 100;
        //初始化坦克是谁控制的
        this.player = player;
        //初始化坦克是否是boss
        this.boss = boss;
        this.tankX = tankX;
        this.tankY = tankY;
        //初始化机器人坦克的方向
        this.dir = dir;
        initTank();
    }

    /**
     * 初始化坦克的种类标识 和生命数
     */
    public void initTank() {
        if (this.isPlayer()) {
            this.tankKind = 0;
            this.life = 2;
        }
        if (!this.isPlayer() && !this.isBoss()) {
            this.tankKind = 1;
            this.life = 1;
        }
        if (this.isBoss()) {
            this.tankKind = 2;
            this.life = 2;
        }
        paintMian = new paintMain();
    }

    /**
     * 得到坦克的X坐标
     */
    public int getTankX() {
        return tankX;
    }

    /**
     * 设置坦克的X坐标
     */
    public void setTankX(int tankX) {
        this.tankX = tankX;
    }

    /**
     * 得到坦克的Y坐标
     */
    public int getTankY() {
        return tankY;
    }

    /**
     * 设置坦克的Y坐标
     */
    public void setTankY(int tankY) {
        this.tankY = tankY;
    }


    /**
     * 得到坦克是否被摧毁
     */
    public boolean isTankDestroy() {
        return tankDestroy;
    }

    /**
     * 设置坦克是否被摧毁
     */
    public void setTankDestroy(boolean tankDestroy) {
        this.tankDestroy = tankDestroy;
    }

    /**
     * 设置坦克左方向是否为true（即玩家有没有按左方向键）
     */
    public void setbL(boolean bL) {
        this.bL = bL;
    }

    /**
     * 设置坦克上方向是否为true（即玩家有没有按上方向键）
     */
    public void setbU(boolean bU) {
        this.bU = bU;
    }

    /**
     * 设置坦克左方向是否为true（即玩家有没有按右方向键）
     */
    public void setbR(boolean bR) {
        this.bR = bR;
    }

    /**
     * 设置坦克左方向是否为true（即玩家有没有按下方向键）
     */
    public void setbD(boolean bD) {
        this.bD = bD;
    }

    /**
     * 得到现在坦克的血量
     */
    public double getExp() {
        return exp;
    }

    /**
     * 设置现在坦克的血量
     */
    public void setExp(double exp) {
        this.exp = exp;
    }

    /**
     * 得到现在玩家的生命数
     */
    public int getLife() {
        return life;
    }

    /**
     * 设置玩家现在的生命数
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * 得到坦克是玩家控制还是计算机控制
     */
    public boolean isPlayer() {
        return player;
    }

    /**
     * 设置坦克由玩家还是计算机控制
     */
    public void setPlayer(boolean player) {
        this.player = player;
    }

    /**
     * 得到坦克是否是boss
     */
    public boolean isBoss() {
        return boss;
    }

    /**
     * 设置坦克是否是boss
     */
    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    /**
     * 得到坦克的矩形（用于碰撞检测）
     */
    public Rectangle getRect() {
        if (this.boss) {
            return new Rectangle(tankX, tankY, BOSS_W, BOSS_H);
        } else {
            return new Rectangle(tankX, tankY, TANK_W, TANK_H);
        }

    }

    /**
     * 坦克开火
     */
    public void fire() {
        if (this.tankDestroy) {
            return;
        }
        //初始炮弹的X坐标和Y坐标
        int x = 0;
        int y = 0;
        //如果是玩家坦克将坦克炮弹标识为玩家炮弹 并新创建一个炮弹到炮弹集合中
        if (this.isPlayer()) {
            x = this.tankX + TANK_W / 2 - Bullet.BULLET_W / 2;
            y = this.tankY + TANK_H / 2 - Bullet.BULLET_H / 2;
            Bullet bullet = new Bullet(x, y, this.dto, pdDir, true, false);
            this.dto.getBullet().add(bullet);
        }
        if (this.isBoss()) {
            x = this.tankX + BOSS_W / 2 - 140 / 2;
            y = this.tankY + BOSS_H / 2 - 110 / 2;
            Bullet bullet = new Bullet(x, y, this.dto, pdDir, false, true);
            this.dto.getBullet().add(bullet);
        }
        //如果是机器人坦克将坦克炮弹标识为机器人炮弹 并新创建一个炮弹到炮弹集合中
        if (!this.isPlayer() && !this.isBoss()) {
            x = this.tankX + TANK_W / 2 - Bullet.BULLET_W / 2;
            y = this.tankY + TANK_H / 2 - Bullet.BULLET_H / 2;
            Bullet bullet = new Bullet(x, y, this.dto, pdDir, false, false);
            this.dto.getBullet().add(bullet);
        }
    }

    /**
     * 扣血
     */
    public void reduceExp() {
        //如果是玩家坦克每次被打扣5滴血
        if (this.isPlayer()) {
            this.exp -= 5;
        }
        if (this.isBoss()) {
            this.exp -= 5;
        }
        //如果是机器人每次被打扣不同滴血（与开火方式有关）
        if (!this.isBoss() && !this.isPlayer())
            switch (this.dto.getFireID()) {
                case 0:
                    this.exp -= 15;
                    break;
                case 1:
                    this.exp -= 25;
                    break;
                case 2:
                    this.exp -= 40;
                    break;
                case 3:
                    this.exp -= 60;
                    break;
            }
    }

    /**
     * 坦克是否 撞击墙壁
     */
    public boolean hitWall(Wall wall) {
        if (this.getRect().intersects(wall.getRect())) {
            return true;
        }
        return false;
    }

    /**
     * 坦克碰撞是否碰撞墙壁们
     */
    public boolean hitWalls() {
        for (int i = 0; i < this.dto.getWalls().size(); i++) {
            if (this.hitWall(this.dto.getWalls().get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否碰撞到了坦克
     */
    public boolean hitTank(Tank tank) {
        if (this.getRect().intersects(tank.getRect()) && tank != thistank) {
            return true;
        }
        return false;
    }

    /**
     * 是否碰撞到了其他的所有坦克
     */
    public boolean hitTanks() {
        for (int i = 0; i < this.dto.getTanks().size(); i++) {
            if (this.hitTank(this.dto.getTanks().get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 坦克碰撞上地雷
     */
    public boolean hitLandMine(LandMine landmine) {
        if (this.getRect().intersects(landmine.getRect()) && !this.isPlayer()) {
            //设置碰上地雷的坦克摧毁
            this.setTankDestroy(true);
            //从集合中移除此坦克
            this.dto.getTanks().remove(this);
            //设置炮炸为需要
            this.dto.getBoom().setLive(true);
            //设置地雷为第五种开火方式
//			this.dto.setFireID(4);
            //设置地雷摧毁
            landmine.setLandMineDestroy(true);
            return true;
        }
        return false;
    }

    /**
     * 坦克碰撞上一群地雷
     */
    public boolean hitLandMines() {
        for (int i = 0; i < this.dto.getLandmines().size(); i++) {
            if (this.hitLandMine(this.dto.getLandmines().get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 不再移动
     */
    public void stay() {
        tankX = oldX;
        tankY = oldY;
    }

    /**
     * 八种不同的按键情况对应枚举的八个参数
     */
    public void locateDirection() {
        if (bL && !bU && !bR && !bD) dir = Direction.L;
        else if (!bL && bU && !bR && !bD) dir = Direction.U;
        else if (!bL && !bU && bR && !bD) dir = Direction.R;
        else if (!bL && !bU && !bR && bD) dir = Direction.D;
        else if (!bL && !bU && !bR && !bD) dir = Direction.STOP;
    }

    /**
     * 根据不同情况坦克做不同的移动
     */
    public void move() {
        if (!this.dto.isStartGame()) {
            return;
        }
        //如果是暂停坦克就不移动
        if (this.dto.isPause()) {
            return;
        }
        //如果是冷却时间并且是机器人那就不移动
        if (!this.isPlayer() && this.dto.isCoolTime()) {
            return;
        }

        oldX = tankX;
        oldY = tankY;

        //如果是机器人坦克  就根据随机方向移动
        if (!this.isPlayer()) {
            Direction[] dirs = Direction.values();
            if (step == 0) {
                step = random.nextInt(12) + 3;
                int rn = random.nextInt(dirs.length);
                //取得随机一个方法
                this.dir = dirs[rn];
                //向某一个方法走step步 step为3到15的随机数
            }

            step--;

            if (this.isBoss()) {
                if (random.nextInt(15) < 3) {
                    fire();
                }
            }
            if (!this.isBoss()) {
                if (random.nextInt(15) < 5) {
                    fire();
                }
            }

        }
        switch (dir) {
            case U:
                xMove = 0;
                yMove = -SPEED;
                if (this.isOverWindow(tankX + xMove, tankY + yMove)) {
                    return;
                }
                tankX += xMove;
                tankY += yMove;
                tankID = 0;
                break;
            case D:
                xMove = 0;
                yMove = SPEED;
                if (this.isOverWindow(tankX + xMove, tankY + yMove)) {
                    return;
                }
                tankX += xMove;
                tankY += yMove;
                tankID = 1;
                break;
            case STOP:
                break;
            case L:
                xMove = -SPEED;
                yMove = 0;
                if (this.isOverWindow(tankX + xMove, tankY + yMove)) {
                    return;
                }
                tankX += xMove;
                tankY += yMove;
                tankID = 2;
                break;
            case R:
                xMove = SPEED;
                yMove = 0;
                if (this.isOverWindow(tankX + xMove, tankY + yMove)) {
                    return;
                }
                tankX += xMove;
                tankY += yMove;
                tankID = 3;
                break;

        }
        if (this.dir != Direction.STOP) {
            this.pdDir = this.dir;
        }
    }

    /**
     * 是否越界
     */
    public boolean isOverWindow(int TankX, int TankY) {
        int game_W = GameDto.panelGame_W;
        int game_H = GameDto.panelGame_H;
        if (this.boss) {
            return TankX < 0 || TankX > game_W - BOSS_W || TankY < 0 || TankY > game_H - BOSS_H;
        } else {
            return TankX < 0 || TankX > game_W - TANK_W || TankY < 0 || TankY > game_H - TANK_H;
        }

    }

    /**
     * 内部类  用来绘制游戏主要部分
     */
    private class paintMain {

        /**
         * 绘制坦克
         */
        public void drawTank(Graphics g) {
            this.drawWalls(g);
            //如果坦克已经被摧毁那么就不绘制坦克
            if (isTankDestroy()) {
                return;
            } else {
                //如果没有被摧毁那么就绘制坦克和血槽
                g.drawImage(Img.TANK[tankKind][tankID], tankX, tankY, null);
                this.drawExp(g);
                //坦克移动  放在这里是因为要移动要不停刷新
                move();
                //如果撞墙了或撞到其他坦克  就不能再移动
                if (hitWalls() || hitTanks() || hitLandMines()) {
                    stay();
                }
            }
        }

        /**
         * 绘制血槽
         */
        public void drawExp(Graphics g) {
            if (dto.isCloseExp()) {
                return;
            }
            //血槽的X坐标
            int rect_x = 0;
            //血槽的Y坐标
            int rect_y = 0;
            //血槽的宽度
            int rect_W = 0;
            //血槽的高度
            int rect_H = 0;
            //血槽框架的x坐标
            int rectframe_x = 0;
            //血槽框架的y坐标
            int rectframe_y = 0;
            //血槽框架的长度
            int rectframe_W = 0;
            //血槽框架的高度
            int rectframe_H = 0;
            if (isBoss()) {
                Img.rectframe = new ImageIcon("skin/exp/rectframe.png").getImage();
                Img.rect = new ImageIcon("skin/exp/rect.png").getImage();
                rect_x = tankX;
                rect_y = tankY - 50;
                rect_W = 185;
                rect_H = 7;
                rectframe_x = rect_x - 70;
                rectframe_y = rect_y - 20;
                rectframe_W = 290;
                rectframe_H = 60;
            }
            if (!isBoss()) {
                Img.rectframe = new ImageIcon("skin/tank/rectframe.png").getImage();
                Img.rect = new ImageIcon("skin/tank/rect.png").getImage();
                rect_x = tankX;
                rect_y = tankY - 20;
                rect_W = 74;
                rect_H = 6;
                rectframe_x = rect_x - 25;
                rectframe_y = rect_y - 10;
                rectframe_W = 110;
                rectframe_H = 35;
            }
            //现在坦克血量占的比例
            double percent = exp / 100;
            //绘制血槽框架
            g.drawImage(Img.rectframe, rectframe_x, rectframe_y, rectframe_W, rectframe_H, null);
            //根据血量比例绘制血槽
            g.drawImage(Img.rect, rect_x, rect_y, rect_x + (int) (rect_W * percent), rect_y + rect_H, 0, 0, rect_W, rect_H, null);
        }

        /**
         * 绘制炮弹
         */
        public void drawBullet(Graphics g) {
            for (int i = 0; i < dto.getBullet().size(); i++) {
                //炮弹打到机器人身上后的处理
                dto.getBullet().get(i).hitTanks(dto.getTanks());
                //炮弹打到玩家坦克身上后的处理
                dto.getBullet().get(i).hitTank(thistank);
                //炮弹打到墙壁上的处理
                dto.getBullet().get(i).hitWalls(dto.getWalls());
                //绘制炮弹
                dto.getBullet().get(i).drawBullet(g);
            }
        }

        /**
         * 绘制地雷
         */
        public void drawLandMine(Graphics g) {
            for (int i = 0; i < dto.getLandmines().size(); i++) {
                dto.getLandmines().get(i).drawLandMine(g);
            }
        }

        /**
         * 绘制机器人坦克
         */
        public void drawTanks(Graphics g) {
            //如果机器人被团灭
            if (dto.getTanks().size() == 0) {
                //升级
                dto.setLevel(dto.getLevel() + 1);
                //dto.setStart(false);
                //刷新地图
                dto.getMap().removeMap();
                dto.setMap(new Map(dto));
                dto.getMap().creatMap();

                //刷新地图的同时  让玩家弹坦克回到家原点
                tankX = 600;
                tankY = 600;

                //如果打到第四关（Boss关
                if (dto.getLevel() == 4) {
                    //删除四个小兵
                    for (int i = 0; i < dto.getTanks().size(); i++) {
                        dto.getTanks().get(i).setTankDestroy(true);
                    }
                    //添加tank Boss
                    dto.getTanks().add(new Tank(dto, false, true, 500, 660 - 180, Tank.Direction.U));
                }
                //如果还没打到第四关
                if (dto.getLevel() != 4) {
                    //添加四个小兵
                    for (int i = 0; i < 4; i++) {
                        dto.getTanks().add(new Tank(dto, false, false, 500 + i * 60, 0, Tank.Direction.D));
                    }
                }
            }
            for (int i = 0; i < dto.getTanks().size(); i++) {
                dto.getTanks().get(i).paintMian.drawTank(g);
            }
        }

        /**
         * 绘制所有墙壁
         */
        public void drawWalls(Graphics g) {
            for (int i = 0; i < dto.getWalls().size(); i++) {
                dto.getWalls().get(i).drawWall(g);
            }
        }

        /**
         * 绘制爆炸
         */
        public void drawBoom(Graphics g) {
            if (!dto.isStartGame()) {
                return;
            }
            dto.getBoom().drawBoom(g);
        }
    }

    /**
     * 调用此paint方法 可刷新与坦克有关的多个图片（炮弹 爆炸等）
     */
    public void paint(Graphics g) {
        // 绘制玩家坦克
        paintMian.drawTank(g);
        // 绘制机器人
        paintMian.drawTanks(g);
        // 绘制炮弹
        paintMian.drawBullet(g);
        // 绘制地雷
        paintMian.drawLandMine(g);
        // 绘制爆炸
        paintMian.drawBoom(g);
        // 绘制信息
        g.drawString("坦克数量：" + this.dto.getTanks().size(), 0, 40);
    }
}