package com.example.listadeclientes.controller;

import javax.validation.Valid;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.listadeclientes.modelo.Cliente;
import com.example.listadeclientes.repository.ClienteRepository;

@Controller
@RequestMapping(ClienteController.CLIENTE)
public class ClienteController {

    /**
	 *
	 */
	static final String CLIENTE = "/cliente";
	@Autowired
	private ClienteRepository clienteRepository;



	@GetMapping("/novo")
	public String adicionarCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "/cadastro";
	}

	@PostMapping("/salvar")
	public String salvarCliente(@Valid Cliente cliente, BindingResult result, 
				Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return "/cadastro";
		}
		
		clienteRepository.save(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
		return "redirect:/cliente/novo";
	}
	@GetMapping("/clientes/pesquisar")
	public String pesquisarClientes(@RequestParam("q") String query, Model model) {
   		 List<Cliente> clientes = clienteRepository
        .findByNomeContainingIgnoreCaseOrCpfContainingIgnoreCaseOrEmailContainingIgnoreCase(
            query, query, query
        );
    model.addAttribute("clientes", clientes);
    return "cliente/listar";
}

    @RequestMapping("/listar")
	public String listarCliente(Model model) {
		model.addAttribute("clientes", clienteRepository.findAll());		
		return "/listar";		
	}

	@GetMapping("/pesquisar")
	public String pesquisarCliente(@RequestParam(required = false) String q, Model model) {
		if (q != null && !q.trim().isEmpty()) {
			// Busca simples por nome, CPF ou email
			model.addAttribute("clientes", clienteRepository.findAll().stream()
				.filter(c -> c.getNome().toLowerCase().contains(q.toLowerCase()) ||
							c.getCpf().contains(q) ||
							c.getEmail().toLowerCase().contains(q.toLowerCase()))
				.collect(java.util.stream.Collectors.toList()));
		} else {
			model.addAttribute("clientes", clienteRepository.findAll());
		}
		model.addAttribute("q", q);
		return "/listar";
	}

	@GetMapping("/editar/{id}")
	public String editarCliente(@PathVariable("id") long id, Model model) {
		Optional<Cliente> clienteVelho = clienteRepository.findById(id);
		if (!clienteVelho.isPresent()) {
            throw new IllegalArgumentException("Cliente inválido:" + id);
        } 
		Cliente cliente = clienteVelho.get();
	    model.addAttribute("cliente", cliente);
	    return "/alterar";
	}
	
	@PostMapping("/editar/{id}")
	public String editarCliente(@PathVariable("id") long id, 
			@Valid Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
	    	cliente.setId(id);
	        return "/alterar";
	    }
	    clienteRepository.save(cliente);
	    return "redirect:/cliente/listar";
	}
	
	@PostMapping("/deletar/{id}")
	public String deletarCliente(@PathVariable("id") long id, RedirectAttributes attributes) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent()) {
			attributes.addFlashAttribute("mensagem", "Cliente não encontrado!");
			return "redirect:/cliente/listar";
		}
		clienteRepository.delete(cliente.get());
		attributes.addFlashAttribute("mensagem", "Cliente excluído com sucesso!");
		return "redirect:/cliente/listar";
	}
}






