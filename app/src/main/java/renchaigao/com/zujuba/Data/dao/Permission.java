package renchaigao.com.zujuba.Data.dao;

/**
 * Created by Administrator on 2018/8/10/010.
 */
public class Permission {
    private String id;

    private String permissionClass;

    private String ownerId;

    private String who;

    private String permissionConten;

    private String createTime;

    private Boolean deleteStyle;

    private String upTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPermissionClass() {
        return permissionClass;
    }

    public void setPermissionClass(String permissionClass) {
        this.permissionClass = permissionClass == null ? null : permissionClass.trim();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who == null ? null : who.trim();
    }

    public String getPermissionConten() {
        return permissionConten;
    }

    public void setPermissionConten(String permissionConten) {
        this.permissionConten = permissionConten == null ? null : permissionConten.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
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
}