/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.model;

import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

public class NoticiaBuilder {

    private Long id;
    private String titulo;
    private String fuente;
    private String autor;
    private String url;
    private String urlFoto;
    private String resumen;
    private String contenido;
    private ZonedDateTime fecha;

    public NoticiaBuilder setId(Long id){
        this.id = id;
        return this;
    }
    public NoticiaBuilder setTitulo(String titulo){
        this.titulo = titulo;
        return this;
    }
    public NoticiaBuilder setFuente(String fuente){
        this.fuente = fuente;
        return this;
    }
    public NoticiaBuilder setAutor(String autor){
        this.autor = autor;
        return this;
    }
    public NoticiaBuilder setUrl(String url){
        this.url = url;
        return this;
    }
    public NoticiaBuilder setUrlFoto(String urlFoto){
        this.urlFoto = urlFoto;
        return this;
    }
    public NoticiaBuilder setResumen(String resumen){
        this.resumen = resumen;
        return this;
    }
    public NoticiaBuilder setContenido(String contenido){
        this.contenido = contenido;
        return this;
    }
    public NoticiaBuilder setFecha(ZonedDateTime fecha){
        this.fecha = fecha;
        return this;
    }

    public Noticia build(){
        return new Noticia(id, titulo, fuente, autor, url, urlFoto,
                resumen, contenido, fecha);
    }


}
