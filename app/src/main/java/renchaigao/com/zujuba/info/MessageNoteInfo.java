package renchaigao.com.zujuba.info;

import org.litepal.crud.DataSupport;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2018/11/14/014.
 */

@Setter
@Getter
public class MessageNoteInfo extends DataSupport{
    private String id;
    private String groupId;//消息组ID；
    private String senderId;//0代表自己；
    private String notesInfo;//消息内容；
    private String sendTime;//发送时间；
    private Integer messageSum;//消息数
}
