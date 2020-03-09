/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.services.newsapi;

import java.io.IOException;
import java.util.List;

import cl.ucn.disc.dsm.fuenz.thenews.Transformer;
import cl.ucn.disc.dsm.fuenz.thenews.model.Noticia;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsApiNoticiaService {
    /**
     * The NewsAPI
     */
    private final NewsApi newsApi;

    /**
     * The Constructor.
     */
    public NewsApiNoticiaService() {

        // https://futurestud.io/tutorials/retrofit-getting-started-and-android-client
        this.newsApi = new Retrofit.Builder()
                // The main URL
                .baseUrl(NewsApi.BASE_URL)
                // JSON to POJO
                .addConverterFactory(GsonConverterFactory.create())
                // Validate the interface
                .validateEagerly(true)
                // Build the Retrofit ..
                .build()
                // .. get the NewsApi.
                .create(NewsApi.class);
    }

    /**
     * Get the Noticias from the Call.
     *
     * @param theCall to use.
     * @return the {@link List} of {@link Noticia}.
     */

    public  List<Noticia> getNoticiasFromCall(Call<NewsApiResult> theCall) {

        try {
            // Get the result from the call
            final Response<NewsApiResult> response = theCall.execute();

            // UnSuccessful !
            if (!response.isSuccessful()) {

                // Error!
                throw new NewsAPIException(
                        "Can't get the NewsResult, code: " + response.code(),
                        new HttpException(response)
                );
            }

            final NewsApiResult theResult = response.body();

            // No body
            if (theResult == null) {
                throw new NewsAPIException("NewsResult was null");
            }

            // No articles
            if (theResult.articles == null) {
                throw new NewsAPIException("Articles in NewsResult was null");
            }

            // Article to Noticia (transformer)
            final Transformer<Article> transformer = new Transformer<>(new ArticleNoticiaTransformer());
            return transformer.transform(theResult.articles);

        } catch (final IOException ex) {
            throw new NewsAPIException("Can't get the NewsResult", ex);
        }

    }

    public NewsApi getNewsApi(){
        return  this.newsApi;
    }

    public static final class NewsAPIException extends RuntimeException{

        public NewsAPIException(final String message) {
            super(message);
        }

        public NewsAPIException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }
}
