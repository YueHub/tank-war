package com.naruto.tankwar.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import com.naruto.tankwar.dto.GameDto;
import com.naruto.tankwar.img.Img;

public class Map {
	
	/**
	 * 游戏数据传输层
	 */
	private GameDto dto;

	/**
	 * 输入流
	 */
	FileReader fr = null;	
	
	/**
	 * 输入包装流
	 */
	BufferedReader br = null;
	
	/**
	 * 玩家现在的等级
	 */
	public static int level;
	
	/**
	 * 构造方法
	 */
	public Map(GameDto dto) {
		this.dto = dto;
	}
	
	
	
	/**
	 * 读入存有地图信息的文件（二维int型数组）PS：借鉴于java吧推箱子游戏
	 */
	public void readMap() {		
		String filepath = "maps/" + level + ".map";
		File file = new File(filepath);	
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			for (int i = 0; i < 11; i++) {
				String line = br.readLine();
				byte[] point = line.getBytes();
				for (int j = 0; j < 20; j++) {
					this.dto.getNumMap()[i][j] = point[j] - 48;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br == null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				br = null;
			}
			if (fr == null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fr = null;
			}
		}
	}
	
	/**
	 * 删除原来的地图
	 */
	public void removeMap() {
		//设置所有墙壁血量为0  删除所有墙壁（即删除地图）
		for (int i = 0; i < this.dto.getWalls().size(); i++) {
				this.dto.getWalls().get(i).setExp(0);
		}
	}
	
	/**
	 * 创建地图
	 */
	public void creatMap() {
		//得到当前的坦克等级
		level = this.dto.getLevel();
		//根据等级更换墙壁图片
		for (int i = 0;i<Img.WALL.length;i++ ) {
			Img.WALL[i] = new ImageIcon("skin/walls/wall"+level+"/"+i+".png").getImage();
		}
		//读入地图文件
		this.readMap();
		//根据地图文件添加墙壁（即创造地图）
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 20; j++) {
				int wallID = this.dto.getNumMap()[i][j];
				this.dto.getWalls().add(new Wall(Img.WALL[wallID],wallID,j*60,i*60,dto));
			}
		}
	}
}