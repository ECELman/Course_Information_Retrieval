package com.nuk.information_retrieval_final_project;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ncpc_activity extends AppCompatActivity {

    private static final String[] url = {"http://ncpc.nsysu.edu.tw/files/11-1351-7771.php?Lang=zh-tw", "http://ncpc.nsysu.edu.tw/files/11-1351-99.php?Lang=zh-tw"};
    private String test;
    private String[] string_data = new String[5];
    private String[] temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncpc_activity);
        setTitle("             NCPC 報名及考試日期");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        TextView show_enroll_start_result = (TextView) findViewById(R.id.show_enroll_start_result);
        TextView show_enroll_end_result = (TextView) findViewById(R.id.show_enroll_end_result);
        TextView show_hold_preliminary_result = (TextView) findViewById(R.id.show_hold_preliminary_result);
        TextView show_hold_final_result = (TextView) findViewById(R.id.show_hold_final_result);

        try
        {
            Document doc = Jsoup.connect(url[1]).get();
            Elements enroll = doc.select("ol");
            test = enroll.get(0).text();
            temp = test.split("\\)");

            doc = Jsoup.connect(url[0]).get();
            enroll = doc.select("strong");
            for(int i=0;i<100;i++)
            {
                test = enroll.get(i).text();
                if(test.contains("報名時間"))
                    break;
            }

            string_data[2] = temp[0] + ")";
            string_data[3] = temp[1].substring(1, temp[1].length()) + ")";

            for(int j=0;j<test.length();j++)
            {
                if(test.charAt(j) == '1')
                {
                    test = test.substring(j, test.length());
                    break;
                }
            }

            temp = test.split("至");
            temp[1] = "106年" + temp[1];
            string_data[0] = temp[0];
            test = temp[1];
            temp = test.split("止");
            string_data[1] = temp[0];

            string_data[0] = "報名開始: " + string_data[0];
            string_data[1] = "報名截止: " + string_data[1];
            string_data[2] = "初賽日期: " + string_data[2].replace("星期", "").replace(" ", "");
            string_data[3] = "複賽日期: " + string_data[3].replace("星期", "").replace(" ", "");

            show_enroll_start_result.setText(string_data[0]);
            show_enroll_end_result.setText(string_data[1]);
            show_hold_preliminary_result.setText(string_data[2]);
            show_hold_final_result.setText(string_data[3]);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            show_enroll_start_result.setText(e.toString());
            show_enroll_end_result.setText(e.toString());
            show_hold_preliminary_result.setText(e.toString());
            show_hold_final_result.setText(e.toString());
        }
    }
}
