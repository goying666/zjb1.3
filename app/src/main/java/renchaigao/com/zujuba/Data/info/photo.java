package renchaigao.com.zujuba.Data.info;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class photo {
    private String id;
    private String useClass;
    private String ownerId;
    private ArrayList<String> pathList;
    private boolean deleteStyle;
    private String upTime;
    private boolean replace;
    private String newPhotoId;

}
