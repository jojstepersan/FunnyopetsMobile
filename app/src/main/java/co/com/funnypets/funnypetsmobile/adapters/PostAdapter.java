package co.com.funnypets.funnypetsmobile.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.com.funnypets.funnypetsmobile.R;
import co.com.funnypets.funnypetsmobile.entities.Post;

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
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView usuario,descripcion;
        private ImageView profile,post;
        private ImageButton adoptar,like,comentar;

        public ViewHolder(View itemView) {
            super(itemView);
            usuario=itemView.findViewById(R.id.main_item_name_profile);
            descripcion=itemView.findViewById(R.id.main_item_descrption);
            profile=itemView.findViewById(R.id.main_item_img_profile);
            post=itemView.findViewById(R.id.main_item_img);
            adoptar=itemView.findViewById(R.id.main_item_botton_adopt);
            like=itemView.findViewById(R.id.main_item_botton_like);
            comentar=itemView.findViewById(R.id.main_item_botton_comment);
        }
    }
}
