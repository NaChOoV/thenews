/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.dsm.fuenz.thenews.model.Noticia;
import cl.ucn.disc.dsm.fuenz.thenews.services.newsapi.NewsApi;
import cl.ucn.disc.dsm.fuenz.thenews.services.newsapi.NewsApiResult;
import cl.ucn.disc.dsm.fuenz.thenews.services.newsapi.NewsApiNoticiaService;
import cl.ucn.disc.dsm.fuenz.thenews.services.theguardian.TheGuardianApi;
import cl.ucn.disc.dsm.fuenz.thenews.services.theguardian.TheGuardianNoticiaService;
import cl.ucn.disc.dsm.fuenz.thenews.services.theguardian.TheGuardianResult;
import retrofit2.Call;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class NoticiaService implements INoticiaService {



    /**
     * Constructor.
     */
    public NoticiaService()  {
    }

    /**
     * Get the Noticias from the backend.
     *
     * @param pageSize how many notices from each, the total is pageSizex2.
     * @return the {@link List} of {@link Noticia}.
     */
    @Override
    public List<Noticia> getNoticias(int pageSize) {


        NewsApiNoticiaService newsApiNoticiaService = new NewsApiNoticiaService();
        TheGuardianNoticiaService theGuardianNoticiaService = new TheGuardianNoticiaService();

        // the Call
        final Call<NewsApiResult> theNewsApiCall = newsApiNoticiaService.getNewsApi()
                .getEverything(pageSize);

        final Call<TheGuardianResult> theGuardianCall = theGuardianNoticiaService.getTheGuardianApi()
                .getContent(
                        TheGuardianApi.API_KEY,
                        pageSize);
        // Process the Call.

        List<Noticia> noticias1 = newsApiNoticiaService.getNoticiasFromCall(theNewsApiCall);
        List<Noticia> noticias2 = theGuardianNoticiaService.getNoticiasFromCall(theGuardianCall);

        List<Noticia> noticias = new ArrayList<>(noticias1);
        noticias.addAll(noticias2);

        //noticias.sort(Comparator.comparing(Noticia::getFecha));
        //Collections.reverse(noticias);

        return noticias;

    }

}
