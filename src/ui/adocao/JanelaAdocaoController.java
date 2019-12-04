/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.adocao;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dados.entidades.Cachorro;
import dados.entidades.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import servicos.CachorroServico;
import servicos.ClienteServico;

/**
 * FXML Controller class
 *
 * @author IFNMG
 */
public class JanelaAdocaoController implements Initializable {

    @FXML
    private JFXComboBox<Cliente> ComboBoxDono;
    @FXML
    private JFXComboBox<Cachorro> ComboBoxCachorro;
    @FXML
    private JFXButton BotaoSalvar;
    @FXML
    private JFXButton botaoeditar;
    @FXML
    private JFXButton botaosalvar;

    private CachorroServico servico = new CachorroServico();
    private ClienteServico clienteSetivoc = new ClienteServico();
    
    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }    
 
    private void configurarTabela() {
       

    }
}