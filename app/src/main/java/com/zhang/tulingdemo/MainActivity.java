package com.zhang.tulingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.http.client.HttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ListData> lists;
    private ListView lv;
    private EditText etSendText;
    private Button btnSend;
    private TextAdapter adapter;

    private long oldTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        lists = new ArrayList<>();
        lv = (ListView) findViewById(R.id.lv);
        etSendText = (EditText) findViewById(R.id.etSendText);
        btnSend = (Button) findViewById(R.id.btnSend);
        adapter = new TextAdapter(this, lists);
        lv.setAdapter(adapter);

        ListData l = new ListData(getRandomWelcomeTips(), ListData.RECEIVE, getTime());
        lists.add(l);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content_str = etSendText.getText().toString();
                etSendText.setText("");
                String dropk = content_str.replace(" ", "");
                String droph = dropk.replace("\n", "");
                ListData l = new ListData(content_str, ListData.SEND, getTime());
                lists.add(l);
                if (lists.size() > 30) {
                    for (int i = 0; i < lists.size(); i++) {
                        lists.remove(i);
                    }
                }
                adapter.notifyDataSetChanged();
                new HttpData("http://www.tuling123.com/openapi/api?key=6af9822f5491fadfc142b53818bbd63a&info=" + droph,
                        new HttpGetDataListener() {
                            @Override
                            public void getDataUrl(String data) {
                                parseText(data);
                            }
                        }).execute();
            }
        });

    }

    private void parseText(String str) {
        try {
            JSONObject jb = new JSONObject(str);
            ListData listData = new ListData(jb.getString("text"), ListData.RECEIVE, getTime());
            lists.add(listData);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getRandomWelcomeTips() {
        String welcome_tip;
        String[] welcome_array = this.getResources()
                .getStringArray(R.array.welcome_tips);
        int index = (int) (Math.random() * (welcome_array.length - 1));
        welcome_tip = welcome_array[index];
        return welcome_tip;
    }

    private String getTime() {
        long curTime = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date d = new Date();
        String str = format.format(d);

        if (curTime - oldTime >= 60 * 1000) {
            oldTime = curTime;
            return str;
        } else {
            return "";
        }
    }
}
