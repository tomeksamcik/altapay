package com.altapay.backend.services;

import com.altapay.backend.exceptions.MerchantApiServiceException;
import com.altapay.backend.model.ShopOrder;
import com.altapay.util.HttpUtil;
import com.altapay.util.XpathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantApiService
{
    private HttpUtil httpUtil;

    private XpathUtil xpathUtil;

    @Autowired
    public MerchantApiService( HttpUtil httpUtil, XpathUtil xpathUtil )
    {
        this.httpUtil = httpUtil;
        this.xpathUtil = xpathUtil;
    }

    public CaptureResponse capturePayment( ShopOrder shopOrder ) throws MerchantApiServiceException
    {
        // We don't need to implement this, write the rest of the code as if this has been implemented by use of the httpUtil and the xpathUtil
        return null;
    }

    public ReleaseResponse releasePayment( ShopOrder shopOrder ) throws MerchantApiServiceException
    {
        // We don't need to implement this, write the rest of the code as if this has been implemented by use of the httpUtil and the xpathUtil
        return null;
    }
}
