package spring.boot.desafio.nubank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.desafio.nubank.dto.ClientesDTO;
import spring.boot.desafio.nubank.dto.ClientesResponseDTO;
import spring.boot.desafio.nubank.dto.ContatoResponseDTO;
import spring.boot.desafio.nubank.model.Clientes;
import spring.boot.desafio.nubank.model.Contato;
import spring.boot.desafio.nubank.repository.ClientesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public Clientes salvarCliente(ClientesDTO dto) {
        Clientes clientes = new Clientes();
        clientes.setNome(dto.getNome());

        if(dto.getContatos() != null && dto.getContatos().size() > 0) {
            List<Contato> contatos = dto.getContatos().stream().map(
                c -> {
                    Contato contato = new Contato();
                    contato.setTelefone(c.getTelefone());
                    contato.setEmail(c.getEmail());
                    contato.setClientes(clientes);

                    return contato;
                }).collect(Collectors.toList());
            clientes.setContatos(contatos);
        }
        return clientesRepository.save(clientes);
    }

    public List<ClientesResponseDTO> listarTodos() {
        return clientesRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ContatoResponseDTO> listarContatosPorCliente(Long clienteId) {
        Clientes cliente = clientesRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente "));
        return cliente.getContatos().stream().map(c -> {
            ContatoResponseDTO dto = new ContatoResponseDTO();
            dto.setId(c.getId());
            dto.setTelefone(c.getTelefone());
            dto.setEmail(c.getEmail());
            return dto;
        }).collect(Collectors.toList());
    }

    private ClientesResponseDTO toDTO(Clientes cliente) {
        ClientesResponseDTO dto = new ClientesResponseDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());

        List<ContatoResponseDTO> contatos = cliente.getContatos().stream().map(c -> {
            ContatoResponseDTO contatoDto = new ContatoResponseDTO();
            contatoDto.setId(c.getId());
            contatoDto.setTelefone(c.getTelefone());
            contatoDto.setEmail(c.getEmail());
            return contatoDto;
        }).collect(Collectors.toList());
        dto.setContatos(contatos);

        return dto;
    }
}
