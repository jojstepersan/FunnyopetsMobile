package co.com.funnypets.funnypetsmobile.adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import co.com.funnypets.funnypetsmobile.R;
import co.com.funnypets.funnypetsmobile.entities.Post;


/**
 * Created by jiacontrerasp on 02/04/18.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.MyViewHolder> {

    private Context mContext;
    private List<Post> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, like,adopt;
        private View elementView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            adopt = (ImageView) view.findViewById(R.id.adopt_p);
            elementView = view;
        }
    }


    public PhotosAdapter(Context mContext, List<Post> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Post album = albumList.get(position);

        holder.elementView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,album.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfLikes() + " Likes");

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getUrlphotopost()).into(holder.thumbnail);


    }

    /**
     * Mostrar menú emergente al tocar en 3 puntos
     */


    /**
     * Evento Click listener para el menú emergente
     */


    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
