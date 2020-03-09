/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.services.newsapi;

import net.openhft.hashing.LongHashFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZonedDateTime;

import cl.ucn.disc.dsm.fuenz.thenews.Transformer;
import cl.ucn.disc.dsm.fuenz.thenews.model.Noticia;
import cl.ucn.disc.dsm.fuenz.thenews.model.NoticiaBuilder;

/**
 * @Author Ignacio Patricio Fuenzalida Veas
 */

public final class ArticleNoticiaTransformer
        implements Transformer.NoticiaTransformer<Article> {

    private static final Logger log = LoggerFactory.getLogger(ArticleNoticiaTransformer.class);

    @Override
    public Noticia transform(Article article) {

        //Verificamos que el articulo no sea nulo
        if(article == null){
            throw new Transformer.NoticiaTransformerException("Articulo fue null");
        }

        // Veficicacion de campos minimos de una noticia: Descripcion y fecha de publicacion
        if(article.description == null || article.publishedAt == null){
            throw new Transformer.NoticiaTransformerException("Description or publishedAt NULL");
        }

        // Se intentara transformar la fecha dada por el articulo
        ZonedDateTime publishedAt;
        try{
            publishedAt = Transformer.parseZonedDateTime(article.publishedAt)
                    .withZoneSameInstant(Noticia.ZONE_ID);
        } catch (Exception e){
            log.error("No es posible tranformar el publishedAt con threetenabp" );
            throw new Transformer.NoticiaTransformerException("Error in format time");
        }

        //Vericiacion del titulo de la noticia
        if(article.title == null){
            log.warn("Titulo es NULL, se modificara");
            article.title = "(*)No title";
        }

        //verificamos que la fuente no sea null
        if(article.source == null){
            log.warn("La fuente es NULL, se modificara");
            article.source = new Source();
            article.source.id = "(*)No source Id";
            article.source.name = "(*)No source name";
        }

        //Verificamos que el contenido nos ea null
        if(article.content == null){
            log.warn("El contenido es NULL, se modificara");
            article.content = "(*)No content";
        }
        // The unique id (computed from hash)
        final Long theId = LongHashFunction.xx()
                .hashChars(article.title + article.publishedAt);


        //Creacion de la noticia y se retorna
        return new NoticiaBuilder()
                .setId(theId)
                .setTitulo(article.title)
                .setFuente(article.source.name)
                .setAutor(article.author)
                .setUrl(article.url)
                .setUrlFoto(article.urlToImage)
                .setResumen(article.description)
                .setContenido(article.content)
                .setFecha(publishedAt)
                .build();
    }
}
