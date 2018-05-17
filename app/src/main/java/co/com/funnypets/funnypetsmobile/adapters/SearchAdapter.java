package co.com.funnypets.funnypetsmobile.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import co.com.funnypets.funnypetsmobile.R;
import co.com.funnypets.funnypetsmobile.entities.Usuario;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.username.setText(usuarios.get(position).getUsuario());
        Glide.with(mContext).load(usuarios.get(position).getUrlfoto()).fitCenter().centerCrop().into(holder.imagenPerfil);
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


        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.search_cardView_name);
            imagenPerfil = itemView.findViewById(R.id.search_foto);
        }

    }
}
