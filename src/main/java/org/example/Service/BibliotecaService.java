package org.example.Service;

import org.example.Aplication.domain.Biblioteca;

import java.util.List;

public interface BibliotecaService {

    List<Biblioteca> FindAll();
    Biblioteca findById(int id);
    void save(Biblioteca biblioteca);
    void update(Biblioteca biblioteca);
    void delete(int id);

}
