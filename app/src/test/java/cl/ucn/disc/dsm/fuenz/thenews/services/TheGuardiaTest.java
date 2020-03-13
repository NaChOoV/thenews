/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.services;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import cl.ucn.disc.dsm.fuenz.thenews.model.Noticia;
import cl.ucn.disc.dsm.fuenz.thenews.services.theguardian.TheGuardianApi;
import cl.ucn.disc.dsm.fuenz.thenews.services.theguardian.TheGuardianNoticiaService;
import cl.ucn.disc.dsm.fuenz.thenews.services.theguardian.TheGuardianResult;
import retrofit2.Call;

public class TheGuardiaTest {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(TheGuardiaTest.class);

    @Test  //Testing para recibir las noticas.
    public void testRetrofit() throws IOException {
        log.debug("Obteniendo noticicas");
        TheGuardianNoticiaService theGuardianNoticiaService = new TheGuardianNoticiaService();

        final Call<TheGuardianResult> theGuardianCall = theGuardianNoticiaService.getTheGuardianApi()
                .getContent(
                        TheGuardianApi.API_KEY,
                        20,
                        "standfirst,thumbnail");

        List<Noticia> noticias = theGuardianNoticiaService.getNoticiasFromCall(theGuardianCall);

        for(Noticia noticia : noticias){
            log.debug(noticia.getResumen());

        }

    }
}
