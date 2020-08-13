package com.naruto.tankwar.main;

import com.naruto.tankwar.control.GameControl;
import com.naruto.tankwar.control.PlayerControl;
import com.naruto.tankwar.dto.GameDto;
import com.naruto.tankwar.service.GameService;
import com.naruto.tankwar.ui.FrameGame;
import com.naruto.tankwar.ui.PanelGame;

public class MainGame {
    public static void main(String ass[]) {
        GameDto dto = new GameDto();
        PanelGame panelgame = new PanelGame(dto);
        GameService gameservice = new GameService(dto, panelgame);
        GameControl gamecontrol = new GameControl(gameservice, panelgame);
        panelgame.setPlayerControl(new PlayerControl(gamecontrol, dto));
        new FrameGame(panelgame, dto);
    }
}