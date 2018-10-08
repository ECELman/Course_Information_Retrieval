package com.nuk.information_retrieval_final_project;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class cpe_activity extends AppCompatActivity {

    private static final String url = "https://cpe.cse.nsysu.edu.tw/newest.php";
    private String[] string_data = new String[5];
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpe_activity);
        setTitle("              CPE 報名及考試日期");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        TextView show_enroll_start_result = (TextView) findViewById(R.id.show_enroll_start_result);
        TextView show_enroll_end_result = (TextView) findViewById(R.id.show_enroll_end_result);
        TextView show_hold_result = (TextView) findViewById(R.id.show_hold_result);


        try
        {
            Document doc = Jsoup.connect(url).get();
            Elements enroll = doc.select("li");
            enroll = enroll.select("ul");
            enroll = enroll.select("li");
            Elements hold = doc.select("ol");
            hold = hold.select("li");
            string_data[0] = "報名開始: " + enroll.get(0).text().substring(0, 14);
            string_data[1] = "報名截止: " + enroll.get(1).text().substring(0, 14);
            for(int i=0;i<50;i++)
            {
                if(hold.get(i).text().matches("(.*)\\d{4}-\\d{2}-\\d{2}(.*)") || hold.get(i).text().matches("(.*)\\d{4}/\\d{2}/\\d{2}(.*)"))
                {
                    if(count == 3)
                    {
                        string_data[2] = "考試日期: " + hold.get(i).text().substring(5, 19);
                        break;
                    }else{
                        count++;
                    }

                }

            }

            show_enroll_start_result.setText(string_data[0]);
            show_enroll_end_result.setText(string_data[1]);
            show_hold_result.setText(string_data[2]);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            show_enroll_start_result.setText(e.toString());
            show_enroll_end_result.setText(e.toString());
            show_hold_result.setText(e.toString());
        }
    }
}
