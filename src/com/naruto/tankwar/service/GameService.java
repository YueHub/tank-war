package com.naruto.tankwar.service;

import com.naruto.tankwar.dto.GameDto;
import com.naruto.tankwar.ui.LandMine;
import com.naruto.tankwar.ui.Map;
import com.naruto.tankwar.ui.PanelGame;
import com.naruto.tankwar.ui.Tank;

public class GameService {
	
	/**
	 * 游戏数据传输层
	 */
	private GameDto dto ;
	
	/**
	 * 构造方法
	 */
	public GameService(GameDto dto,PanelGame panelgame){
		this.dto = dto;
	}
	
	
	/**
	 * 游戏开始！
	 */
	public void startGame() {
		this.dto.setStartGame(true);
	}
	
	/**
	 * 坦克向上移动
	 */
	public void up() {
		this.dto.getTank().setbU(true);
	}

	/**
	 * 坦克向下移动
	 */
	public void down() {
		this.dto.getTank().setbD(true);
	}

	/**
	 * 坦克向左移动
	 */
	public void left() {
		this.dto.getTank().setbL(true);
	}

	/**
	 * 坦克向右移动
	 */
	public void right() {
		this.dto.getTank().setbR(true);
	}
	
	/**
	 * 添加一辆机器人坦克到机器人坦克集合中
	 */
	public void addTank() {
		this.dto.getTanks().add(new Tank(this.dto,false,false,200,200,Tank.Direction.D));
	}
	
	/**
	 * 冷却时间
	 */
	public void coolTime() {
		this.dto.setCoolTime(!this.dto.isCoolTime());
	}
	
	/**
	 *作弊键 
	 */
	public void cheat() {
		//玩家坦克血量增加20
		this.dto.getTank().setExp(this.dto.getTank().getExp()+20);
	}

	public void pause() {
		//每按一次就设置暂停属性没原来相反的
		this.dto.setPause(!this.dto.isPause());
	}
	
	/**
	 * 玩家坦克开火方式一
	 */
	public void fire_1() {
		this.dto.setFireID(0);
		this.dto.getTank().fire();
	}
	
	/**
	 * 玩家坦克开火方式二
	 */
	public void fire_2() {
		this.dto.setFireID(1);
		this.dto.getTank().fire();
	}
	
	/**
	 * 玩家坦克开火方式三
	 */
	public void fire_3() {
		this.dto.setFireID(2);
		this.dto.getTank().fire();
	}
	
	/**
	 * 玩家坦克开方式四
	 */
	public void buryMine() {
		this.dto.getLandmines().add(new LandMine(this.dto.getTank().getTankX(),this.dto.getTank().getTankY(),this.dto)); 
	}
	
	/**
	 * 关闭血槽
	 */
	public void closeExp() {
		this.dto.setCloseExp(!this.dto.isCloseExp());
	}

	/**
	 * 跳关卡
	 */
	public void jumpOff() {
		//升级
		this.dto.setLevel(this.dto.getLevel()+1);
		//刷新地图
		this.dto.getMap().removeMap();
		this.dto.setMap(new Map(dto));
		this.dto.getMap().creatMap();
		//如果打到第四关（Boss关
		if(dto.getLevel() == 4){
			//删除所有小兵
			for(int i = 0;i<dto.getTanks().size();i++){
				dto.getTanks().get(i).setTankDestroy(true);
			}
			//添加tank Boss
			dto.getTanks().add(new Tank(dto,false,true,500,660-180,Tank.Direction.U));
		}
		//如果还没打到第四关
		if(dto.getLevel() != 4){
			//删除所有小兵
			for(int i = 0;i<dto.getTanks().size();i++){
				dto.getTanks().get(i).setTankDestroy(true);
			}
			//添加四个小兵
		for(int i = 0 ;i<4;i++){
			dto.getTanks().add(new Tank(dto,false,false,500+i*60,0,Tank.Direction.D));
		}
		}
	}
}
