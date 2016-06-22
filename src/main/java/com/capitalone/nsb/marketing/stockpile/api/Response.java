package com.capitalone.nsb.marketing.stockpile.api;

import java.io.Serializable;

/**
 * Created by cmathias on 2/4/16.
 */
public class Response<MESSAGE_TYPE> implements Serializable {
    public ResponseType responseType;
    public MESSAGE_TYPE payload;

    public Response() {

    }

    public Response(MESSAGE_TYPE payload) {
        this.responseType = ResponseType.SUCCESS;
        this.payload = payload;
    }

    public Response(MESSAGE_TYPE payload, ResponseType responseType) {
        this.responseType = responseType;
        this.payload = payload;
    }

}
