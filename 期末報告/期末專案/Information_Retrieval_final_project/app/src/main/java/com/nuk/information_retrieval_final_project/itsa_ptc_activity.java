package com.nuk.information_retrieval_final_project;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class itsa_ptc_activity extends AppCompatActivity {

    private static final String url = "https://sites.google.com/site/itsancku/home";
    private String[] string_data = new String[5];
    private String test = "";
    private String[] temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itsa_ptc_activity);
        setTitle("        ITSA & PTC 報名及考試日期");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        TextView show_enroll_start_result = (TextView) findViewById(R.id.show_enroll_start_result);
        TextView show_enroll_end_result = (TextView) findViewById(R.id.show_enroll_end_result);
        TextView show_hold_result = (TextView) findViewById(R.id.show_hold_result);

        try
        {
            Document doc = Jsoup.connect(url).get();
            Elements hold = doc.select("p");
            hold = hold.select("b");

            for(int i=0;i<1000;i++)
            {
                if(hold.get(i).text().equals("比賽日期：")) {
                    test = hold.get(i + 2).text();
                    for(int j=0;j<test.length();j++)
                    {
                        if(test.charAt(j) == '2')
                        {
                            test = test.substring(j, test.length());
                            break;
                        }
                    }

                    string_data[2] = hold.get(i + 1).text();
                    temp = test.split("~");
                    string_data[0] = temp[0];
                    string_data[1] = temp[1];
                    string_data[1] += hold.get(i + 3).text();
                    break;
                }else
                    continue;
            }

            string_data[0] = "報名開始: " + string_data[0];
            string_data[1] = "報名截止: " + string_data[1];
            string_data[2] = "考試日期: " + string_data[2];

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
