package com.example.eric.movieranking;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class movie_ranking extends AppCompatActivity {

    private Button get_ranking;

    private TextView show_result;

    private static final String url = "https://www.rottentomatoes.com/top/bestofrt/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_ranking);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        find_views();
        set_listeners();
    }

    private void find_views()
    {
        get_ranking = (Button) findViewById(R.id.get_ranking);
        show_result = (TextView) findViewById(R.id.show_result);
        show_result.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void set_listeners()
    {
        get_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Document doc = Jsoup.connect(url).get();
                    String word = "";

                    boolean check = false;
                    for(Element data : doc.select("tr"))
                    {
                        String rank = data.select("td.bold").text();

                        if(rank.equals("1.")) check = true;

                        if(check) word += rank + " " + data.select("a").text() + "\n\n";

                        if(rank.equals("100.")) check = false;
                    }

                    show_result.setText(word);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    show_result.setText(e.toString());
                }
            }
        });
    }
}
