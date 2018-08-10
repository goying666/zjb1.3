package renchaigao.com.zujuba.Data.dao;

/**
 * Created by Administrator on 2018/8/10/010.
 */
public class UserLogin {
    private String id;

    private String loginTime;

    private String userId;

    private Boolean deleteStyle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime == null ? null : loginTime.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Boolean getDeleteStyle() {
        return deleteStyle;
    }

    public void setDeleteStyle(Boolean deleteStyle) {
        this.deleteStyle = deleteStyle;
    }
}