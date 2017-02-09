package com.example.guillaume.tp1_mmm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.guillaume.tp1_mmm.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends BaseAdapter implements Filterable {

    // Declare Variables
    private Context mContext;
    private LayoutInflater inflater;
    private List<User> users;
    private ArrayList<User> filteredList;

    public UserListAdapter(Context context, List<User> users) {
        mContext = context;
        this.users = users;
        inflater = LayoutInflater.from(mContext);
        this.filteredList = new ArrayList<>();
        this.filteredList.addAll(users);
    }

    public class ViewHolder {
        TextView fullname;
        TextView date;
        TextView ville;
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public User getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item, null);
            // Locate the TextViews in listview_item.xml
            holder.fullname = (TextView) view.findViewById(R.id.text_list_fullname);
            holder.ville = (TextView) view.findViewById(R.id.text_list_ville);
            holder.date = (TextView) view.findViewById(R.id.text_list_date);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.fullname.setText(filteredList.get(position).getFullname());
        holder.ville.setText(filteredList.get(position).getVille());
        holder.date.setText(filteredList.get(position).getDate());

        return view;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<User> filteredResults = new ArrayList<>();

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    String filter = constraint.toString().toLowerCase();
                    if (user.getFullname().toLowerCase().contains(filter) ||
                            user.getVille().toLowerCase().contains(constraint) ||
                            user.getDepartement().toLowerCase().contains(constraint) ||
                            user.getDate().toLowerCase().contains(constraint)) {
                        filteredResults.add(user);
                    }
                }

                results.count = filteredResults.size();
                results.values = filteredResults;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<User>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
