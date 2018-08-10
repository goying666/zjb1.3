package renchaigao.com.zujuba.Data.dao;

/**
 * Created by Administrator on 2018/8/10/010.
 */
public class Player {
    private String id;

    private String homeOwner;

    private String userId;

    private Integer distance;

    private String state;

    private String comeFrom;

    private String joinTime;

    private String userOpenInfoId;

    private String ageLevel;

    private String nickName;

    private String gender;

    private String job;

    private Boolean deleteStyle;

    private String upTime;

    private Integer integration;

    private Integer teamTimes;

    private Integer gameTimes;

    private String openNote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHomeOwner() {
        return homeOwner;
    }

    public void setHomeOwner(String homeOwner) {
        this.homeOwner = homeOwner == null ? null : homeOwner.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom == null ? null : comeFrom.trim();
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime == null ? null : joinTime.trim();
    }

    public String getUserOpenInfoId() {
        return userOpenInfoId;
    }

    public void setUserOpenInfoId(String userOpenInfoId) {
        this.userOpenInfoId = userOpenInfoId == null ? null : userOpenInfoId.trim();
    }

    public String getAgeLevel() {
        return ageLevel;
    }

    public void setAgeLevel(String ageLevel) {
        this.ageLevel = ageLevel == null ? null : ageLevel.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
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

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
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

    public String getOpenNote() {
        return openNote;
    }

    public void setOpenNote(String openNote) {
        this.openNote = openNote == null ? null : openNote.trim();
    }
}