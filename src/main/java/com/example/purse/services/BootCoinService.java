package com.example.purse.services;

import com.example.purse.models.entities.BootCoin;
import com.example.purse.repositories.IBootCoinRepository;
import com.example.purse.repositories.IRepository;
import com.netflix.discovery.converters.Auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BootCoinService extends BaseService<BootCoin, String> implements IBootCoinService  {

    private final IBootCoinRepository iBootCoinRepository;

    @Autowired
    public BootCoinService(IBootCoinRepository iBootCoinRepository) {
        this.iBootCoinRepository = iBootCoinRepository;
    }

    @Override
    protected IRepository<BootCoin, String> getRepository() {
        return iBootCoinRepository;
    }


    
}
