package org.example.Service;

import org.example.Aplication.domain.Biblioteca;
import org.example.Interfaces.BibliotecaRepository;

import java.util.List;

public class BibliotecaServiceImpl implements BibliotecaService {

private final BibliotecaRepository bibliotecaRepository;

public BibliotecaServiceImpl (BibliotecaRepository bibliotecaRepository){
    this.bibliotecaRepository = bibliotecaRepository;
}


    @Override
    public List<Biblioteca> FindAll() {
        return bibliotecaRepository.FindAll();
    }

    @Override
    public Biblioteca findById(int id) {
        return bibliotecaRepository.findById(id);
    }

    @Override
    public void save(Biblioteca biblioteca) {
validarBiblioteca(biblioteca);
bibliotecaRepository.save(biblioteca);
    }

    @Override
    public void update(Biblioteca biblioteca) {
validarBiblioteca(biblioteca);
bibliotecaRepository.update(biblioteca);

    }

    @Override
    public void delete(int id) {

    }

    private void validarBiblioteca(Biblioteca biblioteca) {
        if (biblioteca.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la biblioteca no puede estar vacio");
        }
        if (biblioteca.getDireccion().equals("ninguna parte")) {
            throw new IllegalArgumentException("debe tener una direccion");
        }
    }


}
