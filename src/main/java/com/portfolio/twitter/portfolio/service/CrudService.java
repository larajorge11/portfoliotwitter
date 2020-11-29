package com.portfolio.twitter.portfolio.service;

import java.util.List;

public interface CrudService<T, ID> {
    List<T> findAll();

    T findById(ID id);

    T save(T object);
}
