package com.nuk.information_retrieval_final_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    private Intent intent;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //設定隱藏標題
        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.FLAG_LAYOUT_ATTACHED_IN_DECOR);

        TextView bar_title = (TextView)findViewById(R.id.bar_title);
        bar_title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Kristen_ITC_Regular.TTF"));
        Button cpe_button = (Button)findViewById(R.id.cpe_button);
        Button itsa_ptc_button = (Button)findViewById(R.id.itsa_ptc_button);
        Button ncpc_button = (Button)findViewById(R.id.ncpc_button);
        Button acm_icpc_button = (Button)findViewById(R.id.acm_icpc_button);
        cpe_button.setOnClickListener(buttonListener);
        itsa_ptc_button.setOnClickListener(buttonListener);
        ncpc_button.setOnClickListener(buttonListener);
        acm_icpc_button.setOnClickListener(buttonListener);

    }
    private Button.OnClickListener buttonListener = new Button.OnClickListener()
    {
        public void onClick(View v)
        {
            if(v.getId() == R.id.cpe_button)
            {
                waitDialog();
                intent = new Intent("com.nuk.information_retrieval_final_project.cpe_activity");
                startActivity(intent);
            }else if(v.getId() == R.id.itsa_ptc_button)
            {
                waitDialog();
                intent = new Intent("com.nuk.information_retrieval_final_project.itsa_ptc_activity");
                startActivity(intent);
            }else if(v.getId() == R.id.ncpc_button)
            {
                waitDialog();
                intent = new Intent("com.nuk.information_retrieval_final_project.ncpc_activity");
                startActivity(intent);
            }else if(v.getId() == R.id.acm_icpc_button)
            {
                waitDialog();
                intent = new Intent("com.nuk.information_retrieval_final_project.acm_icpc_activity");
                startActivity(intent);
            }
        }
    };

    public void waitDialog(){
        dialog = ProgressDialog.show(MainMenu.this,
                "讀取中", "請等待3秒...",true);
        new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally{
                    dialog.dismiss();
                }
            }
        }).start();
    }

}
