package com.example.listadeclientes.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.listadeclientes.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {

    List<Cliente> findByNomeContainingIgnoreCaseOrCpfContainingIgnoreCaseOrEmailContainingIgnoreCase(
        String nome, String cpf, String email
    );
}