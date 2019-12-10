/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.entidades.FichaMedica;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author Petrônio
 */
public class FichaMedicaDAO {
 public void salvar(FichaMedica c) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir FichaMedica
        gerenciador.persist(c);

        //Commit
        gerenciador.getTransaction().commit();

    }

    /**
     * Retorna uma lista com todos as FichaMedica que estejam cadastradss no banco de
     * dados
     *
     * @return
     */
    public List<FichaMedica> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select a from FichaMedica a", FichaMedica.class);

        //Retornar a lista de FichaMedica
        return consulta.getResultList();

    }

    /**
     * Salva as alterações no BD
     */
    public void editar(FichaMedica c) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(c);
        
        //Commit na transação
        gerenciador.getTransaction().commit();

    }
    
    /**
     * Exclui a FichaMedica do BD
     * @param c
     */
    public void excluir(FichaMedica c){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar a FichaMedica do BD com o ator que foi
        //selecionado na tela
        c = gerenciador.merge(c);

        //Mandar sincronizar as alterações 
        gerenciador.remove(c);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
        
    }
    
    public List<FichaMedica> buscarpelonome(String nomec){
         
        
//Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

       
       TypedQuery<FichaMedica> consulta = gerenciador.createNamedQuery
        ("Select a from FichaMedica a where a.nome  like :nome ",
            
            FichaMedica.class);
        //substituindo o parametro :nome pelo valor da variavel nomec
               
            consulta.setParameter("nome",  nomec + "%");
              
            return consulta.getResultList();
    }

}
