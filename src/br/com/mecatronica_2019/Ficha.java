/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mecatronica_2019;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jhow
 */
public final class Ficha {

    //
    private String tag; //VARIAVEL STRING RETORNA TAG
    private String date; //VARIAVEL STRING RETORNA DATA DA MANUTENÇAO
    private String type; //VARIAVEL STRING RETORNA TIPO DE EQUIPAMENTO
    private String autor_uno; //VARIAVEL STRING RETORNA RESPONSAVEL PRINCIPAL
    private String autor_group; //VARIAVEL STRING RETORNA GRUPO RESPONSAVEL
    private String desc_t; //VARIAVEL STRING RETORNA TEXT, DESCRIÇÃO DO EQUIPAMENTO
    //

    /**
     * Metodo principal que chama a classe Ficha
     *
     * @param tag Determina a variavel tag, not null
     * @param date Determina a variavel date, not null
     * @param type Determina a variavel type, not null
     * @param autor_uno Determina a variavel autor_uno, not null
     * @param autor_group Determina a variavel autor_group, not null
     * @param desc_t Determina a variavel desc_t, not null
     * @return this
     */
    public Ficha Ficha(String tag, String date, String type, String autor_uno, String autor_group, String desc_t) {
        this.tag = tag;
        this.date = date;
        this.type = type;
        this.autor_uno = autor_uno;
        this.autor_group = autor_group;
        this.desc_t = desc_t;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public String getData() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getAutor_uno() {
        return autor_uno;
    }

    public String getAutor_Group() {
        return autor_group;
    }

    public String getDesc_t() {
        return desc_t;
    }

    /**
     *
     * Metodo, responsavel por inserir os dados no banco de dados
     *
     * @return class Ficha
     */
    public Ficha insert() {
        Statement st = null;//DEFINE UMA DECLARAÇÃO
        Connection connection = null;//DEFINE UMA CONEXÃO
        try {
            connection = new MySQL().pegaConexao();//CRIA UMA CONEXAO AO DB
            st = connection.createStatement();//CRIA UMA DECLARAÇÃO PARA SE COMUNICAR COM O DB
            //tag , date , type , autor_uno , autor_group , desc_t
            st.executeUpdate("INSERT INTO gestao_manutencao VALUES(" //INSIRA EM gestao_manutencao(TABELA NO DB) OS VALORES
                    + "'" + tag + "'," //STRING TAG
                    + "'" + date + "'," //STRING DATA
                    + "'" + type + "'," //STRING TIPO
                    + "'" + autor_uno + "'," //STRING AUTOR RESPONSAVEL
                    + "'" + autor_group + "'," //STRING GRUPO RESPONSAVEL
                    + "'" + desc_t + "')"); //STRING TEXT DESCRIÇAO DO EQUIPAMENTO
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            MySQL.fechaConexao(connection, st);//FECHA A CONEXAO E A DECLARAÇÃO ABERTA
        }
        return this;
    }

}
