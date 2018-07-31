package cl.vero.stressless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import cl.vero.stressless.models.Pending;

public class DetailsActivity extends AppCompatActivity {

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        long id = getIntent().getLongExtra(MainActivityFragment.ITEM_KEY,0);
        Integer ide = (int) id;
        Log.d("TAG_ID", String.valueOf(ide));





    }
}
