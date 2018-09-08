package renchaigao.com.zujuba.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.renchaigao.zujuba.mongoDB.info.game.MXTWorld.Equip.RoleEquipmentsInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/8/27/027.
 */

public class GameRoleAdapter extends RecyclerView.Adapter<GameRoleAdapter.ItemHolder> {

    private RoleEquipmentsInfo roleEquipmentsInfo;
    @Override
    public GameRoleAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(GameRoleAdapter.ItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ItemHolder(View itemView) {
            super(itemView);
        }
    }
}
