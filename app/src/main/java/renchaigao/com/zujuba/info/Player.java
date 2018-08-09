package renchaigao.com.zujuba.info;

import lombok.Getter;
import lombok.Setter;
import renchaigao.com.zujuba.Json.User;

@Setter
@Getter
public class Player extends User {

    private Long distance;
    private String gameState;//游戏状态：游戏中，离场；
    private String teamState;//组局状态：加入，准备，到场，游戏中；
}
