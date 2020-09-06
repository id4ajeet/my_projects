
package com.ajeet.spark.app.svc.ifc;

import com.ajeet.spark.app.Application;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public interface IBooksApi {
    abstract IBookResponse add();

    IBookResponse get();

    IBookResponse getAll();

    IBookResponse delete();
}
