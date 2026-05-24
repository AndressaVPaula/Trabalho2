package com.example.trabalho2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

        // Adiciona máscaras de entrada
        adicionarMascaraCpf(txtCpf);
        adicionarMascaraTelefone(txtTelefone);
    }

    private void adicionarMascaraCpf(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            String text = newValue.replaceAll("[^\\d]", "");
            if (text.length() > 11) text = text.substring(0, 11);
            
            StringBuilder formatted = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                if (i == 3 || i == 6) formatted.append(".");
                if (i == 9) formatted.append("-");
                formatted.append(text.charAt(i));
            }
            
            if (!formatted.toString().equals(newValue)) {
                textField.setText(formatted.toString());
                textField.positionCaret(formatted.length());
            }
        });
    }

    private void adicionarMascaraTelefone(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            String text = newValue.replaceAll("[^\\d]", "");
            if (text.length() > 11) text = text.substring(0, 11);
            
            StringBuilder formatted = new StringBuilder();
            if (text.length() > 0) {
                formatted.append("(");
                for (int i = 0; i < text.length(); i++) {
                    if (i == 2) formatted.append(") ");
                    if (i == 7 && text.length() > 10) formatted.append("-"); // Celular (11 dígitos)
                    else if (i == 6 && text.length() <= 10) formatted.append("-"); // Fixo (10 dígitos)
                    formatted.append(text.charAt(i));
                }
            }
            
            if (!formatted.toString().equals(newValue)) {
                textField.setText(formatted.toString());
                textField.positionCaret(formatted.length());
            }
        });
    }

    private boolean isEmailValido(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }

    private void showAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    protected void onSalvarClick() {
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();

        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            showAlert("Erro de Validação", "Campos Vazios", "Por favor, preencha todos os campos.", Alert.AlertType.WARNING);
            return;
        }

        if (!isEmailValido(email)) {
            showAlert("Erro de Validação", "E-mail Inválido", "O formato do e-mail informado não é válido.", Alert.AlertType.WARNING);
            return;
        }

        if (cpf.replaceAll("[^\\d]", "").length() < 11) {
            showAlert("Erro de Validação", "CPF Incompleto", "O CPF deve conter 11 dígitos.", Alert.AlertType.WARNING);
            return;
        }

        String phoneDigits = telefone.replaceAll("[^\\d]", "");
        if (phoneDigits.length() < 10) {
            showAlert("Erro de Validação", "Telefone Incompleto", "O telefone deve conter o DDD e pelo menos 8 dígitos.", Alert.AlertType.WARNING);
            return;
        }

        Pessoa novaPessoa = new Pessoa(nome, cpf, email, telefone);
        listaPessoas.add(novaPessoa);

        showAlert("Sucesso", "Cadastro Realizado", "Pessoa salva com sucesso!", Alert.AlertType.INFORMATION);
        onCancelarClick(); // Limpa os campos após salvar
    }

    @FXML
    protected void onCancelarClick() {
        txtNome.clear();
        txtCpf.clear();
        txtEmail.clear();
        txtTelefone.clear();
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
