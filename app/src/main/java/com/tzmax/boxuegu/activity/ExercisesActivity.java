package com.tzmax.boxuegu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.tzmax.boxuegu.BaseApplication;
import com.tzmax.boxuegu.R;

import java.util.ArrayList;

public class ExercisesActivity extends Activity {

    public static void start() {
        Context context = BaseApplication.mContext;
        Intent intent = new Intent(context, ExercisesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private ListView mIssuesList;

    private ArrayList<IssueData> issues;
    private IssueAdapter issueAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        initView();
    }

    private void initView() {
        setPageGoBack();
        mIssuesList = findViewById(R.id.exercises_issue_list);

        setIssues();
    }

    private void setIssues() {
        issues = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ArrayList<AnswerData> answerData = new ArrayList<>();
            answerData.add(new AnswerData("1", "A", "是的"));
            answerData.add(new AnswerData("2", "B", "不是的"));
            answerData.add(new AnswerData("3", "C", "好像是的"));
            answerData.add(new AnswerData("4", "D", "我也不知道"));
            issues.add(new IssueData("1", i + 1 + "、这是一个很南的问题。（）", "D", answerData));
        }

        issueAdapter = new IssueAdapter(this, issues);

        TextView listHeader = new TextView(this);
        listHeader.setText("一、选择题");
        listHeader.setPadding(5,10,5,10);

        mIssuesList.addHeaderView(listHeader);
        mIssuesList.setAdapter(issueAdapter);

    }

    // 设置顶部返回按钮点击事件
    private void setPageGoBack() {
        findViewById(R.id.exercises_page_goback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    private class IssueAdapter implements ListAdapter {

        private Context mContext;
        public ArrayList<IssueData> listData;

        public IssueAdapter(Context context, ArrayList<IssueData> data) {
            this.mContext = context;
            this.listData = data;
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
            viewController vc;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_issue, null);
                vc = new viewController(convertView);
                convertView.setTag(vc);
            } else {
                vc = (viewController) convertView.getTag();
            }

            if (vc == null) return null; // View 控制器判空

            setView(vc, listData.get(position));

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

        private void setView(final viewController vc, final IssueData data) {
            vc.mIssueText.setText(data.issue);

            AnswerData answerA = data.answers.get(0);
            if (answerA != null) {
                vc.mOptionAText.setText(answerA.content);
            }
            AnswerData answerB = data.answers.get(1);
            if (answerB != null) {
                vc.mOptionBText.setText(answerB.content);
            }
            AnswerData answerC = data.answers.get(2);
            if (answerC != null) {
                vc.mOptionCText.setText(answerC.content);
            }
            AnswerData answerD = data.answers.get(3);
            if (answerD != null) {
                vc.mOptionDText.setText(answerD.content);
            }
            final View.OnClickListener oc = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // 判断该题是否答过，未答过的话设置成已经答题
                    if (vc.isRespondence) return;
                    vc.isRespondence = true;

                    int index = v.getId();

                    if (data.answer.equals("A")) {
                        vc.mOptionAImage.setImageResource(R.drawable.exercises_right_icon);
                    } else if (data.answer.equals("B")) {
                        vc.mOptionBImage.setImageResource(R.drawable.exercises_right_icon);
                    } else if (data.answer.equals("C")) {
                        vc.mOptionCImage.setImageResource(R.drawable.exercises_right_icon);
                    } else if (data.answer.equals("D")) {
                        vc.mOptionDImage.setImageResource(R.drawable.exercises_right_icon);
                    }

                    if (index == R.id.item_issue_optionA) {
                        if (!data.answer.equals("A")) {
                            vc.mOptionAImage.setImageResource(R.drawable.exercises_error_icon);
                        }
                    } else if (index == R.id.item_issue_optionB) {
                        if (!data.answer.equals("B")) {
                            vc.mOptionBImage.setImageResource(R.drawable.exercises_error_icon);
                        }
                    } else if (index == R.id.item_issue_optionC) {
                        if (!data.answer.equals("C")) {
                            vc.mOptionCImage.setImageResource(R.drawable.exercises_error_icon);
                        }
                    } else if (index == R.id.item_issue_optionD) {
                        if (!data.answer.equals("D")) {
                            vc.mOptionDImage.setImageResource(R.drawable.exercises_error_icon);
                        }
                    }

                }
            };

            vc.mOptionA.setOnClickListener(oc);
            vc.mOptionB.setOnClickListener(oc);
            vc.mOptionC.setOnClickListener(oc);
            vc.mOptionD.setOnClickListener(oc);

        }

        class viewController {

            View rootView;
            TextView mIssueText, mOptionAText, mOptionBText, mOptionCText, mOptionDText;
            LinearLayout mOptionA, mOptionB, mOptionC, mOptionD;
            ImageView mOptionAImage, mOptionBImage, mOptionCImage, mOptionDImage;
            Boolean isRespondence = false;

            private viewController(View ItemView) {
                this.rootView = ItemView;

                this.mIssueText = ItemView.findViewById(R.id.item_issue_text);

                this.mOptionA = ItemView.findViewById(R.id.item_issue_optionA);
                this.mOptionB = ItemView.findViewById(R.id.item_issue_optionB);
                this.mOptionC = ItemView.findViewById(R.id.item_issue_optionC);
                this.mOptionD = ItemView.findViewById(R.id.item_issue_optionD);

                this.mOptionAText = ItemView.findViewById(R.id.item_issue_optionA_text);
                this.mOptionBText = ItemView.findViewById(R.id.item_issue_optionB_text);
                this.mOptionCText = ItemView.findViewById(R.id.item_issue_optionC_text);
                this.mOptionDText = ItemView.findViewById(R.id.item_issue_optionD_text);

                this.mOptionAImage = ItemView.findViewById(R.id.item_issue_optionA_image);
                this.mOptionBImage = ItemView.findViewById(R.id.item_issue_optionB_image);
                this.mOptionCImage = ItemView.findViewById(R.id.item_issue_optionC_image);
                this.mOptionDImage = ItemView.findViewById(R.id.item_issue_optionD_image);
            }
        }

    }

}
