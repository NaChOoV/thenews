/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import cl.ucn.disc.dsm.fuenz.thenews.model.Noticia;
import cl.ucn.disc.dsm.fuenz.thenews.services.newsapi.Article;


/**
 * @author Ignacio Fuenzalida Veas
 */
public final class Transformer<T> {
    private final NoticiaTransformer<T> noticiasTransformer;

    private static final Logger log = LoggerFactory.getLogger(Transformer.class);


    /**
     * El contructor.
     * @param noticiasTransformer
     */
    public Transformer(NoticiaTransformer<T> noticiasTransformer) {
        Objects.requireNonNull(noticiasTransformer,"No se permiten collection nulas.");
        this.noticiasTransformer = noticiasTransformer;
    }



    /**
     * Transforma una coleccion de {@link Collection} de T en una {@link List} de
     * @param collection una coleccion de T
     * @return List de noticias
     */
    public List<Noticia> transform(final Collection<T> collection){

        Objects.requireNonNull(collection,"No se permiten collection nulas.");

        final List<Noticia> noticias = new ArrayList<>(collection.size());

        for( final T t: collection){
            try {
                final Noticia noticia = this.noticiasTransformer.transform(t);
                noticias.add(noticia);
            } catch (NoticiaTransformerException ex){
                log.warn("Articulo saltado: {}",ex.getMessage());
            }
        }

        return  noticias;
    }


    /**
     * @param <T> es utlinizada como base
     */
    public interface NoticiaTransformer<T>{
        /**
         * @param t por transformar
         * @return la noticias a retornar
         */
        Noticia transform(T t);
    }

    /**
     * Convierte una fecha de {@link String} a una {@link ZonedDateTime}.
     *
     * @param fecha to parse.
     * @return the fecha.
     * @throws cl.ucn.disc.dsm.fuenz.thenews.Transformer.NoticiaTransformerException en caso de no lograr
     *                                                                               convertir la fecha.
     */
    public static ZonedDateTime parseZonedDateTime(final String fecha) {

        // Na' que hacer si la fecha no existe
        if (fecha == null) {
            throw new NoticiaTransformerException("Can't parse null fecha");
        }

        try {
            // Tratar de convertir la fecha ..
            return ZonedDateTime.parse(fecha);
        } catch (NoticiaTransformerException ex) {

            // Mensaje de debug
            log.error("Can't parse date: ->{}<-. Error: ", fecha, ex);

            // Anido la DateTimeParseException en una NoticiaTransformerException.
            throw new NoticiaTransformerException("Can't parse date: " + fecha, ex);
        }
    }

    /**
     * Transforma en String un objeto t mostrando sus atributos.
     *
     * @param t   to convert.
     * @param <T> type of t.
     * @return the object in string format.
     */
    public static <T> String toString(final T t) {
        return ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE);
    }

    public static final class NoticiaTransformerException extends RuntimeException {

        /**
         * @see RuntimeException
         */
        public NoticiaTransformerException(final String message) {
            super(message);
        }
        /**
         * @see RuntimeException
         */
        public NoticiaTransformerException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }


}
