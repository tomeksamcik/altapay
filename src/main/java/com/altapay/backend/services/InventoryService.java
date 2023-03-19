package com.altapay.backend.services;

import com.altapay.backend.model.Inventory;
import com.altapay.backend.model.Product;
import com.altapay.backend.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService
{
    private InventoryRepository repository;

    @Autowired
    public InventoryService( InventoryRepository repository )
    {
        this.repository = repository;
    }

    public boolean checkInventory( Product product, int quantity )
    {
        Inventory inventory = repository.load( product.getId() );
        return inventory.getCount() >= quantity;
    }

    public boolean takeFromInventory( Product product, int quantity )
    {
        Inventory inventory = repository.load( product.getId() );
        inventory.setCount( inventory.getCount() - quantity );
        try
        {
            repository.save( inventory );
        } catch ( Exception e )
        {
            return false;
        }
        return true;
    }
}
