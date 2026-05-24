package com.example.trabalho2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TableView<Pessoa> tblPessoas;

    @FXML
    private TableColumn<Pessoa, String> colNome;

    @FXML
    private TableColumn<Pessoa, String> colCpf;

    @FXML
    private TableColumn<Pessoa, String> colEmail;

    @FXML
    private TableColumn<Pessoa, String> colTelefone;

    private final ObservableList<Pessoa> listaPessoas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configura as colunas da tabela
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        // Vincula a lista observável à tabela
        tblPessoas.setItems(listaPessoas);
    }

    @FXML
    protected void onSalvarClick() {
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();

        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }

        Pessoa novaPessoa = new Pessoa(nome, cpf, email, telefone);
        listaPessoas.add(novaPessoa);

        System.out.println("Pessoa salva: " + novaPessoa);
        onCancelarClick(); // Limpa os campos após salvar
    }

    @FXML
    protected void onCancelarClick() {
        txtNome.clear();
        txtCpf.clear();
        txtEmail.clear();
        txtTelefone.clear();
        System.out.println("Campos limpos.");
    }

    @FXML
    protected void onListarClick() {
        System.out.println("--- Listagem de Pessoas ---");
        if (listaPessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
        } else {
            for (Pessoa p : listaPessoas) {
                System.out.println(p);
            }
        }
    }
}
