package bc.dao;

import java.util.ArrayList;

public interface DAO<T> {
    boolean insert(T t);
    boolean update(T t);
    boolean delete(T t);
    ArrayList<T> selectAll();
}
