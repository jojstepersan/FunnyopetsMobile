package co.com.funnypets.funnypetsmobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import co.com.funnypets.funnypetsmobile.R;
import co.com.funnypets.funnypetsmobile.activities.MainActivity;
import co.com.funnypets.funnypetsmobile.activities.PostDetailActivity;
import co.com.funnypets.funnypetsmobile.activities.ProfileActivity;
import co.com.funnypets.funnypetsmobile.entities.Post;
import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> implements View.OnClickListener{

    public List<Post> postList;
    public Context mContext;
    private View.OnClickListener listener;
    static boolean bo=false;

    public PostAdapter(Context mContext, List<Post> postList) {
        this.postList = postList;
        this.mContext =mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        setOnclickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.usuario.setText(postList.get(position).getUsuario().getUsuario());
        holder.descripcion.setText(postList.get(position).getDescripcion());
        if(postList.get(position).getUsuario().getUrlfoto()==null){
            holder.profile.setImageResource(R.drawable.sinperfil);
        }else{
            Glide.with(mContext).load(postList.get(position).getUsuario().getUrlfoto()).fitCenter().centerCrop().into(holder.profile);
        }
        Glide.with(mContext).load(postList.get(position).getUrlphotopost()).fitCenter().centerCrop().into(holder.post);
        holder.likes.setText(String.valueOf(postList.get(position).getNumOfLikes())+ " likes");
        bo=false;


    }


    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setOnclickListener(View.OnClickListener listener){

        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){}
            listener.onClick(view);



    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView usuario,descripcion,likes;
        private ImageView post;
        private CircleImageView profile;
        private ImageView adoptar,like,comentar;


        public ViewHolder(final View itemView) {
            super(itemView);
            usuario=itemView.findViewById(R.id.cardView_name);
            descripcion=itemView.findViewById(R.id.cardView_description);
            profile=itemView.findViewById(R.id.fotoPerfil);
            post=itemView.findViewById(R.id.cardView_image);
            likes=itemView.findViewById(R.id.cardView_timestamp);
            adoptar=itemView.findViewById(R.id.cardView_send);
            like=itemView.findViewById(R.id.cardView_heart);
            comentar=itemView.findViewById(R.id.cardView_comment);
            adoptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast1 =
                            Toast.makeText(itemView.getContext(),
                                    "Has dado like", Toast.LENGTH_SHORT);

                    toast1.show();
                    if(bo==false) {
                        like.setImageResource(R.drawable.icon_heart_r);
                    }if(bo==true){
                        like.setImageResource(R.drawable.icon_heart);
                    }
                }
            });




            adoptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent hola= new Intent(itemView.getContext(), PostDetailActivity.class);
                    hola.putExtra("pos",getAdapterPosition());
                    itemView.getContext().startActivity(hola);
                }
            });



            // adoptar=itemView.findViewById(R.id.main_item_botton_adopt);
           // like=itemView.findViewById(R.id.main_item_botton_like);
           // comentar=itemView.findViewById(R.id.main_item_botton_comment);

        }
    }
}
