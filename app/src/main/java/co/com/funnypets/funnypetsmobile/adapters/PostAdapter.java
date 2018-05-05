package co.com.funnypets.funnypetsmobile.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.com.funnypets.funnypetsmobile.R;
import co.com.funnypets.funnypetsmobile.entities.Post;
import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public List<Post> postList;

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.usuario.setText(postList.get(position).getUsuario().getUsuario());
        holder.descripcion.setText(postList.get(position).getDescripcion());
        holder.profile.setImageResource(postList.get(position).getPhoto());
        holder.post.setImageResource(postList.get(position).getPhoto());
        holder.likes.setText(String.valueOf(postList.get(position).getNumOfLikes())+ " likes");
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView usuario,descripcion,likes;
        private ImageView post;
        private CircleImageView profile;
        private Button adoptar,like,comentar;

        public ViewHolder(View itemView) {
            super(itemView);
            usuario=itemView.findViewById(R.id.cardView_name);
            descripcion=itemView.findViewById(R.id.cardView_description);
            profile=itemView.findViewById(R.id.fotoPerfil);
            post=itemView.findViewById(R.id.cardView_image);
            likes=itemView.findViewById(R.id.cardView_timestamp);
           // adoptar=itemView.findViewById(R.id.main_item_botton_adopt);
           // like=itemView.findViewById(R.id.main_item_botton_like);
           // comentar=itemView.findViewById(R.id.main_item_botton_comment);
        }
    }
}
