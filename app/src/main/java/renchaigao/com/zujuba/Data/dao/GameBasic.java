package renchaigao.com.zujuba.Data.dao;

/**
 * Created by Administrator on 2018/8/10/010.
 */
public class GameBasic {
    private String id;

    private String name;

    private String category;

    private String introduce;

    private Integer suitPeopleMax;

    private Integer suitPeopleMin;

    private String producer;

    private String producerId;

    private String producerClass;

    private Integer playTimes;

    private Integer teamTimes;

    private Integer playerNum;

    private Integer gameDuration;

    private Boolean deleteStyle;

    private String upTime;

    private String gameIntroduceId;

    private String evaluationId;

    private String photoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getSuitPeopleMax() {
        return suitPeopleMax;
    }

    public void setSuitPeopleMax(Integer suitPeopleMax) {
        this.suitPeopleMax = suitPeopleMax;
    }

    public Integer getSuitPeopleMin() {
        return suitPeopleMin;
    }

    public void setSuitPeopleMin(Integer suitPeopleMin) {
        this.suitPeopleMin = suitPeopleMin;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId == null ? null : producerId.trim();
    }

    public String getProducerClass() {
        return producerClass;
    }

    public void setProducerClass(String producerClass) {
        this.producerClass = producerClass == null ? null : producerClass.trim();
    }

    public Integer getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(Integer playTimes) {
        this.playTimes = playTimes;
    }

    public Integer getTeamTimes() {
        return teamTimes;
    }

    public void setTeamTimes(Integer teamTimes) {
        this.teamTimes = teamTimes;
    }

    public Integer getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(Integer playerNum) {
        this.playerNum = playerNum;
    }

    public Integer getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(Integer gameDuration) {
        this.gameDuration = gameDuration;
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

    public String getGameIntroduceId() {
        return gameIntroduceId;
    }

    public void setGameIntroduceId(String gameIntroduceId) {
        this.gameIntroduceId = gameIntroduceId == null ? null : gameIntroduceId.trim();
    }

    public String getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId == null ? null : evaluationId.trim();
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId == null ? null : photoId.trim();
    }
}