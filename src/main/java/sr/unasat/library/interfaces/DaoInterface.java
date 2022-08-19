package sr.unasat.library.interfaces;

import java.util.List;

public interface DaoInterface<T> {
    List<?> retrieveList();

    T insert(T t);

    void delete(T t);

    void update(T t);
}
