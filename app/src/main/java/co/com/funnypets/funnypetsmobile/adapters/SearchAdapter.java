package co.com.funnypets.funnypetsmobile.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import co.com.funnypets.funnypetsmobile.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolderDatos> {
    ArrayList<String> listDatos;

    public SearchAdapter(ArrayList<String> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView imagen;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.image);
        }

        public void asignarDatos(String s) {
            imagen.setText(s);
        }
    }
}
