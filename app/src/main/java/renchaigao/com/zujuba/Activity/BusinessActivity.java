package renchaigao.com.zujuba.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import renchaigao.com.zujuba.R;

public class BusinessActivity extends AppCompatActivity {
    private TextView business_join_introduce_addres_name, business_join_introduce_time_textView_number;
    private LinearLayout linearLayout_part1, linearLayout_part2, linearLayout_part3, linearLayout_part4;
    private Button business_join_introduce_button_next, business_join_basic_button_next, business_join_detail_button_back, business_join_detail_button_next;
    private ScrollView business_join_NestedScrollView;
    private CheckBox business_join_introduce_time_checkBox1, business_join_introduce_time_checkBox2, business_join_introduce_time_checkBox3, business_join_introduce_time_checkBox4;
    private Integer checkBoxNum = 0;

    private void initView() {
        business_join_detail_button_back = findViewById(R.id.business_join_detail_button_back);
        business_join_detail_button_next = findViewById(R.id.business_join_detail_button_next);
        business_join_basic_button_next = findViewById(R.id.business_join_basic_button_next);
        business_join_introduce_button_next = findViewById(R.id.business_join_introduce_button_next);
        business_join_introduce_addres_name = findViewById(R.id.business_join_introduce_addres_name);
        business_join_NestedScrollView = findViewById(R.id.business_join_NestedScrollView);
        business_join_introduce_time_textView_number = findViewById(R.id.business_join_introduce_time_textView_number);
        business_join_introduce_time_checkBox1 = findViewById(R.id.business_join_introduce_time_checkBox1);
        business_join_introduce_time_checkBox2 = findViewById(R.id.business_join_introduce_time_checkBox2);
        business_join_introduce_time_checkBox3 = findViewById(R.id.business_join_introduce_time_checkBox3);
        business_join_introduce_time_checkBox4 = findViewById(R.id.business_join_introduce_time_checkBox4);
        linearLayout_part1 = findViewById(R.id.business_join_introduce_part1);
        linearLayout_part2 = findViewById(R.id.business_join_introduce_part2);
        linearLayout_part3 = findViewById(R.id.business_join_introduce_part3);
        linearLayout_part4 = findViewById(R.id.business_join_introduce_part4);
        setLinearLayoutVisibile(1);
    }

    private void initClick() {
        business_join_introduce_addres_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessActivity.this, MapBusinessActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        business_join_introduce_button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLinearLayoutVisibile(2);
            }
        });
        business_join_introduce_time_checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) checkBoxNum++;
                else checkBoxNum--;
                business_join_introduce_time_textView_number.setText(checkBoxNum.toString());
            }
        });
        business_join_introduce_time_checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) checkBoxNum++;
                else checkBoxNum--;
                business_join_introduce_time_textView_number.setText(checkBoxNum.toString());
            }
        });
        business_join_introduce_time_checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) checkBoxNum++;
                else checkBoxNum--;
                business_join_introduce_time_textView_number.setText(checkBoxNum.toString());
            }
        });
        business_join_introduce_time_checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) checkBoxNum++;
                else checkBoxNum--;
                business_join_introduce_time_textView_number.setText(checkBoxNum.toString());
            }
        });
        business_join_basic_button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLinearLayoutVisibile(3);
            }
        });
        business_join_detail_button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLinearLayoutVisibile(4);
            }
        });
        business_join_detail_button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLinearLayoutVisibile(2);
            }
        });
    }

    private void setLinearLayoutVisibile(int whichOne) {
        linearLayout_part1.setVisibility(View.GONE);
        linearLayout_part2.setVisibility(View.GONE);
        linearLayout_part3.setVisibility(View.GONE);
        linearLayout_part4.setVisibility(View.GONE);
        switch (whichOne) {
            case 1:
                linearLayout_part1.setVisibility(View.VISIBLE);
                business_join_NestedScrollView.fullScroll(View.FOCUS_UP);
                break;
            case 2:
                linearLayout_part2.setVisibility(View.VISIBLE);
                business_join_NestedScrollView.fullScroll(View.FOCUS_UP);
                break;
            case 3:
                linearLayout_part3.setVisibility(View.VISIBLE);
                business_join_NestedScrollView.fullScroll(View.FOCUS_UP);
                break;
            case 4:
                linearLayout_part4.setVisibility(View.VISIBLE);
                business_join_NestedScrollView.fullScroll(View.FOCUS_UP);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar();
        setContentView(R.layout.activity_business);
        initView();
        initClick();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    business_join_introduce_addres_name.setText(data.getStringExtra("addressJsonStr"));
                }
        }
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private long time = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - time > 1000)) {
                Toast.makeText(this, "再按一次取消入驻", Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
