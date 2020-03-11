/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.services.theguardian;

import cl.ucn.disc.dsm.fuenz.thenews.services.newsapi.NewsApiResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface TheGuardianApi {
    /**
     * The URL
     */
    String BASE_URL = "http://content.guardianapis.com/";

    /**
     * The API Key
     */
    String API_KEY = "bc54e73f-0b58-4356-8308-28de89e1dc8d";

    /**
     * https://newsapi.org/docs/endpoints/everything
     *
     * @return the call of {@link NewsApiResult}.
     */
    //@Headers({"api-key: " + API_KEY})
    @GET("search")
    Call<TheGuardianResult> getContent(
            @Query("api-key") String API_KEY,
            @Query("page-size") int page_size);
}
