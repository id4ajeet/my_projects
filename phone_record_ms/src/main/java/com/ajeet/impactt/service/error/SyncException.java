/*
 * (c) Copyright 2020 Brite:Bill Ltd.
 *
 * 23 Windsor Place, Dublin 2, Ireland
 * info@britebill.com
 * +353 1 661 9426
 */
package com.ajeet.impactt.service.error;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class SyncException extends RuntimeException{
    public SyncException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
