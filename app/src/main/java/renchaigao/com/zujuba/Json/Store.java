package renchaigao.com.zujuba.Json;

import java.util.Map;

/**
 * Created by Administrator on 2018/7/16/016.
 */

public class Store {
    private Integer id;//数据库中的id
    private String name;//商铺名称
    private String storeClass;//商铺所属类型
    private String address;//地址
    private String distance;//相距，单位km、m
    private Map<String,String> pictureUrls;//图片对应的url地址，key是图片类型；
    private Integer maxPeopleNum;//最多接待人数；
    private Integer maxDeskNum;//最多接待桌数；
    private Map<Integer,Integer> deskDetail;//每桌接待情况；key：桌号，value：接待人数；
    private Integer status;//0：打样，1：营业；
    private Map<String,String> workingTime;//每周营业时段；key：星期，value：营业时段，例：18:00~21:00；
    private String telephoneNum;//手机联系方式；
    private String phoneNum;//座机电话号码；
    private String contact;//联系人;
    private Integer starNum;//星级；
    private Integer assessId;//评价系统id；
    private String placeInfo;//场地介绍；
    private String tipsInfo;//备注；
    private Integer historyId;//历史组局信息ID；
}
