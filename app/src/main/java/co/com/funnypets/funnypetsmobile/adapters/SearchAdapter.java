package co.com.funnypets.funnypetsmobile.adapters;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import co.com.funnypets.funnypetsmobile.R;
import co.com.funnypets.funnypetsmobile.activities.otherPerfilActivity;
import co.com.funnypets.funnypetsmobile.entities.Usuario;
import co.com.funnypets.funnypetsmobile.fragments.OtherProfileFragment;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    ArrayList<String> listDatos;
    List<Usuario> usuarios;
    public Context mContext;


    public AutoCompleteTextView autoCompleteTextView = null;
    public SearchAdapter(ArrayList<Usuario> usuarios, Context context) {
        mContext = context;
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent, false);
        SearchAdapter.ViewHolder viewHolder = new SearchAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Usuario album = usuarios.get(position);
        holder.username.setText(usuarios.get(position).getUsuario());
        if(usuarios.get(position).getUrlfoto()==null){
            holder.imagenPerfil.setImageResource(R.drawable.sinperfil);
        }else{
            Glide.with(mContext).load(usuarios.get(position).getUrlfoto()).fitCenter().centerCrop().into(holder.imagenPerfil);
        }


        holder.elementView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager =  ((FragmentActivity)mContext).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                OtherProfileFragment.usuario=album;
                transaction.replace(R.id.main_fragment, new OtherProfileFragment()).commit();
                /*Intent intent2;
                intent2 = new Intent(holder.elementView.getContext(), otherPerfilActivity.class);
                intent2.putExtra("name",album.getUsuario());
                intent2.putExtra("correo",album.getCorreo());
                intent2.putExtra("se",album.getCntFollowers());
                intent2.putExtra("si",album.getCntFollowing());
                intent2.putExtra("foto",album.getUrlfoto());
                holder.elementView.getContext().startActivity(intent2);*/


            }
        });
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public void setFilter(ArrayList<Usuario> lista) {
        this.usuarios.clear();
        this.usuarios.addAll(lista);

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        ImageView imagenPerfil;
        private View elementView;


        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.search_cardView_name);
            imagenPerfil = itemView.findViewById(R.id.search_foto);
            elementView = itemView;

        }

    }
    public void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        animateCircularReveal(viewHolder.itemView);
    }
}
