/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.services.newsapi;

import java.util.ArrayList;
import java.util.List;

public class NewsApiResult {
    public String status;
    public long totalResults;
    public List<Article> articles = new ArrayList<Article>();
}
