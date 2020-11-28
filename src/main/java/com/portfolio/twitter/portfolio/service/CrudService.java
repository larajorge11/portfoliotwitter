package com.portfolio.twitter.portfolio.service;

import java.util.Set;

public interface CrudService<T, ID> {
    Set<T> findAll();

    T findById(ID id);
}
