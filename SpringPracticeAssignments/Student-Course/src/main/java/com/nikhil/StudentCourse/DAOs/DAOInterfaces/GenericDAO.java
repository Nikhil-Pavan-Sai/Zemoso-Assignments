package com.nikhil.StudentCourse.DAOs.DAOInterfaces;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericDAO<T, K extends Serializable>
{

    List<T> list();

    Optional<T> find(K key);

    boolean remove(K key);

    boolean remove(T entry);

    T save(T entry);

}