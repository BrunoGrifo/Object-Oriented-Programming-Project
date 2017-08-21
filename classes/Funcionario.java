/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.pkg1.pkg0;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
//abstract 
abstract class Funcionario extends Pessoa implements Serializable{

    protected int numero_mecanografico;
    protected String categoria;

    Funcionario(String nome, String email, int numero_mecanografico, String categoria) {
        super(nome, email);
        this.numero_mecanografico = numero_mecanografico;
        this.categoria = categoria;
    }

    public int getNumeroFuncionario() {
        return numero_mecanografico;
    }

    @Override
    public String toString() {
        return super.toString() + " |Numero Mecanografico:" + numero_mecanografico + " |Categoria:" + categoria;
    }
}
