package renchaigao.com.zujuba.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import renchaigao.com.zujuba.R;
import renchaigao.com.zujuba.info.TeamInfo;

/**
 * Created by Administrator on 2018/8/1/001.
 */

public class TeamFragmentAdapter extends RecyclerView.Adapter<TeamFragmentAdapter.ItemHolder>  {


    final static String TAG = "TeamFragmentAdapter";
    private ArrayList<TeamInfo> mTeamList;
    private Context mContext;


    public TeamFragmentAdapter(Context context) {
        this.mContext = context;
    }

    public void updateResults(ArrayList<TeamInfo> mStore) {
        this.mTeamList = mStore;
    }


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_team_main, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamFragmentAdapter.ItemHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        if (mTeamList == null) {
            return 0;
        } else
            return mTeamList.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {

        public ItemHolder(View itemView) {
            super(itemView);
        }
    }

}
