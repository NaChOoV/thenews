/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.services.theguardian;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import cl.ucn.disc.dsm.fuenz.thenews.Transformer;
import cl.ucn.disc.dsm.fuenz.thenews.model.Noticia;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TheGuardianNoticiaService {

    private final TheGuardianApi theGuardianApi;

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(TheGuardianNoticiaService.class);

    public TheGuardianNoticiaService() {
        // https://futurestud.io/tutorials/retrofit-getting-started-and-android-client
        this.theGuardianApi = new Retrofit.Builder()
                // The main URL
                .baseUrl(TheGuardianApi.BASE_URL)
                // JSON to POJO
                .addConverterFactory(GsonConverterFactory.create())
                // Validate the interface
                .validateEagerly(true)
                // Build the Retrofit ..
                .build()
                // .. get the Content.
                .create(TheGuardianApi.class);

    }

    public List<Noticia> getNoticiasFromCall(Call<TheGuardianResult> theCall) {

        try {
            // Get the result from the call
            final Response<TheGuardianResult> response = theCall.execute();

            // UnSuccessful !
            if (!response.isSuccessful()) {

                // Error!
                throw new TheGuardianAPIException(
                        "Can't get the NewsResult, code: " + response.code(),
                        new HttpException(response)
                );

            }

            final TheGuardianResult theResult = response.body();

            // No body
            if (theResult == null) {
                throw new TheGuardianAPIException("ContentResult was null");
            }

            // No articles
            if (theResult.response == null) {
                throw new TheGuardianAPIException("Content in NewsResult was null");
            }


            // Article to Noticia (transformer)
            final Transformer<Result> transformer = new Transformer<>(new ResultNoticiaTransformer());
            return transformer.transform(theResult.response.results);

        } catch (final IOException ex) {
            throw new TheGuardianAPIException("Can't get the NewsResult", ex);
        }

    }

    public TheGuardianApi getTheGuardianApi(){
        return this.theGuardianApi;
    }

    public static final class TheGuardianAPIException extends RuntimeException{

        public TheGuardianAPIException(final String message) {
            super(message);
        }

        public TheGuardianAPIException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }
}
