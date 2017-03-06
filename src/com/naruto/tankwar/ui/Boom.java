package com.naruto.tankwar.ui;

import java.awt.Graphics;
import com.naruto.tankwar.dto.GameDto;
import com.naruto.tankwar.img.Img;

public class Boom {
	
	/**
	 * 数据传输层
	 */
	private GameDto dto;
	
	/**
	 * 爆炸的X坐标
	 */
	private int boomX;

	/**
	 * 爆炸的Y坐标
	 */
	private int boomY;
	
	/**
	 * 设置爆炸为需要
	 */
	private boolean live = true;
	
	/**
	 * 爆炸的id
	 */
	private int id ;
	
	/**
	 * 构造方法
	 */
	public Boom(int boomX,int boomY,GameDto dto){
		this.boomX = boomX;
		this.boomY = boomY;
		this.dto = dto;
	}
	
	/**
	 * 得到爆炸点的X坐标
	 */
	public int getBoomX() {
		return boomX;
	}

	/**
	 * 设置爆炸点的Y坐标
	 */
	public void setBoomX(int boomX) {
		this.boomX = boomX;
	}

	/**
	 * 得到爆炸点的Y坐标
	 */
	public int getBoomY() {
		return boomY;
	}
	
	/**
	 * 设置爆炸点的Y坐标
	 */
	public void setBoomY(int boomY) {
		this.boomY = boomY;
	}
	
	/**
	 * 设置爆炸是否需要
	 */
	public void setLive(boolean live){
		this.live = live;
	}
	
	public void drawBoom(Graphics g){	
		//如果不需要爆炸就不绘画
		if(!live){
			return;
		}
		if(id == Img.BOOM[0].length){
			live = false;
			id = 0;
			return;
		}
		g.drawImage(Img.BOOM[this.dto.getFireID()][id], boomX, boomY,null);
		id++;	
	}
	
}
