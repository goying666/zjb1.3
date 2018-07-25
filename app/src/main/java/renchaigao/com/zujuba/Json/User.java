package renchaigao.com.zujuba.Json;

public class User {
    private Integer id;

    private String nickname;

    private Integer age;

    private String realname;

    private String idcard;

    private String addres;

    private String gender;

    private String job;

    private String telephone;

    private String marriage;

    private Integer whiteuserid;

    private Integer blackuserid;

    private Integer userloginid;

    private Integer roomid;

    private Integer gameid;

    private Integer storeid;

    private Integer integration;

    private Integer otherinfoid;

    private String photosn;

    private String userpwd;

    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres == null ? null : addres.trim();
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage == null ? null : marriage.trim();
    }

    public Integer getWhiteuserid() {
        return whiteuserid;
    }

    public void setWhiteuserid(Integer whiteuserid) {
        this.whiteuserid = whiteuserid;
    }

    public Integer getBlackuserid() {
        return blackuserid;
    }

    public void setBlackuserid(Integer blackuserid) {
        this.blackuserid = blackuserid;
    }

    public Integer getUserloginid() {
        return userloginid;
    }

    public void setUserloginid(Integer userloginid) {
        this.userloginid = userloginid;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public Integer getOtherinfoid() {
        return otherinfoid;
    }

    public void setOtherinfoid(Integer otherinfoid) {
        this.otherinfoid = otherinfoid;
    }

    public String getPhotosn() {
        return photosn;
    }

    public void setPhotosn(String photosn) {
        this.photosn = photosn == null ? null : photosn.trim();
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }
}