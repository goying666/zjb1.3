package renchaigao.com.zujuba.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

import renchaigao.com.zujuba.R;
import renchaigao.com.zujuba.info.MessageNoteInfo;

public class MessageInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_info);
    }

//查询本地消息，从本地数据库读取当前群组的消息记录，并更新至界面；
    private List<MessageNoteInfo> getMessageFromLiteSQL(String groupId){
//        拿数据
        List<MessageNoteInfo> allMessage = DataSupport.select("id",groupId).find(MessageNoteInfo.class);
        return allMessage;
//        显示
    }
//    更新消息，单独线程执行监控，服务器有新消息通知后及更新最新消息；

//    增加消息
    private void addMessageToLiteSQL(MessageNoteInfo newMessage){
        newMessage.save();
    }
//    删除消息
    private void deleteMessagesFromLiteSQL(String messageId){
        DataSupport.deleteAll(MessageNoteInfo.class,"id = ?",messageId);
    }

}
