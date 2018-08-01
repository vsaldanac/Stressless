package cl.vero.stressless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cl.vero.stressless.models.Pending;

public class DetailsActivity extends AppCompatActivity {

private Pending pending;
private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        editText = findViewById(R.id.detailsEt);

        long id = getIntent().getLongExtra(MainActivityFragment.ITEM_KEY,0);
        Integer ide = (int) id;
        Log.d("TAG_ID", String.valueOf(ide));

        pending = Pending.findById(Pending.class,ide);
        getSupportActionBar().setTitle(pending.getName());
        Log.d("TAG_ID", pending.getName());

    }

    @Override
    protected void onResume() {
        if (pending.getDescription() != null){
            editText.setText(pending.getDescription());
        }
        super.onResume();

    }

    @Override
    protected void onPause() {
        String description = String.valueOf(editText.getText());
        pending.setDescription(description);
        pending.save();
        super.onPause();
    }
}
