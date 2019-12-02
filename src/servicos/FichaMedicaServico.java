/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.daos.FichaMedicaDAO;
import dados.entidades.FichaMedica;
import java.util.List;

/**
 *
 * @author Petrônio
 */
public class FichaMedicaServico {
    //Atributo para representar a camada de dados
    private FichaMedicaDAO dao = new FichaMedicaDAO();
    
    public void salvar(FichaMedica f){
        //Fazer qualquer regra de negócio
        
        //Mandar o FichaMedica para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(f);
    }
    
    /**
     * Solicita a camada DAO para buscar os FichaMedicas
     * cadastrados
     * @return 
     */
    public List<FichaMedica> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    /**
     * Recebe um cliente e manda para a camada DAO atualizar 
     */
    public void editar(FichaMedica f){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(f);
        
    }
    
    
    public void excluir(FichaMedica f){
        
        
        dao.excluir(f);
    }

    public List<FichaMedica> buscarPeloNome (String nomef){
   
        
        
        return dao.buscarpelonome(nomef);
    
    
    }
}

   
