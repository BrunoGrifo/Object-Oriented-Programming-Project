/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.pkg1.pkg0;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author ASUS
 */
public class Aluno extends Pessoa implements Serializable{

    private int numero_aluno;

    /**
     *
     */
    protected HashMap<Curso, Integer> cursos;
    private String regime;

    Aluno(String nome, String email, int numero_aluno, String regime) {
        super(nome, email);
        this.numero_aluno = numero_aluno;
        cursos = new HashMap<>();
        this.regime = regime;
    }

    /**
     *
     * @return
     */
    public int getNumeroAluno() {
        return numero_aluno;
    }

    /**
     *
     * @return
     */
    public HashMap<Curso, Integer> getCursos() {
        return cursos;
    }

    /**
     *
     * @return
     */
    public String getRegime() {
        return regime;
    }

    /**
     *
     * @param curso
     * @param matricula
     */
    public void adicionaCurso(Curso curso, int matricula){
        cursos.put(curso,matricula);
    }

    /**
     *
     * @param curso
     */
    public void removeCurso(Curso curso){
        cursos.remove(curso);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + " |Numero aluno:" + numero_aluno + " |Regime:" + regime;
    }

}
