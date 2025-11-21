/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.HeadlessException;
import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;
import jdbc.ModuloConexao;
import model.Usuario;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;



/**
 *
 * @author clebe
 */
public class ClienteDAO {
    
    Connection conexao;
    
    public ClienteDAO(){
        this.conexao =  ModuloConexao.conectar();
    }
    
      public List<Cliente> listarCliente() {
        try {

            //1 passo criar a lista
            List<Cliente> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tbclientes";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente obj = new Cliente();

                obj.setId(rs.getInt("idcli"));
                obj.setNome(rs.getString("nomecli"));
                obj.setEndereco(rs.getString("endcli"));
                obj.setFone(rs.getString("fonecli"));
                obj.setEmail(rs.getString("emailcli"));
                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
      
    /**
     * Método responsável pela pesquisa de clientes pelo nome com filtro
     */
    public List<Cliente> listarClienteNome(String nome) {
        try {

            //1 passo criar a lista
            List<Cliente> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select idcli as id, nomecli as nome, endcli as endereço, fonecli as fone, emailcli as email from tbclientes where nomecli like ?";
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente obj = new Cliente();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setEndereco(rs.getString("endereço"));
                obj.setFone(rs.getString("fone"));
                obj.setEmail(rs.getString("email"));
                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }   
      
      public Cliente buscarCliente(int IdCli) {
        try {
            String sql = "select * from tbclientes WHERE idcli = ;";
            
           Conexao con = new Conexao();
            con = (Conexao) ModuloConexao.conectar();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, IdCli);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente clientes = new Cliente();
                clientes.setId(rs.getInt("idcli"));
                clientes.setNome(rs.getString("cliente"));
                clientes.setFone(rs.getString("fone"));
                clientes.setEmail(rs.getString("email"));
                clientes.setEndereco(rs.getString("endereco"));

                return clientes;
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!!!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;

    }

  
      public void adicionarCliente(Cliente obj) {
        
        try{
        String sql = "insert into tbclientes(idcli,nomecli,fonecli,enderecocli,emailcli) values(?,?,?,?,?)";
            conexao = ModuloConexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getFone());
            stmt.setString(4, obj.getEndereco());
            stmt.setString(5, obj.getEmail());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!!!");
        } catch (SQLIntegrityConstraintViolationException el){
            JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login.");
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try{
                conexao.close();
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    public void alterarCliente(Cliente obj, int idcli) {
        
        try{
        String sql = "update tbclientes set nomecli = ?, fonecli = ?, endcli = ?, emailcli = ? where idcli = ?;";
            conexao = ModuloConexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
           
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getFone());
            stmt.setString(3, obj.getEndereco());
            stmt.setString(4, obj.getEmail());
            stmt.setInt(5, idcli);
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!!!");
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try{
                conexao.close();
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
   
    public void deletarCliente(int idcli) {
        
        try{
        String sql = "delete from tbcliente where idcli = ?";
            conexao = ModuloConexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
           
            stmt.setInt(1, idcli);
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!!!");
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try{
                conexao.close();
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
}

   
    private static class Conexao {

        public Conexao() {
        }

        private PreparedStatement prepareStatement(String sql) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
   


   
    


    
