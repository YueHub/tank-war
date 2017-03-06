package com.naruto.tankwar.img;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Img {
	
	/**
	 * ±³¾°Í¼Æ¬
	 */
	public static Image background = new ImageIcon("skin/background/background0"+".png").getImage();
	
	/**
	 * Ç½ÌåÍ¼Æ¬
	 */
	public static Image[] WALL ; 
	static{
		WALL = new Image[12];
		for(int i = 0;i<WALL.length;i++ ){
		WALL[i] = new ImageIcon("skin/walls/wall0/"+i+".png").getImage();
		}
	}
	
	/**
	 * Ì¹¿ËÍ¼Æ¬
	 */
	public static Image[][] TANK;
	static {
		TANK = new Image[3][4];
		for(int i = 0;i<3;i++){
			for(int j =0;j<4;j++){
				TANK[i][j] = new ImageIcon("skin/tank01/tank"+i+"/"+j+".png").getImage();
			}
		}
	}
	
	/**
	 * ÅÚµ¯µÄÍ¼Æ¬
	 */
	public static Image[] BULLET;
	static {
		BULLET = new Image[4];
		for(int i = 0;i<BULLET.length;i++){
			BULLET[i] =new ImageIcon("skin/bullet/"+i+".png").getImage();
		}
	}
	
	/**
	 *±¬Õ¨µÄÍ¼Æ¬ 
	 */
	public static Image[][] BOOM;
	static {
			BOOM = new Image[4][18];
			for(int i=0;i<4;i++){
				for(int j =0 ;j<17;j++){
					BOOM[i][j] = new ImageIcon("skin/booms/boom"+i+"/"+j+".png").getImage();
				}
			}
	}
	
	/**
	 * µØÀ×µÄÍ¼Æ¬
	 */
	public static Image landmine = new ImageIcon("skin/landmine/landmine.png").getImage();
	
	/**
	 * bossµÄÍ¼Æ¬
	 */
	public static Image boss = new ImageIcon("skin/boss/boss.png").getImage();
	
	
	/**
	 * Ñª²Û¿òÍ¼Æ¬	
	 */
	public static Image rectframe = new ImageIcon("skin/tank/rectframe.png").getImage();
	
	/**
	 * Ñª²ÛµÄÍ¼Æ¬
	 */
	public static Image rect = new ImageIcon("skin/tank/rect.png").getImage();
	
	/**
	 * ÔÝÍ£Í¼Æ¬
	 */
	public static Image pause = new ImageIcon("skin/other/pause.png").getImage();
	
	/**
	 * ÀäÈ´Í¼Æ¬
	 */
	public static Image cooltime = new ImageIcon("skin/other/cooltime.png").getImage();
	
	
}
