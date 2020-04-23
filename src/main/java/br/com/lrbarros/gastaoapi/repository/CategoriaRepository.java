package br.com.lrbarros.gastaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lrbarros.gastaoapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
