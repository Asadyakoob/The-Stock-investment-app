package butterfield.mentorschools.org.listapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    //Testing pull request
    ListView myListView;
    Button go;
    EditText searchBar;
    String searchText;
    //String[] companies;
    //String[] percentChange;
    ///String[] ticker;
    String[] loadCompanies;
    ArrayAdapter<String> adapter;


    ArrayList<String> arrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        searchBar = (EditText) findViewById(R.id.searchBar);
        go = (Button) findViewById(R.id.button);

        new PopulateFollowedList(this).populate();



        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendSearch = new Intent(MainActivity.this, inDepthAnalysis.class);

                searchText = searchBar.getText().toString();
                sendSearch.putExtra("userInput",searchText);
                startActivity(sendSearch);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new PopulateFollowedList(this).populate();

    }
}
