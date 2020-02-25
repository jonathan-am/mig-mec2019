/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mecatronica_2019;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jhow
 */
public class Cache {

    private String data;//String data setada vazia
    private final ArrayList<Ficha> list = new ArrayList<>();//Lista com retorna ao objeto Ficha

    public Cache init() {
        PreparedStatement st = null;
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = new MySQL().pegaConexao();
            //SELECIONE TUDO DE gestao_manutencao(TABELA NO DB) ONDE date = 'data'(CHAVE DE BUSCA)
            st = connection.prepareStatement("SELECT * FROM gestao_manutencao");
            rs = st.executeQuery();
            while (rs.next()) {//ENQUANTO RESULTADO DA QUERY ACHAR RESPOSTA...
                //DEFINE UM OBJETO FICHA
                Ficha f = new Ficha().Ficha(rs.getString("tag"), rs.getString("date"), rs.getString("type"), rs.getString("autor_uno"), rs.getString("autor_group"), rs.getString("desc_t"));
                list.add(f);//ADICIONA FICHA A LISTA ESTRUTURADA
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            MySQL.fechaConexao(connection, st, rs);
        }
        return this;
    }

    /**
     *
     * Metodo de declaração a classe, classe estruturada em Cache para nao
     * sobrecarregar o banco de dados
     *
     * @param data USADO COMO CHAVE BUSCA NO BANCO DE DADOS
     * @return classe instanciada
     */
    public Cache initByData(String data) {
        this.data = data;
        PreparedStatement st = null;
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = new MySQL().pegaConexao();
            //SELECIONE TUDO DE gestao_manutencao(TABELA NO DB) ONDE date = 'data'(CHAVE DE BUSCA)
            st = connection.prepareStatement("SELECT * FROM gestao_manutencao WHERE date = '" + data + "'");
            rs = st.executeQuery();
            while (rs.next()) {//ENQUANTO RESULTADO DA QUERY ACHAR RESPOSTA...
                //DEFINE UM OBJETO FICHA
                Ficha f = new Ficha().Ficha(rs.getString("tag"), data, rs.getString("type"), rs.getString("autor_uno"), rs.getString("autor_group"), rs.getString("desc_t"));
                list.add(f);//ADICIONA FICHA A LISTA ESTRUTURADA
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            MySQL.fechaConexao(connection, st, rs);
        }
        return this;
    }

    /**
     *
     * metodo que retorna a constant list
     *
     * @return list, caso iniciada retorna a list, caso nao aberta retorna null
     */
    public ArrayList<Ficha> getFichas() {
        return list;
    }

    /**
     *
     * Metodo de checagem se o banco pesquisado na data em questao esta vazio ou
     * nao
     *
     * @return true se a list fichas tiver um tamanho maior que 0
     */
    public boolean has() {
        return getFichas().size() > 0;
    }
}
