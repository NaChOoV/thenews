/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.model;

import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

public class Noticia {
    /**
     * The Zone
     */
    public static final ZoneId ZONE_ID = ZoneId.of("-3");

    /**
     * The Id
     */
    private final Long id;

    /**
     * The Titulo
     */
    private final String titulo;

    /**
     * The Fuente
     */
    private final String fuente;

    /**
     * The Author
     */
    private final String autor;

    /**
     * The URL
     */
    private final String url;

    /**
     * The URL of Photo
     */
    private final String urlFoto;

    /**
     * The Resumen
     */
    private final String resumen;

    /**
     * The Contenido
     */
    private final String contenido;

    /**
     * The Fecha
     */
    private final ZonedDateTime fecha;

    /**
     * The Constructor.
     *
     * @param id
     * @param titulo
     * @param fuente
     * @param autor
     * @param url
     * @param urlFoto
     * @param resumen
     * @param contenido
     * @param fecha
     */
    public Noticia(Long id, String titulo, String fuente, String autor, String url, String urlFoto,
                   String resumen, String contenido, ZonedDateTime fecha) {
        this.id = id;
        this.titulo = titulo;
        this.fuente = fuente;
        this.autor = autor;
        this.url = url;
        this.urlFoto = urlFoto;
        this.resumen = resumen;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    /**
     * @return The id.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @return The titulo.
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * @return The fuente.
     */
    public String getFuente() {
        return this.fuente;
    }

    /**
     * @return The autor.
     */
    public String getAutor() {
        return this.autor;
    }

    /**
     * @return The url.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * @return The urlFoto.
     */
    public String getUrlFoto() {
        return this.urlFoto;
    }

    /**
     * @return The resumen.
     */
    public String getResumen() {
        return this.resumen;
    }

    /**
     * @return The contenido.
     */
    public String getContenido() {
        return this.contenido;
    }

    /**
     * @return The fecha.
     */
    public ZonedDateTime getFecha() {
        return this.fecha;
    }



}
