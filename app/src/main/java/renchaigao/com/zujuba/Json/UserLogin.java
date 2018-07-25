package renchaigao.com.zujuba.Json;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Administrator on 2018/7/25/025.
 */
@Setter
@Getter
public class UserLogin {
    private String userpwd;
    private String telephone;

    @Override
    public String toString() {
        return "{\"userpwd\":" + userpwd + ",\"telephone\":" + telephone + "}";
    }
}
