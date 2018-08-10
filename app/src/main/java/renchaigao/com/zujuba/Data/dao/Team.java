package renchaigao.com.zujuba.Data.dao;

/**
 * Created by Administrator on 2018/8/10/010.
 */
public class Team {
    private String id;

    private Integer playerMax;

    private Integer playerMin;

    private Integer playerNow;

    private String createTime;

    private String startDate;

    private String startTime;

    private String endTime;

    private String createrStyle;

    private String createrNote;

    private String state;

    private String teamName;

    private String createrId;

    private Boolean deleteStyle;

    private String upTime;

    private String playersInfoId;

    private String gameId;

    private String addressId;

    private String spendId;

    private String messageId;

    private String filterId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getPlayerMax() {
        return playerMax;
    }

    public void setPlayerMax(Integer playerMax) {
        this.playerMax = playerMax;
    }

    public Integer getPlayerMin() {
        return playerMin;
    }

    public void setPlayerMin(Integer playerMin) {
        this.playerMin = playerMin;
    }

    public Integer getPlayerNow() {
        return playerNow;
    }

    public void setPlayerNow(Integer playerNow) {
        this.playerNow = playerNow;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getCreaterStyle() {
        return createrStyle;
    }

    public void setCreaterStyle(String createrStyle) {
        this.createrStyle = createrStyle == null ? null : createrStyle.trim();
    }

    public String getCreaterNote() {
        return createrNote;
    }

    public void setCreaterNote(String createrNote) {
        this.createrNote = createrNote == null ? null : createrNote.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId == null ? null : createrId.trim();
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

    public String getPlayersInfoId() {
        return playersInfoId;
    }

    public void setPlayersInfoId(String playersInfoId) {
        this.playersInfoId = playersInfoId == null ? null : playersInfoId.trim();
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId == null ? null : gameId.trim();
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId == null ? null : addressId.trim();
    }

    public String getSpendId() {
        return spendId;
    }

    public void setSpendId(String spendId) {
        this.spendId = spendId == null ? null : spendId.trim();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getFilterId() {
        return filterId;
    }

    public void setFilterId(String filterId) {
        this.filterId = filterId == null ? null : filterId.trim();
    }
}