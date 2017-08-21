/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.pkg1.pkg0;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Curso implements Serializable{

    private String nome_curso;
    private int duracao_anos;
    private String grau_curso;
    private ArrayList<Disciplina> lista_disciplinas;

    Curso(String nome_curso, int duracao_anos, String grau_curso, ArrayList<Disciplina> lista_disciplinas) {
        this.nome_curso = nome_curso;
        this.duracao_anos = duracao_anos;
        this.grau_curso = grau_curso;
        this.lista_disciplinas = lista_disciplinas;
    }

    public String getCurso() {
        return nome_curso;
    }

    public int getDuracaoCurso() {
        return duracao_anos;
    }

    public ArrayList<Disciplina> getDisciplinasCurso() {
        return lista_disciplinas;
    }
    
    public void adicionaDisciplinas(Disciplina disciplina){
        lista_disciplinas.add(disciplina);
    }
    
    public void removeDisciplinas(Disciplina disciplina){
        lista_disciplinas.remove(disciplina);
    }
    
    public String getStringDisciplinasCurso() {
        String string = "";
        for (Disciplina x : lista_disciplinas) {

            string += ((Disciplina) x).toString() + "\n";
        }
        if (string.equals("")) {
            return "Nao existem disciplinas no curso!";
        } else {
            return string;
        }
    }

    @Override
    public String toString() {
        return "Nome curso:" + nome_curso + " |Duracao anos: " + duracao_anos + " |Grau de curso:" + grau_curso;
    }
}
