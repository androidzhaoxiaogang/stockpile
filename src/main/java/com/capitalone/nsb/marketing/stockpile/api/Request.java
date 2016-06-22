package com.capitalone.nsb.marketing.stockpile.api;

import java.io.Serializable;

/**
 *
 * This is up for discussion. Intent is that this class wraps some meta about the request, however much
 * of this maybe handled orthoganally via OAUTH/JWT...
 *
 * Therefore I put the stub in here but haven't used it.
 */
public class Request<MESSAGE_TYPE> implements Serializable {
    public String publicKey;
    public String authToken;
    public String dataType;
    public MESSAGE_TYPE requestObject;

    public Request() {

    }

    public Request(MESSAGE_TYPE requestObject) {
        this.requestObject = requestObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;

        Request that = (Request) o;

        if (authToken != null ? !authToken.equals(that.authToken) : that.authToken != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (publicKey != null ? !publicKey.equals(that.publicKey) : that.publicKey != null) return false;
        if (!requestObject.equals(that.requestObject)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = publicKey != null ? publicKey.hashCode() : 0;
        result = 31 * result + (authToken != null ? authToken.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + requestObject.hashCode();
        return result;
    }
}
