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

    private Integer playerinfoId;

    private Integer gameId;

    private Integer addressId;

    private Integer spendId;

    private Integer messageId;

    private Integer filterId;

    private Integer playerMax;

    private Integer playerMin;

    private Integer playerNow;

    private Integer createrId;

    private Integer ownerId;

    private Date createTime;

    private String startDate;

    private String startTime;

    private Date endTime;

    private String createrStyle;

    private String note;

    private String teamstate;
}
