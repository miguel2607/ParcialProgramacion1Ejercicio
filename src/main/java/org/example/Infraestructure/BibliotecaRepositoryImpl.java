package org.example.Infraestructure;

import org.example.Aplication.domain.Biblioteca;
import org.example.Interfaces.BibliotecaRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaRepositoryImpl implements BibliotecaRepository {
    private static final String FILE_NAME = "Bibliotecas.dat";

    @Override
    public List<Biblioteca> FindAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Biblioteca>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Biblioteca findById(int id) {
        return FindAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Biblioteca biblioteca) {
        List<Biblioteca> bibliotecas = FindAll();
       bibliotecas.add(biblioteca);
        saveAll(bibliotecas);
    }

    @Override
    public void update(Biblioteca biblioteca) {
        List<Biblioteca> bibliotecas = FindAll();
        bibliotecas = bibliotecas.stream()
                .map(p -> p.getId() ==  biblioteca.getId() ? biblioteca : p)
                .collect(Collectors.toList());
        saveAll(bibliotecas);
    }

    @Override
    public void delete(int id) {
        List<Biblioteca> bibliotecas = FindAll();
       bibliotecas = bibliotecas.stream()
                .filter(p -> p.getId() != id)
                .collect(Collectors.toList());
        saveAll(bibliotecas);
    }

    private void saveAll(List<Biblioteca> bibliotecas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(bibliotecas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
