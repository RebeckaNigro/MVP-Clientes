package com.example.listadeclientes.modelo;

import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, message = "O nome deve ter no mínimo 3 carateres")
	private String nome;
	
	@NotNull
	@Size(min = 11, message = "O cpf deve ter 11 carateres")
	private String cpf;

	@NotNull(message = "A data de nascimento deve ser informada")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		private LocalDate dataNascimento;
		
	@NotEmpty(message = "O email deve ser informada")
	@Size(min = 3, message = "email deve ter no mínimo 3 caracteres")
	private String email;

	@Column(nullable = false)
    private Integer idade;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public LocalDate getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdade() { return idade; }
	public void setIdade(Integer idade) { this.idade = idade; }
	
	public void calcularIdade() {
        if (this.dataNascimento != null) {
            this.idade = Period.between(this.dataNascimento, LocalDate.now()).getYears();
        }
    }

	@PrePersist
	@PreUpdate
	private void atualizarIdadeAntesDeSalvar() {
		calcularIdade();
	}
}

    