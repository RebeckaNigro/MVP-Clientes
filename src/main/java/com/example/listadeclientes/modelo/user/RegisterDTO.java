package com.example.listadeclientes.modelo.user;

public record RegisterDTO(String login, String password, UserRole role) {
}