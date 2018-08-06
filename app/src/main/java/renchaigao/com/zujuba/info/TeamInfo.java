package renchaigao.com.zujuba.info;

import lombok.Getter;
import lombok.Setter;
import renchaigao.com.zujuba.Json.Team;

/**
 * Created by Administrator on 2018/8/3/003.
 */
@Setter
@Getter
public class TeamInfo extends Team {
    private String scoreFilter;
    private Integer scoreFilterID;
    private AddressInfo addressInfo;
    private FilterInfo filterInfo;
}
