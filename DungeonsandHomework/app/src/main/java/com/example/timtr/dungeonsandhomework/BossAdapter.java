package com.example.timtr.dungeonsandhomework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Henry W on 2017-11-18.
 */

public class BossAdapter extends ArrayAdapter<Boss> {

    public BossAdapter(Context context, ArrayList<Boss> bosses) {
        super(context, 0, bosses);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View bossData = getBossData(convertView, parent);
        TextView bossNameTextView = (TextView) bossData.findViewById(R.id.bossName);
        TextView bossHpTextView = (TextView) bossData.findViewById(R.id.boss_hp_textview);
        TextView bossRewardTextView = (TextView) bossData.findViewById(R.id.boss_reward_textview);

        Boss boss = getItem(position);
        bossNameTextView.setText(boss.getBossName());
        bossHpTextView.setText(String.format("HP: %d", boss.getHealth()));
        bossRewardTextView.setText(String.format("Reward: %d coins", boss.getGold()));

        return bossData;
    }

    private View getBossData(@Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView != null) {
            return convertView;
        }
        return LayoutInflater.from(getContext()).inflate(R.layout.boss_list, parent, false);
    }

}

