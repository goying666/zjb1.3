package renchaigao.com.zujuba.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.zujuba.mongoDB.info.team.TeamInfo;


import renchaigao.com.zujuba.R;

public class TeamActivity extends AppCompatActivity {

    private TextView ti_team_number;
    private TextView ti_user_position;
    private TextView ti_user_state;
    private TextView ti_text_null1;
    private TextView ti_text_distance;
    private TextView ti_store_score;
    private TextView ti_text_spend;
    private TextView ti_text_rank;
    private TextView ti_start_data;
    private TextView ti_start_time;
    private TextView ti_spend_way;
    private TextView ti_game_class;
    private TextView ti_filter_all_number;
    private TextView ti_filter_my_sum;
    private TextView ti_IntegrityScore;
    private TextView ti_IntegrityScore_result_info;
    private TextView ti_sexRatio;
    private TextView ti_sexRatio_result_info;
    private TextView ti_ageScreening;
    private TextView ti_ageScreening_result_info;
    private TextView ti_evaluationScreening;
    private TextView ti_evaluationScreening_result_info;
    private TextView ti_careerScreening;
    private TextView ti_careerScreening_result_info;
    private TextView ti_marriage;
    private TextView ti_marriage_result_info;
    private TextView ti_note_info;


    private void setViewDate(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        initIntentData();
        setToolBar();
        initView();
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private TeamInfo teamInfo;

    private void initIntentData() {
        Intent intent = getIntent();
        String teamString = intent.getStringExtra("team");
        teamInfo = JSONObject.parseObject(teamString, TeamInfo.class);
    }

    private void initView() {
        ti_team_number = findViewById(R.id.ti_team_number);
        ti_user_position = findViewById(R.id.ti_user_position);
        ti_user_state = findViewById(R.id.ti_user_state);
        ti_text_null1 = findViewById(R.id.ti_text_null1);
        ti_text_distance = findViewById(R.id.ti_text_distance);
        ti_store_score = findViewById(R.id.ti_store_score);
        ti_text_spend = findViewById(R.id.ti_text_spend);
        ti_text_rank = findViewById(R.id.ti_text_rank);
        ti_start_data = findViewById(R.id.ti_start_data);
        ti_start_time = findViewById(R.id.ti_start_time);
        ti_spend_way = findViewById(R.id.ti_spend_way);
        ti_game_class = findViewById(R.id.ti_game_class);
        ti_filter_all_number = findViewById(R.id.ti_filter_all_number);
        ti_filter_my_sum = findViewById(R.id.ti_filter_my_sum);
        ti_IntegrityScore = findViewById(R.id.ti_IntegrityScore);
        ti_IntegrityScore_result_info = findViewById(R.id.ti_IntegrityScore_result_info);
        ti_sexRatio = findViewById(R.id.ti_sexRatio);
        ti_sexRatio_result_info = findViewById(R.id.ti_sexRatio_result_info);
        ti_ageScreening = findViewById(R.id.ti_ageScreening);
        ti_ageScreening_result_info = findViewById(R.id.ti_ageScreening_result_info);
        ti_evaluationScreening = findViewById(R.id.ti_evaluationScreening);
        ti_evaluationScreening_result_info = findViewById(R.id.ti_evaluationScreening_result_info);
        ti_careerScreening = findViewById(R.id.ti_careerScreening);
        ti_careerScreening_result_info = findViewById(R.id.ti_careerScreening_result_info);
        ti_marriage = findViewById(R.id.ti_marriage);
        ti_marriage_result_info = findViewById(R.id.ti_marriage_result_info);
        ti_note_info = findViewById(R.id.ti_note_info);
    }
}
