package renchaigao.com.zujuba.info;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerInfo {

    private String id;
    private Integer teamId;
    private List<Player> players;

}
