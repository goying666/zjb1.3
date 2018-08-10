package renchaigao.com.zujuba.Data.info.user;

import lombok.Getter;
import lombok.Setter;
import renchaigao.com.zujuba.Data.dao.User;
import renchaigao.com.zujuba.Data.dao.UserRank;
import renchaigao.com.zujuba.Data.info.addressInfo;
import renchaigao.com.zujuba.Data.info.photo;
import renchaigao.com.zujuba.Data.info.userFriendInfo;
import renchaigao.com.zujuba.Data.info.userPermissionInfo;
import renchaigao.com.zujuba.Data.info.userSpendInfo;

@Setter
@Getter
public class UserInfo extends User {
    private String userId;
    private myTeamsInfo myTeamsInfo;
    private myPlayGamesInfo myPlayGamesInfo;
    private myStoresInfo myStoresInfo;
    private photo myPhotoInfo;
    private addressInfo myAddressInfo;
    private userSpendInfo mySpendInfo;
    private UserRank myRankInfo;
    private userFriendInfo myFreiendInfo;
//    private ; //积分信息
//    private ; //名单信息
    private userPermissionInfo myPermissionInfo;

    public UserInfo(){

    }
    public UserInfo(User user){
        this.setId(user.getId());
    }
}
