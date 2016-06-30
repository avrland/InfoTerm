package com.avrland.infoterm;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ListView list ;
    private ArrayAdapter<String> adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().setIcon(R.mipmap.icon_m);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setSubtitle("v0.1");
    }
    public void sync(View view) {
        if(isConnected()) {
            GraphView graph = (GraphView) findViewById(R.id.graph);
            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                    new DataPoint(0, 1),
                    new DataPoint(1, 5),
                    new DataPoint(2, 3),
                    new DataPoint(3, 2),
                    new DataPoint(4, 6)
            });
            graph.addSeries(series);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),"Brak połączenia z Internetem.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public void lista(View view) {
        list = (ListView) findViewById(R.id.listView);
        String cars[] = {"Ostatni pomiar:", "Średnia dobowa:", "Max:", "Min:", "Max dzień:", "Min dzień:", "Max noc:", "Min noc:" };
        ArrayList<String> carL = new ArrayList<String>();
        carL.addAll( Arrays.asList(cars) );
        adapter = new ArrayAdapter<String>(this, R.layout.row, carL);
        list.setAdapter(adapter);
    }
}
