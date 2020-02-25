/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mecatronica_2019;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jhow
 */
public class MySQL {

    public static Connection connection;//Cria uma connection
    private Statement st;//Cria um Statment
    //=======================SISTEMA E CONFIGURAÇOES========================

    //<editor-fold defaultstate="collapsed" desc="pegaConexao">
    public synchronized Connection pegaConexao() {//Extende a Conexao
        return createConnection();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="createConnection">
    private Connection createConnection() {//Cria a Conexao apartir dos dados do banco
        try {
            String connStr = "jdbc:mysql://" + Main.Host + ":" + Main.Port + "/" + Main.Banco;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(connStr, Main.Usuario, Main.Senha);
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return null;
    }//</editor-fold>

    //=======================METODOS APARTIR DO MYSQL=======================
    public void start() throws SQLException {
        connection = pegaConexao();
        st = connection.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS gestao_manutencao(tag VARCHAR(120), date VARCHAR(15), type VARCHAR(120), autor_uno VARCHAR(60), autor_group VARCHAR(20), desc_t TEXT(1600))");
        fechaConexao(connection, st);
    }

    /**
     * Fecha apenas a conexao
     *
     * @param conexao Retorna a conexao consultada.
     */
    public static void fechaConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Fecha a conexao e o Statement
     *
     * @param conexao Retorna a conexao consultada
     * @param stmt Retorna o statement consultado
     */
    public static void fechaConexao(Connection conexao, Statement stmt) {
        fechaConexao(conexao);
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Fecha a conexao, o statement e o resultset
     *
     * @param conexao Retorna a conexao consultada
     * @param stmt Retorna o statement consultado
     * @param rs Retorna o resultset consultado
     */
    public static void fechaConexao(Connection conexao, Statement stmt, ResultSet rs) {
        fechaConexao(conexao, stmt);
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
