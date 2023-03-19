package com.altapay.util;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public final class HttpUtil
{
    public StringBuffer doPostAuthorized(
        URL url, HttpParamList params, String username, String password ) throws IOException
    {
        // We don't need to implement this, write the rest of the code as if this just works
        return null;
    }
}