package com.naruto.tankwar.dto;

import java.util.ArrayList;
import com.naruto.tankwar.ui.Boom;
import com.naruto.tankwar.ui.Bullet;
import com.naruto.tankwar.ui.LandMine;
import com.naruto.tankwar.ui.Map;
import com.naruto.tankwar.ui.Tank;
import com.naruto.tankwar.ui.Wall;

public class GameDto  {
	
	/**
	 * panelgame的宽度
	 */
	public static int panelGame_W;
	
	/**
	 * panelgame的高度
	 */
	public static int panelGame_H;

	/**
	 * 玩家坦克
	 */
	private Tank mytank;

	/**
	 * 电脑坦克集合
	 */
	private ArrayList<Tank> tanks;

	/**
	 * 炮弹集合
	 */
	private ArrayList<Bullet> bullet;
	
	/**
	 * 地雷集合
	 */
	private ArrayList<LandMine> landmines;
	
	/**
	 * 墙壁
	 */
	private ArrayList<Wall> walls;
	
	/**
	 * 爆炸
	 */
	private Boom boom;
	
	/**
	 * 存储地图的二维数组（实际存的是地图每张图片的id）
	 */
	private int[][] numMap ;
		
	/**
	 * 游戏地图
	 */
	private Map map ;

	/**
	 * 是否是暂停状态
	 */
	private boolean pause = false;

	/**
	 * 是否是冷却时间
	 */
	private boolean coolTime = false;
	
	/**
	 * 是否关闭血槽
	 */
	private boolean closeExp = false;
	
	/**
	 * 游戏是否开始
	 */
	private boolean startGame = false;

	/**
	 * 开火的ID号
	 */
	private int fireID = 0; 
	
	/**
	 * 坦克目前的等级
	 */
	private int level;

	/**
	 * 构造方法
	 */
	public GameDto() {
		this.initDto();
		boom = new Boom(0,0,this);
	}
	
	/**
	 * 初始化GameDto
	 */
	public void initDto() {
		//创建存放机器人坦克的集合对象
		this.tanks = new ArrayList<Tank>();
		//创建存放炮弹的集合对象
		this.bullet = new ArrayList<Bullet>();
		//创建存放地雷的集合对象
		this.landmines = new ArrayList<LandMine>();
		//创造存放墙壁的集合对象
		this.walls = new ArrayList<Wall>();
		//创建玩家坦克对象
		mytank = new Tank(this,true,false,600,200,Tank.Direction.STOP);
		//创造数字地图
		numMap = new int[11][20];
	}
	
	/**
	 * 得到游戏是否开始
	 */
	public boolean isStartGame() {
		return startGame;
	}

	/**
	 * 设置游戏是否开始
	 */
	public void setStartGame(boolean startGame) {
		this.startGame = startGame;
	}
	
	/**
	 *得到玩家坦克 
	 */
	public Tank getTank() {
		return mytank;
	}
	
	/**
	 * 设置玩家坦克
	 */
	public void setTank(Tank mytank) {
		this.mytank = mytank;
	}
	
	/**
	 * 得到机器人坦克集合
	 */
	public ArrayList<Tank> getTanks() {
		return tanks;
	}
	
	/**
	 * 得到炮弹集合
	 */
	public ArrayList<Bullet> getBullet() {
		return bullet;
	}
	
	/**
	 * 得到地雷集合
	 */
	public ArrayList<LandMine> getLandmines() {
		return landmines;
	}
	
	/**
	 * 得到爆炸
	 */
	public Boom getBoom() {
		return boom;
	}
	
	/**
	 * 得到墙壁集合
	 */
	public ArrayList<Wall> getWalls() {
		return walls;
	}
	
	/**
	 * 得到地图二维数组
	 */
	public int[][] getNumMap() {
		return numMap;
	}
	/**
	 * 设置游戏地图
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	
	/**
	 * 得到游戏地图
	 */
	public Map getMap() {
		return map;
	}
	
	/**
	 * 得到是否暂停
	 */
	public boolean isPause() {
		return pause;
	}
	
	/**
	 * 设置是否暂停
	 */
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	/**
	 * 得到是否是冷却时间
	 */
	public boolean isCoolTime() {
		return coolTime;
	}
	
	/**
	 * 设置冷却时间
	 */
	public void setCoolTime(boolean coolTime) {
		this.coolTime = coolTime;
	}
	
	/**
	 * 得到是否绘制血槽
	 */
	public boolean isCloseExp() {
		return closeExp;
	}
	
	/**
	 * 设置是否绘制血槽
	 */
	public void setCloseExp(boolean closeExp) {
		this.closeExp = closeExp;
	}
	
	/**
	 * 得到开火方式的ID
	 */
	public int getFireID() {
		return fireID;
	}
	
	/**
	 * 设置开火方式的ID
	 */
	public void setFireID(int fireID) {
		this.fireID = fireID;
	}
	
	/**
	 * 得到坦克目前的等级
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * 设置坦克目前的等级
	 */
	public void setLevel(int level) {
		this.level = level;
	}
}