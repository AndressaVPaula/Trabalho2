package com.example.trabalho2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pessoa {
    private final StringProperty nome;
    private final StringProperty cpf;
    private final StringProperty email;
    private final StringProperty telefone;

    public Pessoa(String nome, String cpf, String email, String telefone) {
        this.nome = new SimpleStringProperty(nome);
        this.cpf = new SimpleStringProperty(cpf);
        this.email = new SimpleStringProperty(email);
        this.telefone = new SimpleStringProperty(telefone);
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getCpf() {
        return cpf.get();
    }

    public StringProperty cpfProperty() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getTelefone() {
        return telefone.get();
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                '}';
    }
}
