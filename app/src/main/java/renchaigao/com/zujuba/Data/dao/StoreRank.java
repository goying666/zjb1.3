package renchaigao.com.zujuba.Data.dao;

/**
 * Created by Administrator on 2018/8/10/010.
 */
public class StoreRank {
    private String id;

    private String storeId;

    private Integer integral;

    private Integer cityIntegralRank;

    private Integer provinceIntegralRank;

    private Integer countryIntegralRank;

    private Boolean deleteStyle;

    private String upTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId == null ? null : storeId.trim();
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getCityIntegralRank() {
        return cityIntegralRank;
    }

    public void setCityIntegralRank(Integer cityIntegralRank) {
        this.cityIntegralRank = cityIntegralRank;
    }

    public Integer getProvinceIntegralRank() {
        return provinceIntegralRank;
    }

    public void setProvinceIntegralRank(Integer provinceIntegralRank) {
        this.provinceIntegralRank = provinceIntegralRank;
    }

    public Integer getCountryIntegralRank() {
        return countryIntegralRank;
    }

    public void setCountryIntegralRank(Integer countryIntegralRank) {
        this.countryIntegralRank = countryIntegralRank;
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