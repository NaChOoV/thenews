package cl.ucn.disc.dsm.fuenz.thenews.activities.adapters;/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

import androidx.recyclerview.widget.RecyclerView;

import org.ocpsoft.prettytime.PrettyTime;
import org.threeten.bp.DateTimeUtils;

import java.util.Date;

import cl.ucn.disc.dsm.fuenz.thenews.databinding.RowNoticiaBinding;
import cl.ucn.disc.dsm.fuenz.thenews.model.Noticia;

public final class NoticiaViewHolder extends RecyclerView.ViewHolder{
    /**
     * The Bindings
     */
    private final RowNoticiaBinding binding;

    /**
     * The Date formatter
     */
    private static final PrettyTime PRETTY_TIME = new PrettyTime();

    /**
     * The Constructor.
     *
     * @param rowNoticiaBinding to use.
     */
    public NoticiaViewHolder(RowNoticiaBinding rowNoticiaBinding) {
        super(rowNoticiaBinding.getRoot());
        this.binding = rowNoticiaBinding;
    }

    /**
     * Bind the Noticia to the ViewHolder.
     *
     * @param noticia to bind.
     */
    public void bind(final Noticia noticia) {

        this.binding.tvTitulo.setText(noticia.getTitulo());
        this.binding.tvResumen.setText(noticia.getResumen());
        this.binding.tvAutor.setText(noticia.getAutor());
        this.binding.tvFuente.setText(noticia.getFuente());

        final Date date = DateTimeUtils.toDate(noticia.getFecha().toInstant());
        this.binding.tvFecha.setText(PRETTY_TIME.format(date));

        if (noticia.getUrlFoto() != null) {
            // .. set the uri
            this.binding.sdvFoto.setImageURI(noticia.getUrlFoto());
        } else {
            // .. set a default image
            this.binding.sdvFoto.setImageURI("https://perfectstart.com.au/wp-content/uploads/2017/08/not-available.jpg");        }



    }

}
