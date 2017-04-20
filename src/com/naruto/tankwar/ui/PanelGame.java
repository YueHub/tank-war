package com.naruto.tankwar.ui;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import com.naruto.tankwar.control.PlayerControl;
import com.naruto.tankwar.dto.GameDto;
import com.naruto.tankwar.img.Img;

public class PanelGame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 游戏数据传输层
	 */
	private GameDto dto;
	
	/**
	 * 构造方法
	 */
	public PanelGame(GameDto dto) {
		this.dto = dto;
	}
	
	/**
	 *安装游戏控制器 
	 */
	public void setPlayerControl(PlayerControl playercontrol) {
		this.addKeyListener(playercontrol);
	}
	
	/**
	 * 得到游戏数据传输层
	 */
	public GameDto getGameDto() {
		return dto;
	}


	public void paintComponent(Graphics g) {
		//根据等级更换背景图片
		Img.background = new ImageIcon("skin/background/background"+this.dto.getLevel()+".png").getImage();
		//绘制背景图片
		g.drawImage(Img.background,0,0,1200,660,null);
		//绘制主要
		this.dto.getTank().paint(g);
		
		//如果是冷却时间那么就绘制冷却图片
		if (this.dto.isCoolTime()) {
			g.drawImage(Img.cooltime, 0, 0,1200,660,null);
		}
		
		//如果是暂停状态那么就绘制暂停图片
		if (this.dto.isPause()) {
			g.drawImage(Img.pause,450,280,null);
		}
		//获得焦点
		this.requestFocus();
	}
	
}