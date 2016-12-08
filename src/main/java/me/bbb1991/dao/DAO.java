package me.bbb1991.dao;

import java.util.List;

/**
 * Created by bbb1991 on 11/28/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public interface DAO<T> {
    T findById(Integer id);

    List<T> findAll();

    void save(T model);

    void update(T model);

    void delete(Integer id);

}
