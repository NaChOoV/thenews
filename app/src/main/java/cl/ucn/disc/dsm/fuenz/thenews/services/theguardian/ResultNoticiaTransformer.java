/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.services.theguardian;

import net.openhft.hashing.LongHashFunction;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZonedDateTime;

import cl.ucn.disc.dsm.fuenz.thenews.Transformer;
import cl.ucn.disc.dsm.fuenz.thenews.model.Noticia;
import cl.ucn.disc.dsm.fuenz.thenews.model.NoticiaBuilder;

/**
 * @Author Ignacio Patricio Fuenzalida Veas
 */
public class ResultNoticiaTransformer implements Transformer.NoticiaTransformer<Result> {

    private static final Logger log = LoggerFactory.getLogger(ResultNoticiaTransformer.class);

    @Override
    public Noticia transform(Result result) {

        //Verificamos que el contenido no sea nula
        if(result == null){
            throw new Transformer.NoticiaTransformerException("Edicion fue null");
        }

        // Veficicacion de campos minimos de un Contenido: Titulo y fecha de publicacion
        if(result.webTitle == null || result.webPublicationDate == null){
            throw new Transformer.NoticiaTransformerException("Title or date NULL");
        }

        /* Verificamos la existancia de los parametros de la URLfoto y el resumen, en caso de que
        no exista se reempalzaran por unos predeterminados
         */
        if(result.fields == null){
            result.fields = new Field();
            result.fields.thumbnail = null;
            result.fields.standfirst = "(*) Not avaliable content.";
        }



        //Se verifica la fecha de la publicacion y se corrije dependiendo de la zona
        ZonedDateTime publishedAt;
        try{
            publishedAt = Transformer.parseZonedDateTime(result.webPublicationDate)
                    .withZoneSameInstant(Noticia.ZONE_ID);
        } catch (Exception e){
            log.error("No es posible tranformar el webPublicationDate con threetenabp." );
            throw new Transformer.NoticiaTransformerException("Error in format time");
        }

        // The unique id (computed from hash)
        final Long theId = LongHashFunction.xx()
                .hashChars(result.webTitle + result.webPublicationDate);


        // Se eliminara la mayor cantidad  de tags HTML que contenda el body
        String resumen = result.fields.standfirst;
        if(resumen != null){
            if(resumen.contains("<p>")){
                resumen = StringUtils.substringBetween(resumen,
                        "<p>", "</p>");
                // En algunos casos puede traer un tag <br>, se debe eliminar

            }else if (resumen.contains("<li>")){
                int pos1 = resumen.indexOf("<li>");
                int pos2 = resumen.indexOf("</li>");
                resumen = resumen.substring(pos1 + 4 ,pos2);
            }
            if(resumen.contains("<br>"))
                resumen = resumen.replace("<br>","");
            if(resumen.contains("<strong>"))
                resumen = resumen.replace("<strong>","");
        }



        // Se crea la noticia con el builder y se retorna
        return new NoticiaBuilder()
                .setId(theId)
                .setTitulo(result.webTitle)
                .setFuente("@TheGuardian")
                .setAutor("theGuardian")
                .setUrl(result.webUrl)
                .setUrlFoto(result.fields.thumbnail)
                .setResumen(resumen)
                .setContenido(null)
                .setFecha(publishedAt)
                .build();
    }
}
