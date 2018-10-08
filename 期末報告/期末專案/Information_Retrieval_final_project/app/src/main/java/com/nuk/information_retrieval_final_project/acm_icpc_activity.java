package com.nuk.information_retrieval_final_project;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class acm_icpc_activity extends AppCompatActivity {

    private static final String url = "https://icpc.baylor.edu/regionals/upcoming";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acm_icpc_activity);
        setTitle("         ACM-ICPC 報名及考試日期");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        TextView show_result = (TextView) findViewById(R.id.show_result);

        try
        {
            Document doc = Jsoup.connect(url).get();
            String rank = "";
            Elements enroll = doc.select("tbody");
            enroll = enroll.select("tr");
            enroll = enroll.select("td");
            rank += "---------------------------------------------------------------\r\n";
            for(int i=199;i<1005;i++)
            {
                if(enroll.get(i).text().toLowerCase().contains("hua-lien")
                        || enroll.get(i).text().toLowerCase().contains("chia-yi")
                        || enroll.get(i).text().toLowerCase().contains("taichung")
                        || enroll.get(i).text().toLowerCase().contains("taipei")
                        || enroll.get(i).text().toLowerCase().contains("chung-li"))
                {
                    rank += enroll.get(i).text();
                    rank += "\r\n";
                    rank += enroll.get(i+1).text();
                    rank += "\r\n";
                    rank += enroll.get(i+2).text();
                    rank += "\r\n";
                    rank += "---------------------------------------------------------------\r\n";
                }

            }

            show_result.setText(rank);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            show_result.setText(e.toString());
        }
    }
}
