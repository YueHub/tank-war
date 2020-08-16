package cn.lcy.tankwar.control;

import cn.lcy.tankwar.service.GameService;
import cn.lcy.tankwar.ui.PanelGame;

public class GameControl {

    /**
     * 游戏逻辑层
     */
    private GameService gameservice;

    /**
     * 构造方法
     */
    public GameControl(GameService gameservice, PanelGame panelgame) {
        this.gameservice = gameservice;
    }

    /**
     * 游戏控制器调用逻辑层的游戏开始的方法
     */
    public void startGame() {
        this.gameservice.startGame();
    }

    /**
     * 游戏控制器调用逻辑层的向上移动的方法
     */
    public void up() {
        this.gameservice.up();
    }

    /**
     * 游戏控制器调用逻辑层的向下移动的方法
     */
    public void down() {
        this.gameservice.down();
    }

    /**
     * 游戏控制器调用逻辑层的向左移动的方法
     */
    public void left() {
        this.gameservice.left();
    }

    /**
     * 游戏控制器调用逻辑层的向右移动的方法
     */
    public void right() {
        this.gameservice.right();
    }

    /**
     * 游戏控制器调用逻辑层的添加坦克的方法
     */
    public void addTank() {
        this.gameservice.addTank();
    }

    /**
     * 暂时测试
     */
    public void coolTime() {
        this.gameservice.coolTime();
    }

    /**
     * 游戏控制器调用逻辑层的作弊的方法
     */
    public void cheat() {
        this.gameservice.cheat();
    }

    /**
     * 游戏控制器调用逻辑层的游戏暂停的方法
     */
    public void pause() {
        this.gameservice.pause();
    }

    /**
     * 游戏控制器调用逻辑层的开火方式一的方法
     */
    public void fire_1() {
        this.gameservice.fire_1();
    }

    /**
     * 游戏控制器调用逻辑层的开火方式二的方法
     */
    public void fire_2() {
        this.gameservice.fire_2();
    }

    /**
     * 游戏控制器调用逻辑层的开火方式三的方法
     */
    public void fire_3() {
        this.gameservice.fire_3();
    }

    /**
     * 游戏控制器调用逻辑层的开火方式四的方法
     */
    public void buryMine() {
        this.gameservice.buryMine();
    }

    /**
     * 游戏控制器调用逻辑层的关闭血槽的方法
     */
    public void closeExp() {
        this.gameservice.closeExp();
    }

    /**
     * 跳关卡
     */
    public void jumpOff() {
        this.gameservice.jumpOff();
    }
}