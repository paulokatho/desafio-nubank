package spring.boot.desafio.nubank.repository;

import spring.boot.desafio.nubank.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {
    // Spring Data JPA fornecerá automaticamente métodos CRUD aqui (save, findById, findAll, etc.)
}
