package com.altapay.backend.repositories;

import com.altapay.backend.model.IModelFactory;

import java.util.HashMap;
import java.util.Map;

public class ShopOrderRepositoryFactory
{
    private static final Map<String, ShopOrderRepository> instances = new HashMap<>();

    public static synchronized ShopOrderRepository getInstance( IModelFactory modelFactory )
    {
        String key = modelFactory.getClass().getName();
        instances.putIfAbsent( key, new ShopOrderRepository( modelFactory ) );
        return instances.get( key );
    }
}
