package spring.boot.desafio.nubank.repository;

import spring.boot.desafio.nubank.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
