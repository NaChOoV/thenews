/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.activities.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import cl.ucn.disc.dsm.fuenz.thenews.databinding.RowNoticiaBinding;
import cl.ucn.disc.dsm.fuenz.thenews.model.Noticia;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaViewHolder> {
    /**
     * The List of Noticias.
     */
    private List<Noticia> theNoticias;

    /**
     * The Constructor.
     */
    public NoticiaAdapter() {
        this.theNoticias = new ArrayList<>();
    }
    /**
     * Change the current List of Noticias.
     *
     * @param noticias to use.
     */
    public void setNoticias(final List<Noticia> noticias) {

        // Update the noticias
        if(this.theNoticias.size() == 0){
            this.theNoticias = noticias;
        }else{
            for(Noticia noticia : noticias){
                if(findNoticia(noticia.getId()))
                    continue;
                this.theNoticias.add(noticia);
            }

        }

        //Ordenamos por fecha
        noticias.sort(Comparator.comparing(Noticia::getFecha));
        Collections.reverse(noticias);


        // Notify to re-layout
        this.notifyDataSetChanged();
    }

    /**
     * Metodo que compara el nuevo id unico con todas las noticias de la aplicacion, de esta manera
     * para asegurarnos de que las nuevas noticias nnten repetidas.
     * @param id
     * @return boolean
     */
    private boolean findNoticia(Long id){
        for(Noticia noticia : this.theNoticias){
            if(noticia.getId().equals(id))
                return true;
        }
        return false;
    }
    /**
     * Called when RecyclerView needs a newViewHolder of the given type to represent an item.
     */
    @Override
    public NoticiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new NoticiaViewHolder(RowNoticiaBinding.inflate(layoutInflater, parent, false));

    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should update the contents of the
     * ViewHolder to reflect the item at the given position.
     */
    @Override
    public void onBindViewHolder(@NonNull NoticiaViewHolder holder, int position) {
        holder.bind(this.theNoticias.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    @Override
    public int getItemCount() {
        return this.theNoticias.size();
    }
}
