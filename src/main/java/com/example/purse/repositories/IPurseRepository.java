package com.example.purse.repositories;

import com.example.purse.models.entities.Purse;

import reactor.core.publisher.Mono;

public interface IPurseRepository extends IRepository<Purse,String> {
}
