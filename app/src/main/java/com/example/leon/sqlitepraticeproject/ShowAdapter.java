package com.example.leon.sqlitepraticeproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Leon on 2017/3/6.
 */
public class ShowAdapter extends BaseAdapter {

    ArrayList<ShowItem> showList = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public ShowAdapter(Context context, ArrayList<ShowItem> arrayList){
        layoutInflater = LayoutInflater.from(context);
        showList = arrayList;
    }

    @Override
    public int getCount() {
        return showList.size();
    }

    @Override
    public Object getItem(int position) {
        return showList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return showList.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        ShowItem showItem = showList.get(position);
        if (view == null){
            view = layoutInflater.inflate(R.layout.showlist_adapter, null);
            viewHolder = new ViewHolder((TextView) view.findViewById(R.id.txv_account),
                                             (TextView) view.findViewById(R.id.txv_name),
                                                    (TextView) view.findViewById(R.id.txv_phone));
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.txvAccount.setText("帳號： " + showItem.getItemAccount());
        viewHolder.txvName.setText("姓名： " + showItem.getItemName());
        viewHolder.txvPhone.setText("電話：" + showItem.getItemPhone());

        return view;
    }

    private class ViewHolder{
        TextView txvAccount;
        TextView txvName;
        TextView txvPhone;
        public ViewHolder(TextView txvAccount, TextView txvName, TextView txvPhone){
            this.txvAccount = txvAccount;
            this.txvName = txvName;
            this.txvPhone = txvPhone;
        }
    }


}
