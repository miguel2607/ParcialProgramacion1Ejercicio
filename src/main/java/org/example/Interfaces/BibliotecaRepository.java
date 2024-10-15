package org.example.Interfaces;
import org.example.Aplication.domain.Biblioteca;

import java.util.List;

public interface BibliotecaRepository {

    List<Biblioteca> FindAll();
    Biblioteca findById(int id);
    void save(Biblioteca biblioteca);
    void update(Biblioteca biblioteca);
    void delete(int id);

}
