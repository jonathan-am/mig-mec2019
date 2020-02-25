/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mecatronica_2019;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jhow
 */
public class TableColumModel extends AbstractTableModel{

    public final ArrayList<Ficha> dados = new ArrayList<>();//LISTA DE OBJETOS FICHA
    private final String[] colunas = {"DATA","ETIQUETA"};//STRING ARRAY COM 2 ITEMS
    public static Ficha ficha;//OBJETO INSTANCIAVEL
    
    // <editor-fold defaultstate="collapsed" desc="CODIGO GERADO PELA AbstractTableModel">
    @Override
    public String getColumnName(int column) {
        return colunas[column]; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        
        switch(coluna){
            case 0:
                return dados.get(linha).getData();
            case 1:
                return dados.get(linha).getTag();
        }
        
        return null;
        
    }//</editor-fold>
    
    /**
     * 
     * Metodo usado para adicionar um novo item na tabela
     * 
     * @param p objeto Ficha, 
     */
    public void addRow(Ficha p){
        this.dados.add(p);
        this.fireTableDataChanged();
    }
    
    /**
     * 
     * Remove um item da tabela a partir do numero da linha
     * 
     * @param linha variavel que retorna a linha chamada
     */
    public void removeRow(int linha){
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
    
}
