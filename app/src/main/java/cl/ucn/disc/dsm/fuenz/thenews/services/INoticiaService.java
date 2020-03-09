/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.services;

import cl.ucn.disc.dsm.fuenz.thenews.model.Noticia;
import cl.ucn.disc.dsm.fuenz.thenews.services.newsapi.NewsApi;

import java.util.List;

public interface INoticiaService {

    /**
     * Get the Noticias from the backend.
     *
     * @param pageSize how many.
     * @return the {@link List} of {@link Noticia}.
     */
    List<Noticia> getNoticias(final int pageSize);
}
