package renchaigao.com.zujuba.Data.dao;

/**
 * Created by Administrator on 2018/8/10/010.
 */
public class Friend {
    private String id;

    private String userId;

    private String friendId;

    private String friendOpenInfoId;

    private Integer messageNum;

    private Integer teamTimes;

    private Integer gameTimes;

    private Boolean deleteStyle;

    private String upTime;

    private String addTime;

    private String firstTeamId;

    private String nearlyTeamId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId == null ? null : friendId.trim();
    }

    public String getFriendOpenInfoId() {
        return friendOpenInfoId;
    }

    public void setFriendOpenInfoId(String friendOpenInfoId) {
        this.friendOpenInfoId = friendOpenInfoId == null ? null : friendOpenInfoId.trim();
    }

    public Integer getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(Integer messageNum) {
        this.messageNum = messageNum;
    }

    public Integer getTeamTimes() {
        return teamTimes;
    }

    public void setTeamTimes(Integer teamTimes) {
        this.teamTimes = teamTimes;
    }

    public Integer getGameTimes() {
        return gameTimes;
    }

    public void setGameTimes(Integer gameTimes) {
        this.gameTimes = gameTimes;
    }

    public Boolean getDeleteStyle() {
        return deleteStyle;
    }

    public void setDeleteStyle(Boolean deleteStyle) {
        this.deleteStyle = deleteStyle;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime == null ? null : upTime.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public String getFirstTeamId() {
        return firstTeamId;
    }

    public void setFirstTeamId(String firstTeamId) {
        this.firstTeamId = firstTeamId == null ? null : firstTeamId.trim();
    }

    public String getNearlyTeamId() {
        return nearlyTeamId;
    }

    public void setNearlyTeamId(String nearlyTeamId) {
        this.nearlyTeamId = nearlyTeamId == null ? null : nearlyTeamId.trim();
    }
}