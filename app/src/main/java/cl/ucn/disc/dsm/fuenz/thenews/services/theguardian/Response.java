/*
 * Copyright (c) 2020. This code is purely educational, the rights of use are
 *  reserved, the owner of the code is Ignacio Fuenzalida Veas
 *  contact ignacio.fuenzalida@alumnos.ucn.cl
 *  Do not use in production.
 */

package cl.ucn.disc.dsm.fuenz.thenews.services.theguardian;

import java.util.ArrayList;
import java.util.List;

public class Response {
    public String status;
    public String userTier;
    public int total;
    public int starIndex;
    public int pageSize;
    public int currentPage;
    public int pages;
    public String orderBy;
    public List<Result> results = new ArrayList<Result>();
}
