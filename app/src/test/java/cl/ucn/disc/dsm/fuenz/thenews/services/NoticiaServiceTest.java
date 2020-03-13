/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.services;
import cl.ucn.disc.dsm.fuenz.thenews.model.Noticia;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoticiaServiceTest {
    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(NoticiaServiceTest.class);

    /**
     * Test {@link INoticiaService#getNoticias(int)}
     */
    @Test
    public void testGetNoticias() {

        log.debug("Testing the NoticiaService ..");

        // The noticia service
        final NoticiaService noticiaService = new NoticiaService();

        // The List of Noticia.
        final List<Noticia> noticias = noticiaService.getNoticias(10);

        Assertions.assertNotNull(noticias);
        Assertions.assertEquals(noticias.size(), 20, "Error de tamanio");


        for (final Noticia noticia : noticias) {
            log.debug(noticia.getFecha().toString()+ " " + noticia.getId());
        }


        log.debug("Done.");
    }
}
