package cn.lcy.tankwar.main;

import cn.lcy.tankwar.control.GameControl;
import cn.lcy.tankwar.control.PlayerControl;
import cn.lcy.tankwar.dto.GameDto;
import cn.lcy.tankwar.service.GameService;
import cn.lcy.tankwar.ui.FrameGame;
import cn.lcy.tankwar.ui.PanelGame;

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