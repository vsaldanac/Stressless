package cl.vero.stressless.Views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.vero.stressless.R;
import cl.vero.stressless.adapters.PendingClickListener;
import cl.vero.stressless.adapters.PendingsAdapter;
import cl.vero.stressless.models.Pending;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements PendingClickListener {

    public static final String ITEM_KEY = "cl.vero.stressless.KEY.ITEM_KEY";

    private PendingsAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.pendingRv);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        for (int i = 0; i < 9 ; i++) {
            Pending pending = new Pending();
            pending.setName(String.valueOf(i));
            pending.setDone(false);
            pending.save();
        }

        adapter = new PendingsAdapter(this);
        recyclerView.setAdapter(adapter);


    }

    public void updateList(Pending pending){
        adapter.update(pending);

    }

    @Override
    public void clickedId(long id) {
        Intent intent = new Intent(getContext(),DetailsActivity.class);
        intent.putExtra(ITEM_KEY,id);
        startActivity(intent);

    }
}
