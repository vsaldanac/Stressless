package cl.vero.stressless.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import cl.vero.stressless.Data.Queries;
import cl.vero.stressless.R;
import cl.vero.stressless.models.Pending;

public class PendingsAdapter extends RecyclerView.Adapter<PendingsAdapter.ViewHolder>{

    List<Pending> pendings = new Queries().pendings();
    private PendingClickListener listener;

    public PendingsAdapter(PendingClickListener pendingClickListener) {
        this.listener = pendingClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_pending,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final Pending pending = pendings.get(i);
        viewHolder.textView.setText(pending.getName());
        viewHolder.checkBox.setChecked(pending.isDone());

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        int auxPosition = viewHolder.getAdapterPosition();
                        Pending auxPending = pendings.get(auxPosition);
                        auxPending.setDone(true);
                        auxPending.save();
                        pendings.remove(auxPosition);
                        notifyItemRemoved(auxPosition);

                    }
                },400);

            }
        });

        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pending auxPending = pendings.get(viewHolder.getAdapterPosition());
                listener.clickedId(auxPending.getId());


            }
        });

    }

    @Override
    public int getItemCount() {
        return pendings.size();
    }

    public void update(Pending pending){
        pendings.add(pending);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.pendingCb);
            textView = itemView.findViewById(R.id.pendingTv);
        }
    }

}

