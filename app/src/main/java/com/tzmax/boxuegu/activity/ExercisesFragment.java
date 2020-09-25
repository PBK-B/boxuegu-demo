package com.tzmax.boxuegu.activity;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tzmax.boxuegu.R;

import java.util.ArrayList;

public class ExercisesFragment extends Fragment {

    private Activity activity;
    private View rootView;

    private ListView mList;
    private ExercisesAdapter exercisesAdapter;
    private ArrayList<ExercisesData> exercises;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercises, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();
        rootView = view;

        initView();
    }

    private void initView() {
        mList = rootView.findViewById(R.id.exercises_list);

        setExercisesList();
    }

    private void setExercisesList() {
        exercises = new ArrayList<>();
        exercises.add(new ExercisesData("1", "第1章 Android 基础入门", R.drawable.exercises_bg_1, 5));
        exercises.add(new ExercisesData("2", "第2章 Android UI 开发", R.drawable.exercises_bg_2, 5));
        exercises.add(new ExercisesData("3", "第3章 Android UI 开发", R.drawable.exercises_bg_3, 5));

        exercisesAdapter = new ExercisesAdapter(activity, exercises);
        mList.setAdapter(exercisesAdapter);

    }

    class ExercisesData {
        String id, title;
        int countIssue, imageID;
        ArrayList<IssueData> issues;

        public ExercisesData(String id, String title, int image, int countIssue, ArrayList<IssueData> issues) {
            this.id = id;
            this.title = title;
            this.imageID = image;
            this.countIssue = countIssue;
            this.issues = issues;
        }

        public ExercisesData(String id, String title, int image, int countIssue) {
            this.id = id;
            this.title = title;
            this.imageID = image;
            this.countIssue = countIssue;
        }

        class IssueData {
            String id, issue, answer;
            ArrayList<AnswerData> answers;

            public IssueData(String id, String issue, String answer, ArrayList<AnswerData> answers) {
                this.id = id;
                this.issue = issue;
                this.answer = answer;
                this.answers = answers;
            }

        }

        class AnswerData {
            String id, symbol, content;

            public AnswerData(String id, String symbol, String content) {
                this.id = id;
                this.symbol = symbol;
                this.content = content;
            }
        }

    }

    private class ExercisesAdapter implements ListAdapter {

        private Context mContext;
        public ArrayList<ExercisesData> listData;

        protected ExercisesAdapter(Context context, ArrayList<ExercisesData> datas) {
            this.mContext = context;
            this.listData = datas;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int position) {
            return false;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Object getItem(int position) {
            return listData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewController vc;
            ExercisesData data = listData.get(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_exercises, null);
                vc = new ViewController(convertView);
                convertView.setTag(vc);
            } else {
                vc = (ViewController) convertView.getTag();
            }

            vc.mImage.setText((String) data.id);
            vc.mImage.setBackgroundResource(data.imageID);
            vc.mTitle.setText((String) data.title);
            vc.mInfo.setText((String) "共计" + data.countIssue + "题");

            return convertView;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return listData.size();
        }

        @Override
        public boolean isEmpty() {
            return false;
        }


        class ViewController {
            View rootView;
            TextView mImage, mTitle, mInfo;

            public ViewController(@NonNull View itemView) {
                this.rootView = itemView;
                this.mImage = itemView.findViewById(R.id.item_exercises_image);
                this.mTitle = itemView.findViewById(R.id.item_exercises_title);
                this.mInfo = itemView.findViewById(R.id.item_exercises_info);
            }
        }

    }

}
