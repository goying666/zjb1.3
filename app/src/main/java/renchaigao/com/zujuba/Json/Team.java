package renchaigao.com.zujuba.Json;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2018/7/16/016.
 */
@Getter
@Setter
public class Team {
    private Integer id;

    private String playerinfoId;

    private String gameId;

    private String addressId;

    private String spendId;

    private String messageId;

    private String filterId;

    private String playerMax;

    private String playerMin;

    private String playerNow;

    private Integer createrId;

    private Integer ownerId;

    private String createTime;

    private String startDate;

    private String startTime;

    private Date endTime;

    private String createrStyle;

    private String note;

    private String teamstate;
}
