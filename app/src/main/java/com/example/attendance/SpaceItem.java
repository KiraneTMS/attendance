package com.example.attendance;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public class SpaceItem extends DrawerItem<SpaceItem.viewHolder>{

    private int spaceDp;

    public SpaceItem(int spaceDp){
        this.spaceDp = spaceDp;
    }

    @Override
    public SpaceItem.viewHolder createViewHolder(ViewGroup parent) {
        Context c = parent.getContext();
        View view = new View(c);
        int height = (int) (c.getResources().getDisplayMetrics().density*spaceDp);
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                height
        ));
        return new viewHolder(view);
    }

    @Override
    public void bindViewHolder(SpaceItem.viewHolder holder) {

    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    public class viewHolder extends DrawerAdapter.ViewHolder{

        public viewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
