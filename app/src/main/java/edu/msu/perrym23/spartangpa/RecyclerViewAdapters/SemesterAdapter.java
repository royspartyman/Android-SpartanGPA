package edu.msu.perrym23.spartangpa.RecyclerViewAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.msu.perrym23.spartangpa.R;
import edu.msu.perrym23.spartangpa.info.Term;
import io.realm.RealmList;

/**
 * Created by royspartyman on 12/28/16.
 */

public class SemesterAdapter extends RecyclerView.Adapter<SemesterAdapter.CustomViewHolder> {

    private RealmList<Term> termList;

        public SemesterAdapter(RealmList<Term> termList) {
        this.termList = termList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_semester, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Term term = termList.get(i);
        customViewHolder.season.setText(term.getSeason() + " " + term.getYear().toString());
    }

    @Override
    public int getItemCount() {
        return (null != termList ? termList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected LinearLayout linearLayout;
        protected TextView season;

        public CustomViewHolder(View view) {
            super(view);
            this.linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_courses);
            this.season = (TextView) view.findViewById(R.id.title_semester);
        }
    }
}