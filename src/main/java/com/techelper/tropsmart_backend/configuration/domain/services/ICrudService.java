package com.techelper.tropsmart_backend.configuration.domain.services;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T> {
    public T save(T t) throws Exception;
    public void deleteById(Integer id) throws Exception;
    public Optional<T> findById(Integer id) throws  Exception;
    public List<T> findAll() throws Exception;
}
