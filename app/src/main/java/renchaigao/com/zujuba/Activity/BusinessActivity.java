package renchaigao.com.zujuba.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import renchaigao.com.zujuba.R;

public class BusinessActivity extends AppCompatActivity {
    private TextView business_join_introduce_addres_name;

    private void initView() {
        business_join_introduce_addres_name = findViewById(R.id.business_join_introduce_addres_name);
    }

    private void initClick() {
        business_join_introduce_addres_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessActivity.this, MapBusinessActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        initView();
        initClick();
        setToolBar();
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
}
