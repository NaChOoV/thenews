/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.services.newsapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NewsApi {
    /**
     * The URL
     */
    String BASE_URL = "https://newsapi.org/v2/";

    /**
     * The API Key
     */
    String API_KEY = "69eac618765842bba640a20d8dc2a2cf";

    /**
     * https://newsapi.org/docs/endpoints/top-headlines
     *
     * @param category to use as filter.
     * @param pageSize the number of results to get.
     * @return the call of {@link NewsApiResult}.
     */
    @Headers({"X-Api-Key: " + API_KEY})
    @GET("top-headlines")
    Call<NewsApiResult> getTopHeadlines(@Query("category") final String category, @Query("pageSize") final int pageSize);

    /**
     * https://newsapi.org/docs/endpoints/everything
     *
     * @return the call of {@link NewsApiResult}.
     */
    @Headers({"X-Api-Key: " + API_KEY})
    @GET("everything?sources=ars-technica,wired,hacker-news,recode")
    Call<NewsApiResult> getEverything(@Query("pageSize") final int pageSize);
}
