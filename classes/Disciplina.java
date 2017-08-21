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
public class Disciplina implements Serializable {

    private String nome_disciplina;
    private Docente docente_responsavel;
    private ArrayList<Docente> outros_docentes;
    private ArrayList<Aluno> alunos_inscritos;

    Disciplina(String nome_disciplina, Docente docente_responsavel) {
        this.nome_disciplina = nome_disciplina;
        this.docente_responsavel = docente_responsavel;
        outros_docentes = new ArrayList<>();
        alunos_inscritos = new ArrayList<>();
    }
    
    public void adicionaDocentes(Docente docente){
        outros_docentes.add(docente);
    }
    
    public void removeDocentes(Docente docente){
        outros_docentes.remove(docente);
    }
    
    public void adicionaAlunos(Aluno aluno){
        alunos_inscritos.add(aluno);
    }
    
    public void removeAlunos(Aluno aluno){
        alunos_inscritos.remove(aluno);
    }

    public String getDisciplina() {
        return nome_disciplina;
    }

    public Docente getResponsavelTotal() {
        return docente_responsavel;
    }

    public ArrayList<Docente> getArrayDocentes() {
        return outros_docentes;
    }

    public ArrayList<Aluno> getArrayAlunos() {
        return alunos_inscritos;
    }
    public void setDocenteResponsavel(Docente docente_responsavel){ //vaiser preciso quando se elimina um docente
     this.docente_responsavel=docente_responsavel;
     }

    public String getDocentesDisciplina() {
        String string = "";
        for (Docente x : outros_docentes) {

            string += ((Docente) x).toString() + "\n";
        }
        if (string.equals("")) {
            return "Nao existem mais docentes na disciplina";
        } else {
            return string;
        }
    }

    public String getAlunosDisciplina() {
        String string = "";
        for (Aluno x : alunos_inscritos) {

            string += ((Aluno) x).toString() + "\n";
        }
        if (string.equals("")) {
            return "Nao existem alunos na disciplina";
        } else {
            return string;
        }
    }

    @Override
    public String toString() {
        return "Nome disciplina:" + nome_disciplina + "\nDocente responsavel:" + docente_responsavel.toString();
    }
}
