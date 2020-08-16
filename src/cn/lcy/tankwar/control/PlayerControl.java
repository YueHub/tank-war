package cn.lcy.tankwar.control;

import cn.lcy.tankwar.dto.GameDto;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class PlayerControl extends KeyAdapter {

    /**
     * 游戏控制器
     */
    private GameControl gamecontrol;

    /**
     * 游戏数据传输层
     */
    private GameDto dto;

    /**
     * 构造方法
     */
    public PlayerControl(GameControl gamecontrol, GameDto dto) {
        this.gamecontrol = gamecontrol;
        this.dto = dto;
    }

    /**
     * 玩家控制器（键盘事件）
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                this.gamecontrol.startGame();
                break;
            case KeyEvent.VK_W:
                this.gamecontrol.up();
                break;
            case KeyEvent.VK_S:
                this.gamecontrol.down();
                break;
            case KeyEvent.VK_A:
                this.gamecontrol.left();
                break;
            case KeyEvent.VK_D:
                this.gamecontrol.right();
                break;
            //开火方式一
            case KeyEvent.VK_J:
                this.gamecontrol.fire_1();
                break;
            //开火方式二
            case KeyEvent.VK_K:
                this.gamecontrol.fire_2();
                break;
            //开火方式三
            case KeyEvent.VK_L:
                this.gamecontrol.fire_3();
                break;
            //开火方式四
            case KeyEvent.VK_I:
                this.gamecontrol.buryMine();
                break;
            //添加机器人
            case KeyEvent.VK_F1:
                this.gamecontrol.addTank();
                break;
            //作弊键
            case KeyEvent.VK_F2:
                this.gamecontrol.cheat();
                break;
            //冷却时间
            case KeyEvent.VK_F3:
                this.gamecontrol.coolTime();
                break;
            //跳关卡(作弊)
            case KeyEvent.VK_F4:
                this.gamecontrol.jumpOff();
                break;
            //暂停
            case KeyEvent.VK_ENTER:
                this.gamecontrol.pause();
                break;
            //关闭血槽
            case KeyEvent.VK_SHIFT:
                this.gamecontrol.closeExp();
                break;

            default:
                break;
        }
        this.dto.getTank().locateDirection();
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                this.dto.getTank().setbU(false);
                break;
            case KeyEvent.VK_S:
                this.dto.getTank().setbD(false);
                break;
            case KeyEvent.VK_A:
                this.dto.getTank().setbL(false);
                break;
            case KeyEvent.VK_D:
                this.dto.getTank().setbR(false);
                break;
        }
        this.dto.getTank().locateDirection();
    }
}