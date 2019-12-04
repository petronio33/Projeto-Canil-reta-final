/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.FichaMedica;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dados.entidades.Cachorro;
import dados.entidades.FichaMedica;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import servicos.CachorroServico;
import servicos.FichaMedicaServico;

/**
 * FXML Controller class
 *
 * @author rafae
 */
public class FichaMedicaController implements Initializable {

    @FXML
    private JFXComboBox<Cachorro> comboBoxCachorro;
    @FXML
    private JFXTextArea textFieldObservacao;
    @FXML
    private JFXTextField textFieldSexo;
    @FXML
    private JFXTextField textFieldPorte;
    @FXML
    private JFXTextField textFieldRaca;
    @FXML
    private JFXTextArea textAreaAtendimentos;
    @FXML
    private JFXTextField textFieldVeterinario;
    @FXML
    private JFXDatePicker datePickerAtendimento;
    @FXML
    private TableView<FichaMedica> tabela;
    @FXML
    private TableColumn colCachorro;
    @FXML
    private TableColumn colVeterinario;
    @FXML
    private TableColumn colDataAtendimento;
    @FXML
    private TableColumn colAtendimentos;
    @FXML
    private JFXTextField pesquisaNome;
    
     private FichaMedicaServico servico = new FichaMedicaServico();
     private CachorroServico cachorroServico = new CachorroServico();    
    private ObservableList<FichaMedica> dados
            = FXCollections.observableArrayList();
    
    private FichaMedica selecionado;
    @FXML
    private JFXTextField textFieldId;
    private Cachorro Cachorroselecionado;
    @FXML
    private JFXComboBox<Cachorro> comboC;

    /**
     * Initializes the controller class.
     */
    
    public void mensagemDeErro(String MDE) {
        Alert cuidado = new Alert(Alert.AlertType.ERROR);
        cuidado.setTitle("Erro!");
        cuidado.setHeaderText(null);
        cuidado.setContentText(MDE);
        cuidado.showAndWait();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabela();
        listarfichaMedicaTabela();
        listarCachorro();
    }    

    @FXML
    private void salvar(ActionEvent event) {
        
        System.out.println("salvar");
        
        Object o = comboBoxCachorro.getValue();
        Object c1 = comboC.getValue();

        if (textFieldId.getText().isEmpty()) {
            
            Cachorro c = comboBoxCachorro.getValue();
            
            FichaMedica f = new FichaMedica(textFieldVeterinario.getText(),
                    datePickerAtendimento.getValue(),
                    textAreaAtendimentos.getText(),
                    comboBoxCachorro.getValue());
                    

            servico.salvar(f);

            mensagemsucesso("Cachorro salvo com sucesso");

            listarfichaMedicaTabela();

        } else {

            Optional<ButtonType> btn
                    = mensagemDeConfirmacao("Deseja efetuar as alterações?",
                            "Editar");
            if (btn.get() == ButtonType.OK) {
                selecionado.setAtendimentos(textAreaAtendimentos.getText());
                selecionado.setVeterinario(textFieldVeterinario.getText());
                selecionado.setDataAtendimento(datePickerAtendimento.getValue());
                selecionado.setCao(comboBoxCachorro.getValue());
                

                servico.editar(selecionado);

                mensagemsucesso("Cachorro Atualizado!");
                listarfichaMedicaTabela();

            }
        }
        textAreaAtendimentos.setText("");
        textFieldVeterinario.setText("");
        textFieldObservacao.setText("");
        textFieldPorte.setText("");
        textFieldRaca.setText("");
        textFieldSexo.setText("");
        comboBoxCachorro.getSelectionModel().clearSelection();
       
                }

    @FXML
    private void editar(ActionEvent event) {
        selecionado = tabela.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            textAreaAtendimentos.setText(selecionado.getAtendimentos());
            textFieldVeterinario.setText(selecionado.getVeterinario());
            //comboBoxCachorro.setValue(selecionado.getCao());
            
            
            ///listarCachorroTabela();
        } else {
            mensagemDeErro("Selecione um Cachorro!");

        }

    }

    @FXML
    private void excluir(ActionEvent event) {
        selecionado = tabela.getSelectionModel().getSelectedItem();

        if (selecionado != null) {

            Optional<ButtonType> btn = mensagemDeConfirmacao("Deseja excluir a FichaMedica?",
                    "Excluido");
            if (btn.get() == ButtonType.OK) {
                servico.excluir(selecionado);

                mensagemsucesso("FichaMedica apagada!");

                listarfichaMedicaTabela();

            }
            listarfichaMedicaTabela();
        }

    }

    public void mensagemsucesso(String mes) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso!");
        alerta.setHeaderText(null);
        alerta.setContentText(mes);
        alerta.showAndWait();

    }

    private void configurarTabela() {
        colAtendimentos.setCellValueFactory(
                new PropertyValueFactory("atendimentos"));
        colCachorro.setCellValueFactory(
                new PropertyValueFactory("cao"));
        colDataAtendimento.setCellValueFactory(
                new PropertyValueFactory("dataAtendimento"));
        colVeterinario.setCellValueFactory(
                new PropertyValueFactory("veterinario"));
        
        
    }

    private void listarfichaMedicaTabela() {

        dados.clear();

        List<FichaMedica> fichasmedicas = servico.listar();
        dados = FXCollections.observableArrayList(fichasmedicas);

        tabela.setItems(dados);
    }

    private Optional<ButtonType> mensagemDeConfirmacao(String mensagem, String titulo) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        return alert.showAndWait();
    }

    @FXML
    private void pesquisar(ActionEvent event) {
        dados.clear();
        String nome = pesquisaNome.getText();

        List<FichaMedica> fichasmedicas = servico.buscarPeloNome(nome);
        dados = FXCollections.observableArrayList(fichasmedicas);

        tabela.setItems(dados);
    }
    
    
    private void listarCachorro(){
       List<Cachorro> cachorros = cachorroServico.listar();
       comboBoxCachorro.setItems(FXCollections.observableArrayList(cachorros));
       comboC.setItems(FXCollections.observableArrayList(cachorros));
   }
}
