package co.com.funnypets.funnypetsmobile.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import co.com.funnypets.funnypetsmobile.R;
import co.com.funnypets.funnypetsmobile.activities.PhotoModel;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            foto= (ImageView)itemView.findViewById(R.id.imageView_id);
            cardView=(CardView)itemView.findViewById(R.id.cardview_id);
        }
    }

    public List<PhotoModel> photoList;

    public RecyclerViewAdapter(List<PhotoModel> photoList) {
        this.photoList = photoList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo,parent,false);

    ViewHolder viewHolder= new ViewHolder(view);
    return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.foto.setImageResource(photoList.get(position).getFoto());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
}
