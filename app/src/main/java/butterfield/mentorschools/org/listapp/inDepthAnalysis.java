package butterfield.mentorschools.org.listapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class inDepthAnalysis extends AppCompatActivity {
    //private static final String FILE_NAME = "followedCompanies.txt";    //SAVE FOLLOWED COMPANIES HERE
    private static final String FILE_NAME = "LocalStorage.txt";

    public static TextView companyName;
    public static TextView companyTicker;
    public static TextView Open;
    public static TextView close;
    public static TextView dailyhigh;
    public static TextView dailyLow;
    public static TextView vol;
    String[] tickers;
    Button followUnfollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_depth_analysis);

        Intent in = getIntent();
        String tickerInput =  in.getStringExtra("userInput");

        companyName = (TextView) findViewById(R.id.companyName);
        companyTicker = (TextView) findViewById(R.id.companyTicker);
        Open = (TextView) findViewById(R.id.open);
        close = (TextView) findViewById(R.id.previousClose);
        dailyhigh = (TextView) findViewById(R.id.dailyHigh);
        dailyLow = (TextView) findViewById(R.id.dailyLow);
        vol = (TextView) findViewById(R.id.shareVolume);

        followUnfollow = (Button) findViewById(R.id.followUnfollow);
        Resources res= getResources();
        tickers = res.getStringArray(R.array.tickers);

        new fetchData().execute("http://13.59.22.125/" + tickerInput);


        //FOR FRONT END TESTING ONLY
        companyTicker.setText("aapl");
        companyName.setText("Apple, Inc.");
        for (String ticker: tickers)
        {
            String[] pair = ticker.split(":");

            String key = pair[0];
            String value = pair[1];

            if (tickerInput.equals(key)) //if you find a recognized ticker populate ticker and company
            {
                companyTicker.setText(key.toUpperCase());
                companyName.setText(value);
                break;
            }
            else
            {
                companyTicker.setText("null");
                companyName.setText("nulL");
            }
        }




        followUnfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  save(v);                //currently only saves company to file. Does not remove if already following
                //currently only saves company to file. Does not remove if already following
                String cName = companyName.getText().toString();
                String cTicker = companyTicker.getText().toString();
                String concat = cName + ":" + cTicker + "\n";

//loads currently followed companies
                String[] loadCompanies = new LocalStorage().load(getApplicationContext());

                Boolean check= new LocalStorage().check(loadCompanies,cTicker);
                if(!check)
                {
                    new LocalStorage().save(concat,getApplicationContext());
                }

//new LocalStorage().save(concat,getApplicationContext());


            }
        });

    }

  /*  public void save(View v) {
        String saveName = companyName.getText().toString();
        String saveTicker = companyTicker.getText().toString();
        String concat = saveName + ":" + saveTicker;
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(concat.getBytes());

            Toast.makeText(this,"Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null)
            {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    */

}
