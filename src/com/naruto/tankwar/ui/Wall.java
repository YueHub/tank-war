package com.naruto.tankwar.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import com.naruto.tankwar.dto.GameDto;
import com.naruto.tankwar.img.Img;

public class Wall {
	
	/**
	 * 游戏数据传输层
	 */
	private GameDto dto;
	
	/**
	 * 墙壁图片
	 */
	private Image img;
	
	/**
	 * 墙壁的X坐标
	 */
	private int wallX;
	
	/**
	 * 墙壁的Y坐标
	 */
	private int wallY;
	
	/**
	 * 标识墙壁的ID号
	 */
	private int wallID;
	
	/**
	 * 墙壁的血量
	 */
	private int exp;
	
	/**
	 * 墙壁图片的宽度
	 */
	private int WALL_W ;
	
	/**
	 * 墙壁图片的高度
	 */
	private  int WALL_H ;
	
	/**
	 * 构造方法
	 */
	public Wall(Image img,int wallID,int wallX,int wallY,GameDto dto) {
		this.img = img;
		this.wallID = wallID;
		this.wallX = wallX;
		this.wallY = wallY;
		this.dto = dto;
		this.initWall();
	}
	
	/**
	 * 墙壁初始化
	 */
	public void initWall() {
		if (this.wallID == 0) {
			WALL_W = 0 ;
			WALL_H = 0 ;
		} else {
			WALL_W = Img.WALL[wallID].getWidth(null);
			WALL_H = Img.WALL[wallID].getHeight(null);
			this.exp = 100;
		}
	}
	
	/**
	 * 墙壁掉血
	 */
	public void reduceExp() {
		if (this.getWallID() != 5 && this.getWallID() != 0) {
			this.exp -= (10+this.dto.getFireID()*30);
		}
	}
	
	/**
	 * 得到现在墙壁的血量
	 */
	public int getExp() {
		return exp;
	}
	
	/**
	 * 设置墙壁现在的血量
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	/**
	 * 得到墙壁的ID
	 */
	public int getWallID() {
		return wallID;
	}
	
	/**
	 * 设置墙壁的ID
	 */
	public void setWallID(int wallID) {
		this.wallID = wallID;
	}

	/**
	 * 碰撞检测
	 */
	public Rectangle getRect() {
		return new Rectangle(wallX,wallY,WALL_W,WALL_H);
	}
	
	/**
	 * 绘制墙壁
	 */
	public void drawWall(Graphics g) {
		if (this.exp <= 0) {
			this.dto.getWalls().remove(this);
			return ;
		}
		g.drawImage(img, wallX, wallY,null);
	}
}