package renchaigao.com.zujuba.Data.info;
import renchaigao.com.zujuba.Data.dao.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class addressInfo extends Address {
    private Integer distance;
    private Double startLatitude;
    private Double startLongitude;

}
