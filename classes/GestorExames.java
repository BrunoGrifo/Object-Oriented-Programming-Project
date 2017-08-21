/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.pkg1.pkg0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.System.out;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class GestorExames implements Serializable{

    private static final Scanner inputI = new Scanner(System.in);
    private static final Scanner inputS = new Scanner(System.in); //testar pro ser final
    private ArrayList<Exame> lista_exames;
    private ArrayList<Pessoa> lista_pessoas;
    private ArrayList<Curso> lista_cursos;
    private ArrayList<Disciplina> lista_disciplinas;
    private ArrayList<Sala> lista_salas;

    GestorExames() {
        lista_exames = new ArrayList<>();
        lista_disciplinas = new ArrayList<>();
        lista_pessoas = new ArrayList<>();
        lista_cursos = new ArrayList<>();
        lista_salas = new ArrayList<>();
    }

    /**
     *
     */
    public void criaSalas() {
        String nome_sala = "";
        int numero_maximo;
        do {
            out.println("Nome da sala:");
            do {
                nome_sala = inputS.nextLine();
            } while (!testaNomeSala(nome_sala));
        } while (!verificaStringComNumeros(nome_sala));
        out.print("Numero maximo de lugar:\n0-Sair");
        numero_maximo = testaOpcao(0, 200);
        if (numero_maximo != 0) {
            Sala sala = new Sala(nome_sala, numero_maximo);
            lista_salas.add(sala);
        }

    }

    /**
     *
     */
    public void criaPessoa() {
        int estatuto = 0, numero_mecanografico = -1, opcao = 0, confirma = 0, numero_aluno_add;
        String numero_aluno = "", numero_mecanografico_test = "", nome, email, regime = "", categoria, area_investigacao, cargo;
        do {
            out.println("1-Aluno");
            out.println("2-Funcionario");
            out.print("0-Sair");
            opcao = testaOpcao(0, 2);
            if (opcao > 0) {
                do {
                    out.print("Nome:");
                    nome = inputS.nextLine();
                } while (!verificaString(nome));
                do {
                    out.print("Email:");
                    email = inputS.nextLine();
                } while (!verificaStringComNumeros(email));
                switch (opcao) {
                    case 1:
                        do {
                            do {
                                out.print("Numero de aluno de 9 digitos:");
                                try {
                                    numero_aluno = inputS.nextLine();
                                } catch (InputMismatchException e) {
                                    out.println("Opção invalida");
                                    inputI.next();
                                }
                            } while (!verificaNumeros(numero_aluno) || numero_aluno.length() != 9);
                            numero_aluno_add = Integer.parseInt(numero_aluno);
                        } while (!verificaNumeroAluno(numero_aluno_add));
                        out.println("Regime: ");
                        out.println("1-Normal");
                        out.println("2-Trabalhador Estudante");
                        out.println("3-Atleta");
                        out.println("4-Dirigente Associativo");
                        out.println("5-Eramus");
                        out.print("0-Cancelar");
                        estatuto = testaOpcao(0, 5);
                        switch (estatuto) {
                            case 0:
                                continue;
                            case 1:
                                regime = "normal";
                                break;
                            case 2:
                                regime = "trabalhador-estudante";
                                break;
                            case 3:
                                regime = "atleta";
                                break;
                            case 4:
                                regime = "dirigente associativo";
                                break;
                            case 5:
                                regime = "erasmus";
                                break;
                            default:
                                out.println("Opção invalida");
                        }

                        if (estatuto != 0) {
                            Aluno novo_aluno = new Aluno(nome, email, numero_aluno_add, regime);
                            lista_pessoas.add(novo_aluno);
                        }
                        break;
                    case 2:
                        out.println("1-Docente");
                        out.println("2-Nao Docente");
                        out.print("0-Sair");
                        opcao = testaOpcao(0, 2);
                        if (opcao != 0) {
                            do {
                                do {
                                    out.print("Numero mecanografico(5 digitos):");
                                    try {
                                        numero_mecanografico_test = inputS.nextLine();
                                    } catch (InputMismatchException e) {
                                        out.println("Opção invalida");
                                        inputI.next();
                                    }
                                } while (!verificaNumeros(numero_mecanografico_test) || numero_mecanografico_test.length() != 5);
                                numero_mecanografico = Integer.parseInt(numero_mecanografico_test);
                            } while (!verificaNumeroFuncionario(numero_mecanografico));
                            do {
                                out.print("Categoria:");
                                categoria = inputS.nextLine();
                            } while (!verificaString(categoria));
                            switch (opcao) {
                                case 1:
                                    do {
                                        out.print("Area de investigacao:");
                                        area_investigacao = inputS.nextLine();
                                    } while (!verificaString(area_investigacao));
                                    out.println("1-Confirmar");
                                    out.print("0-Cancelar");
                                    confirma = testaOpcao(0, 1);
                                    if (confirma == 1) {
                                        Docente novo_docente = new Docente(nome, email, numero_mecanografico, categoria, area_investigacao);
                                        lista_pessoas.add(novo_docente);
                                    }
                                    break;
                                case 2:
                                    do {
                                        out.print("Cargo:");
                                        cargo = inputS.nextLine();
                                    } while (!verificaString(cargo));
                                    out.println("1-Confirmar");
                                    out.print("0-Cancelar");
                                    confirma = testaOpcao(0, 1);
                                    if (confirma == 1) {
                                        NaoDocentes novo_funcionario = new NaoDocentes(nome, email, cargo, numero_mecanografico, categoria);
                                        lista_pessoas.add(novo_funcionario);
                                    }
                                    break;
                                default:
                                    out.println("Opcao invalida!");
                            }
                        }
                        break;
                    default:
                        out.println("Opcao invalida!");
                }
            }
        } while (opcao != 0);
    }

    /**
     *
     */
    public void criaDisciplina() {
        String nome_disciplina;
        do {
            out.print("Nome disciplina:");
            do {
                nome_disciplina = inputS.nextLine();
            } while (!testaNomeDisciplina(nome_disciplina));
        } while (!verificaString(nome_disciplina));
        int i;
        int opcao = 0;
        out.println("Nome docente responsavel:");
        for (int j = 0; j < 2; j++) {
            i = 0;
            for (Pessoa x : lista_pessoas) {
                if (x instanceof Docente) {
                    if (j == 0) {
                        out.println(++i + "-" + ((Docente) x).toString());
                    } else {
                        ++i;
                        if (opcao == i) {

                            Disciplina nova_disciplina = new Disciplina(nome_disciplina, (Docente) x);
                            addDiscipla(nova_disciplina);
                        }
                    }
                }
            }
            if (i != 0) {
                if (j == 0) {
                    out.print("0-Sair");
                    opcao = testaOpcao(0, i);
                }
            } else {
                out.println("Nao existem Docentes para serem postos como Docente responsavel, disciplina nao adicionada.");
                j++;
            }
            if (opcao == 0) {
                j++;
            }
        }
    }

    /**
     *
     */
    public void criarCurso() {
        String nome_curso, grau = "";
        int anos, i = 0, opcao = 1;
        ArrayList<Disciplina> confirma = new ArrayList<>();
        do {
            out.print("Nome do Curso:");
            do {
                nome_curso = inputS.nextLine();
            } while (!testaNomeCurso(nome_curso));
        } while (!verificaString(nome_curso));
        out.print("Duração anos: \n"
                + "0-Sair");
        anos = testaOpcao(0, 5);
        if (anos != 0) {
            out.print("Grau curso:\n"
                    + "1-Licenciatura\n"
                    + "2-Mestrado\n"
                    + "3-Doutoramento\n"
                    + "0-Sair");
            opcao = testaOpcao(0, 3);
            switch (opcao) {
                case 1:
                    grau = "Licenciatura";
                    break;
                case 2:
                    grau = "Mestrado";
                    break;
                case 3:
                    grau = "Doutoramento";
                    break;
                case 0:
                    break;
                default:
                    out.println("opcao invalida");
            }
            if (opcao != 0) {
                while (opcao != 0) {
                    for (int j = 0; j < 2; j++) {
                        i = 0;
                        for (Disciplina x : lista_disciplinas) {
                            if (!confirma.contains(x)) {
                                if (j == 0) {
                                    out.println(++i + "-" + ((Disciplina) x).toString());
                                } else {
                                    i++;
                                    if (i == opcao) {
                                        confirma.add(x);
                                    }
                                }
                            }
                        }
                        if (i != 0) {
                            if (j == 0) {
                                out.print("0-sair");
                                opcao = testaOpcao(0, i);
                            }
                        } else {
                            opcao = 0;
                            j++;
                        }
                    }
                }
                Curso curso = new Curso(nome_curso, anos, grau, confirma);
                addCurso(curso);
            }
        }
    }

    /**
     *
     */
    public void adicionaExames() {
        int opcao = 0, duracao = 0, existe = 0;
        Sala sala = null;
        Disciplina disciplina;
        Docente docente_responsavel;
        SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        Date data_inicial = new Date(0, 0, 0, 0, 0, 0);
        Date data_final = new Date(0, 0, 0, 0, 0, 0);
        ArrayList<Docente> vigilantes = new ArrayList<>();
        ArrayList<NaoDocentes> ajudantes = new ArrayList<>();
        HashMap<Aluno, Integer> alunos = new HashMap<>();
        if (!lista_disciplinas.isEmpty()) {
            if (!lista_salas.isEmpty()) {
                imprimeContadorDisciplinas();
                out.print("0-Sair");
                opcao = testaOpcao(0, lista_disciplinas.size());
                if (opcao != 0) {
                    disciplina = (Disciplina) lista_disciplinas.get(opcao - 1);
                    if (testaExames(disciplina) != 3) {
                        int tipo_exame = 0;
                        opcao = 1;
                        docente_responsavel = disciplina.getResponsavelTotal();
                        while (opcao != 0) {
                            out.print("Tipo de Exame:\n1-Normal\n2-Recurso\n3-Especial\n0-sair");
                            tipo_exame = testaOpcao(0, 3);
                            if (tipo_exame > 0) {
                                existe = 0;
                                for (Exame x : lista_exames) {
                                    if (disciplina.equals(x.getDisciplina()) && ((x instanceof Normal && tipo_exame == 1) || (x instanceof Recurso && tipo_exame == 2) || (x instanceof Especial && tipo_exame == 3))) {
                                        out.println("Exame ja existente:");
                                        imprimeExames(x);
                                        out.print("1-Mudar tipo de exame\n0-Sair");
                                        tipo_exame = testaOpcao(0, 1);
                                        existe = 1;
                                    }
                                }
                                if (tipo_exame == 0 || existe == 0) {
                                    opcao = 0;
                                }
                            } else {
                                opcao = 0;
                            }
                        }
                        if (tipo_exame != 0) {
                            String data_escrita;
                            opcao = 1;
                            int sair = 1, opcao2 = 1;
                            while (opcao != 0) {
                                sair = 1;

                                while (opcao2 != 0) {
                                    try {
                                        out.println("Data(hh:mm:ss dd-mm-yyyy):");
                                        data_escrita = inputS.nextLine();
                                        data_inicial = dt.parse(data_escrita);
                                        data_final = new Date(data_inicial.getTime());
                                        out.print("Duracao em minutos:");
                                        duracao = testaOpcao(0, 180);
                                        data_final.setMinutes(data_inicial.getMinutes() + duracao);
                                        opcao2 = 0;
                                    } catch (ParseException e) {
                                        out.println("Erro no formato da data!");
                                    }
                                }
                                if (testaDocente(docente_responsavel, data_inicial, data_final) == 1) {
                                    out.print("Horario incompativel com o docente responsavel da disciplina.\n1-Mudar Hora\n0-Sair");
                                    opcao = testaOpcao(0, 1);
                                    if (opcao == 1) {
                                        opcao2 = 1;
                                    } else {
                                        sair = 0;
                                    }
                                }
                                if (opcao2 == 0 && sair != 0) {
                                    int numero_salas = 0;
                                    numero_salas = imprimeSalas(data_inicial, data_final, contaAlunos(disciplina, tipo_exame));
                                    if (numero_salas == 0) {
                                        out.print("Nao existem salas Disponiveis para o exame a hora que pretende realizar.\n"
                                                + "1-Mudar hora do exame\n"
                                                + "0-Sair");
                                        opcao = testaOpcao(0, 1);
                                        if (opcao != 1) {
                                            sair = 0;
                                        } else {
                                            opcao2 = 1;
                                        }
                                    } else {
                                        out.print("0-Sair");
                                        opcao = testaOpcao(0, numero_salas);
                                        if (opcao > 0) {
                                            sala = buscarSala(opcao, data_inicial, data_final);
                                            opcao = 0;
                                        } else {
                                            sair = 0;
                                        }
                                    }
                                }
                            }
                            if (sair != 0) {
                                adicionaDocenteNaoDOcentesExame(data_inicial, data_final, docente_responsavel,
                                        vigilantes, ajudantes);
                                if (tipo_exame == 1) {
                                    for (Pessoa x : lista_pessoas) {
                                        if (x instanceof Aluno) {
                                            if (disciplina.getArrayAlunos().contains((Aluno) x)) {
                                                if (alunos.size() < sala.getMaximo()) {
                                                    alunos.put((Aluno) x, -1);
                                                }
                                            }
                                        }
                                    }
                                    Normal normal = new Normal(disciplina, duracao, sala, docente_responsavel, vigilantes, ajudantes, alunos, data_inicial, data_final);
                                    lista_exames.add(normal);
                                } else if (tipo_exame == 2) {
                                    Recurso recurso = new Recurso(disciplina, duracao, sala, docente_responsavel, vigilantes, ajudantes, alunos, data_inicial, data_final);
                                    lista_exames.add(recurso);

                                } else if (tipo_exame == 3) {
                                    Especial especial = new Especial(disciplina, duracao, sala, docente_responsavel, vigilantes, ajudantes, alunos, data_inicial, data_final);
                                    lista_exames.add(especial);

                                }
                                escreveExamesFicheiro();
                            }
                        }
                    } else {
                        out.println("Nao é possivel criar mais exames nesta disciplina!");
                    }
                }
            } else {
                out.println("Nao existem salas, salas devem de ser criadas primeiro!");
            }
        } else {
            out.println("Nao existem disciplinas!");
        }
    }

    /**
     *
     */
    public void configurarSalaExame() {
        int opcao, tipo_exame, numero_salas;
        Exame exame;
        Sala sala;
        if (!lista_exames.isEmpty()) {
            imprimeContadorExame();
            out.print("0-Sair");
            opcao = testaOpcao(0, lista_exames.size());
            if (opcao != 0) {
                exame = (Exame) lista_exames.get(opcao - 1);
                if (exame instanceof Normal) {
                    tipo_exame = 1;
                } else if (exame instanceof Recurso) {
                    tipo_exame = 2;
                } else {
                    tipo_exame = 3;
                }
                out.println("Sala atual: \n" + exame.getSala().toString());
                numero_salas = imprimeSalas(exame.getDataInicio(), exame.getDataFinal(), contaAlunos(exame.getDisciplina(), tipo_exame));
                if (numero_salas == 0) {
                    out.println("Nao existem mais salas disponiveis para a hora do exame!");
                } else {
                    out.print("0-Sair");
                    opcao = testaOpcao(0, numero_salas);
                    if (opcao > 0) {
                        sala = buscarSala(opcao, exame.getDataInicio(), exame.getDataFinal());
                        exame.setSala(sala);
                        while (exame.getAlunos().size() > exame.getSala().getMaximo()) {
                            exame.removeAluno(removeAlunoExcesso(exame));
                        }
                    }
                    escreveExamesFicheiro();
                }
            }
        } else {
            out.println("Nao existem exames!");
        }
    }

    /**
     *
     */
    public void convocaAjudantesExame() {
        int opcao;
        Exame exame;
        if (!lista_exames.isEmpty()) {
            imprimeContadorExame();
            out.print("0-Sair");
            opcao = testaOpcao(0, lista_exames.size());
            if (opcao != 0) {
                exame = (Exame) lista_exames.get(opcao - 1);
                adicionaDocenteNaoDOcentesExame(exame.getDataInicio(), exame.getDataFinal(), exame.getDocenteResponsavel(),
                        exame.getDocentes(), exame.getApoio());
                escreveExamesFicheiro();
            }
        } else {
            out.println("Nao existem exames!");
        }
    }

    /**
     *
     */
    public void increveAlunosExame() {
        int i, opcao = 1;
        Exame exame;
        Disciplina disciplina;
        if (!lista_exames.isEmpty()) {
            imprimeContadorExame();
            out.print("0-sair");
            opcao = testaOpcao(0, lista_exames.size());
            if (opcao != 0) {
                exame = (Exame) lista_exames.get(opcao - 1);
                disciplina = exame.getDisciplina();
                if (!disciplina.getArrayAlunos().isEmpty()) {
                    while (opcao != 0) {
                        if (exame.getAlunos().size() < exame.getSala().getMaximo()) {
                            for (int j = 0; j < 2; j++) {
                                i = 0;
                                for (Aluno x : disciplina.getArrayAlunos()) {
                                    if (exame instanceof Normal || exame instanceof Recurso
                                            || (exame instanceof Especial && (x.getRegime().equals("trabalhador-estudante")
                                            || x.getRegime().equals("atleta") || x.getRegime().equals("dirigente associativo")
                                            || testaFinalista(x, disciplina) == 1))) {
                                        if (!exame.getAlunos().containsKey((Aluno) x)) {
                                            if (j == 0) {
                                                out.println(++i + "-" + ((Aluno) x).toString());
                                            } else {
                                                ++i;
                                                if (i == opcao) {
                                                    exame.adicionaAluno((Aluno) x, -1);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (i != 0) {
                                    if (j == 0) {
                                        out.print("0-sair");
                                        opcao = testaOpcao(0, i);
                                    }
                                } else {
                                    opcao = 0;
                                    out.println("Nao existem alunos para serem adicionados!");
                                    j++;
                                }
                            }
                        } else {
                            out.println("Impossivel adicionar mais alunos devido ao tamanho da sala ou nao há mais alunos para serem adicionados!");
                            opcao = 0;
                        }
                    }
                } else {
                    out.println("Nao existem alunos para serem adicionados!");
                }
                escreveExamesFicheiro();
            }
        } else {
            out.println("Nao existem exames!");
        }
    }

    /**
     *
     */
    public void lancaNotasExame() {
        int opcao = 1, exame_escolhido, nota;
        if (!lista_exames.isEmpty()) {
            imprimeContadorExame();
            out.print("0-Sair");
            exame_escolhido = testaOpcao(0, lista_exames.size());
            if (exame_escolhido != 0) {
                while (opcao != 0) {
                    for (int j = 0; j < 2; j++) {
                        int i = 0;
                        for (Entry<Aluno, Integer> e : ((Exame) lista_exames.get(exame_escolhido - 1)).getAlunos().entrySet()) {
                            if (j == 0) {
                                out.print(++i + "-" + ((Aluno) e.getKey()).toString());
                                if (e.getValue() == -1) {
                                    out.println("-Nota nao lancada ainda");
                                } else {
                                    out.println("-Nota:" + e.getValue());
                                }
                            } else {
                                ++i;
                                if (opcao == i) {
                                    out.print("Nota:");
                                    nota = testaOpcao(0, 20);
                                    ((Exame) lista_exames.get(exame_escolhido - 1)).adicionaAluno(e.getKey(), nota);
                                }
                            }
                        }
                        if (i != 0) {
                            if (j == 0) {
                                out.print("0-sair");
                                opcao = testaOpcao(0, i);
                            }
                        } else {
                            out.println("Nao existem alunos para serem lançadas notas!");
                            j++;
                            opcao = 0;
                        }
                    }
                }
                escreveExamesFicheiro();
            }
        } else {
            out.println("Nao existem exames para serem lancadas notas!");
        }
    }

    /**
     *
     */
    public void adicionaDocentesDisciplina() {
        Disciplina disciplina;
        Docente responsavel;
        int i = 0, opcao;
        if (!lista_disciplinas.isEmpty()) {
            imprimeContadorDisciplinas();
            out.print("0-Sair");
            opcao = testaOpcao(0, lista_disciplinas.size());
            if (opcao != 0) {
                disciplina = (Disciplina) lista_disciplinas.get(opcao - 1);
                responsavel = disciplina.getResponsavelTotal();
                while (opcao != 0) {
                    for (int j = 0; j < 2; j++) {
                        i = 0;
                        for (Pessoa x : lista_pessoas) {
                            if (x instanceof Docente && !responsavel.equals((Docente) x) && !disciplina.getArrayDocentes().contains((Docente) x)) {
                                if (j == 0) {
                                    out.println(++i + "-" + ((Docente) x).toString());
                                } else {
                                    ++i;
                                    if (i == opcao) {
                                        disciplina.adicionaDocentes((Docente) x);
                                    }
                                }
                            }
                        }
                        if (i != 0) {
                            if (j == 0) {
                                out.print("0-Sair");
                                opcao = testaOpcao(0, i);
                            }
                        } else {
                            out.print("Nao existem mais docentes para serem adicionados a esta disciplina!");
                            opcao = 0;
                        }
                    }
                }
            }
        } else {
            out.print("nao existem disciplinas!");
        }
    }

    /**
     *
     */
    public void inscreveAlunoDisciplina() {
        int i = 0, opcao = 0, inscrito = 0;
        Disciplina disciplina;
        if (!lista_disciplinas.isEmpty()) {
            imprimeContadorDisciplinas();
            out.print("0-Sair");
            opcao = testaOpcao(0, lista_disciplinas.size());
            if (opcao != 0) {
                disciplina = (Disciplina) lista_disciplinas.get(opcao - 1);
                while (opcao != 0) {
                    for (int j = 0; j < 2; j++) {
                        i = 0;
                        for (Pessoa k : lista_pessoas) {
                            inscrito = 0;
                            if (k instanceof Aluno) {
                                for (Entry<Curso, Integer> e : ((Aluno) k).getCursos().entrySet()) {
                                    if (e.getKey().getDisciplinasCurso().contains(disciplina)) {
                                        inscrito = 1;
                                    }
                                }
                                if (inscrito == 1 && !disciplina.getArrayAlunos().contains((Aluno) k)) {
                                    if (j == 0) {
                                        out.println(++i + "-" + ((Aluno) k).toString());
                                    } else {
                                        ++i;
                                        if (i == opcao) {
                                            disciplina.adicionaAlunos((Aluno) k);
                                        }
                                    }
                                }
                            }
                        }
                        if (i != 0) {
                            if (j == 0) {
                                out.print("0-Sair");
                                opcao = testaOpcao(0, i);
                            }
                        } else {
                            out.println("Nao existem mais alunos para adicionar!");
                            j++;
                            opcao = 0;
                        }
                    }
                }
            }
        } else {
            out.println("Nao existem disciplinas!");
        }
    }

    /**
     *
     */
    public void adicionaCursosAluno() {
        int i = 0, opcao = 0;
        Aluno aluno = null;
        if (!lista_cursos.isEmpty()) {
            for (int j = 0; j < 2; j++) {
                i = 0;
                for (Pessoa x : lista_pessoas) {
                    if (x instanceof Aluno) {
                        if (j == 0) {
                            out.print(++i + "-");
                            out.println(((Aluno) x).toString());
                        } else {
                            ++i;
                            if (i == opcao) {
                                aluno = (Aluno) x;
                            }
                        }
                    }
                }
                if (i != 0) {
                    if (j == 0) {
                        out.print("0-Sair");
                        opcao = testaOpcao(0, i);
                    }
                } else {
                    out.print("Nao existem alunos!");
                    j++;
                }
            }
            int matricula;
            while (opcao != 0) {
                for (int j = 0; j < 2; j++) {
                    i = 0;
                    for (Curso k : lista_cursos) {
                        if (!aluno.getCursos().containsKey(k)) {
                            if (j == 0) {
                                out.println(++i + "-" + k.toString());
                            } else {
                                ++i;
                                if (i == opcao) {
                                    j++;
                                    out.println("Ano de matricula no Curso:");
                                    out.print("0-sair");
                                    matricula = testaOpcao(0, k.getDuracaoCurso());
                                    if (matricula != 0) {
                                        aluno.adicionaCurso(k, matricula);
                                    }
                                }
                            }
                        }
                    }

                    if (i != 0) {
                        if (j == 0) {
                            out.print("0-Sair");
                            opcao = testaOpcao(0, i);
                        }
                    } else {
                        out.println("Nao existem Cursos para serem adicionados!");
                        opcao = 0;
                        j++;
                    }

                }
            }
        } else {
            out.println("Nao existem Cursos!");
        }
    }

    /**
     *
     */
    public void adicionaDisciplinasCurso() {
        int i = 0, opcao = 1;
        Curso curso = null;

        for (Curso x : lista_cursos) {
            out.println(++i + "-" + x.toString());
        }
        if (i != 0) {
            out.print("0-Sair");
            opcao = testaOpcao(0, i);
            if (opcao != 0) {
                curso = lista_cursos.get(opcao - 1);
                while (opcao != 0) {
                    for (int j = 0; j < 2; j++) {
                        i = 0;
                        for (Disciplina x : lista_disciplinas) {
                            if (!curso.getDisciplinasCurso().contains(x)) {
                                if (j == 0) {
                                    out.println(++i + "-" + x.toString());
                                } else {
                                    ++i;
                                    if (i == opcao) {
                                        curso.adicionaDisciplinas(x);
                                    }
                                }
                            }
                        }
                        if (i != 0) {
                            if (j == 0) {
                                out.print("0-Sair");
                                opcao = testaOpcao(0, i);
                            }
                        } else {
                            out.println("Nao existem mais disciplina para adicionar!");
                            j++;
                            opcao = 0;
                        }
                        if (opcao == 0) {
                            j++;
                            opcao = 0;
                        }
                    }
                }
            }
        } else {
            out.println("Nao existem cursos!");
        }
    }

    //REMOVE

    /**
     *
     */
        public void eliminaPessoa() {
        int opcao, i = 0;
        if (!lista_pessoas.isEmpty()) {
            out.print("1-Aluno\n"
                    + "2-Docente\n"
                    + "3-Nao Docente\n"
                    + "0-Sair");
            opcao = testaOpcao(0, 3);
            if (opcao != 0) {
                switch (opcao) {
                    case 1:
                        ArrayList<Aluno> alunos = new ArrayList<>();
                        Aluno aluno;
                        for (Pessoa x : lista_pessoas) {
                            if (x instanceof Aluno) {
                                out.println(++i + "-" + ((Aluno) x).toString());
                                alunos.add((Aluno) x);
                            }
                        }
                        if (i != 0) {
                            out.print("0-Sair");
                            opcao = testaOpcao(0, i);
                            if (opcao == 0) {
                                out.println("Cancelado");
                            } else {
                                aluno = alunos.get(opcao - 1);
                                for (Disciplina x : lista_disciplinas) {
                                    if (x.getArrayAlunos().contains(aluno)) {
                                        x.removeAlunos(aluno);
                                    }
                                }
                                for (Exame x : lista_exames) {
                                    for (Entry<Aluno, Integer> key : x.getAlunos().entrySet()) {
                                        if (key.getKey().equals(aluno)) {
                                            x.removeAluno(key.getKey());
                                        }
                                    }
                                }
                                lista_pessoas.remove(aluno);
                            }
                        } else {
                            out.println("Não existem alunos criados");
                        }
                        break;
                    case 2:
                        i = 0;
                        int elimina;
                        ArrayList<Docente> docentes = new ArrayList<>();
                        ArrayList<Disciplina> elimina_disciplinas = new ArrayList<>();
                        Docente docente;
                        for (Pessoa x : lista_pessoas) {
                            if (x instanceof Docente) {
                                out.println(++i + "-" + ((Docente) x).toString());
                                docentes.add((Docente) x);
                            }
                        }
                        if (i != 0) {
                            out.print("0-Sair");
                            elimina = testaOpcao(0, i);
                            if (elimina != 0) {
                                docente = (Docente) docentes.get(elimina - 1);
                                for (Disciplina x : lista_disciplinas) {
                                    if (x.getResponsavelTotal().equals(docente)) {
                                        out.println("Docente que quer eliminar é o responsavel pela Disciplina: " + x.getDisciplina());
                                        out.println("1-Mudar de Responsável(Só irá imprimir os sem exames sobreposto com os da Disciplina)");
                                        out.println("2-Eliminar Disciplina(irá eliminar todos os exames desta disciplina)");
                                        out.print("0-Sair");
                                        elimina = testaOpcao(0, 2);
                                        if (elimina != 0) {
                                            if (elimina == 1) {
                                                opcao = substituiResponsavel(x, docentes, docente);
                                                if (opcao == 1) {
                                                    elimina_disciplinas.add(x);
                                                }
                                            } else {
                                                elimina_disciplinas.add(x);
                                            }
                                        }
                                    } else if (x.getArrayDocentes().contains(docente)) {
                                        x.removeDocentes(docente);
                                    }
                                }
                                for (Disciplina x : elimina_disciplinas) {
                                    eliminarDisciplina(x);
                                }
                                if (elimina != 0) {
                                    for (Exame x : lista_exames) {
                                        if (x.getDocentes().contains(docente)) {
                                            x.removeVigilantes(docente);
                                        }
                                    }
                                    lista_pessoas.remove(docente);
                                }
                            }
                        } else {
                            out.println("Nao existe Docentes para eliminar!");
                        }
                        break;
                    case 3:
                        i = 0;//testar
                        ArrayList<NaoDocentes> naodocentes = new ArrayList<>();
                        NaoDocentes person;
                        for (Pessoa x : lista_pessoas) {
                            if (x instanceof NaoDocentes) {
                                out.println(++i + "-" + ((NaoDocentes) x).toString());
                                naodocentes.add((NaoDocentes) x);
                            }
                        }
                        if (i != 0) {
                            out.print("0-Sair");
                            opcao = testaOpcao(0, i);
                            if (opcao != 0) {
                                person = naodocentes.get(opcao - 1);
                                for (Exame y : lista_exames) {
                                    if (y.getApoio().contains(person)) {
                                        y.getApoio().remove(person);
                                    }
                                }
                                lista_pessoas.remove(person);
                            }
                        } else {
                            out.println("Nao existem Funcionarios Nao Docentes!");
                        }
                        break;
                    default:
                        out.println("Opcao invalida!");
                }
                escreveExamesFicheiro();
            }
        } else {
            out.println("Nao existem pessoas");
        }
    }

    /**
     *
     */
    public void eliminarCurso() {
        int i = 0, opcao;
        Curso curso;
        if (!lista_cursos.isEmpty()) {
            for (Curso x : lista_cursos) {
                out.println(++i + "-" + x.toString());
            }
            out.print("0-Sair");
            opcao = testaOpcao(0, i);
            if (opcao != 0) {
                curso = lista_cursos.get(opcao - 1);
                for (Pessoa x : lista_pessoas) {
                    if (x instanceof Aluno) {
                        if (((Aluno) x).getCursos().containsKey(curso)) {
                            removeCurso(curso, ((Aluno) x));
                            ((Aluno) x).removeCurso(curso);
                        }
                    }
                }
                lista_cursos.remove(curso);
                out.println("Curso eliminada com sucesso!");
            }
            escreveExamesFicheiro();
        } else {
            out.println("Não existem cursos!");
        }
    }

    /**
     *
     */
    public void eliminaCursoAluno() {
        int i, opcao = 0;
        if (!lista_cursos.isEmpty()) {
            for (int j = 0; j < 2; j++) {
                i = 0;
                for (Pessoa x : lista_pessoas) {
                    if (x instanceof Aluno) {
                        if (j == 0) {
                            out.println(++i + "-" + ((Aluno) x).toString());
                        } else {
                            ++i;
                            if (i == opcao) {
                                selecionaCursoAluno((Aluno) x);
                            }
                        }
                    }
                }
                if (i != 0) {
                    if (j == 0) {
                        out.print("0-Sair");
                        opcao = testaOpcao(0, i);
                    }
                } else {
                    out.println("Nao existem alunos!");
                    j++;
                }
                if (opcao == 0) {
                    j++;
                }
            }
            escreveExamesFicheiro();
        } else {
            out.println("Nao existem cursos!");
        }
    }

    /**
     *
     */
    public void removeDisciplinaCurso() {
        Curso curso = null;
        Disciplina disciplina = null;
        int i = 0, opcao = 0;
        if (!lista_cursos.isEmpty()) {
            for (Curso x : lista_cursos) {
                out.println(++i + "-" + x.toString());
            }
            out.print("0-Sair");
            opcao = testaOpcao(0, i);
            if (opcao != 0) {
                curso = lista_cursos.get(opcao - 1);
                if (!curso.getDisciplinasCurso().isEmpty()) {
                    i = 0;
                    for (Disciplina x : curso.getDisciplinasCurso()) {
                        out.println(++i + "-" + x.toString());
                    }
                    out.print("0-Sair");
                    opcao = testaOpcao(0, i);
                    if (opcao != 0) {
                        disciplina = curso.getDisciplinasCurso().get(opcao - 1);
                        out.println(disciplina);
                        int existe;
                        for (Pessoa x : lista_pessoas) {
                            existe = 0;
                            if (x instanceof Aluno) {
                                for (Entry<Curso, Integer> k : ((Aluno) x).getCursos().entrySet()) {
                                    if (k.getKey().getDisciplinasCurso().contains(disciplina) && !k.getKey().equals(curso)) {
                                        existe = 1;
                                    }
                                }
                                if (existe == 0) {
                                    for (Exame k : lista_exames) {
                                        if (((Exame) k).getDisciplina().equals(disciplina)) {
                                            if (k.getAlunos().containsKey((Aluno) x)) {
                                                k.removeAluno((Aluno) x);
                                            }
                                        }
                                    }
                                    if (disciplina.getArrayAlunos().contains((Aluno) x)) {
                                        disciplina.removeAlunos((Aluno) x);
                                    }
                                }
                            }
                        }
                        curso.removeDisciplinas(disciplina);
                        escreveExamesFicheiro();
                    }
                } else {
                    out.println("Nao existem Disciplinas neste curso!");
                }
            }
        } else {
            out.println("Nao existem cursos!");
        }
    }

    /**
     *
     */
    public void selecionaDisciplinaEliminar() {
        int opcao;
        Disciplina elimina;
        if (!lista_disciplinas.isEmpty()) {
            out.println("Todos os exames associados a esta disciplina tambem serao apagados");
            imprimeContadorDisciplinas();
            out.print("0-Sair");
            opcao = testaOpcao(0, lista_disciplinas.size());
            if (opcao != 0) {
                elimina = lista_disciplinas.get(opcao - 1);
                eliminarDisciplina(elimina);
                escreveExamesFicheiro();
            }
        } else {
            out.println("Não existem disciplinas|");
        }
    }

    /**
     *
     */
    public void eliminaExame() {
        int opcao;
        if (!lista_exames.isEmpty()) {
            imprimeContadorExame();
            out.print("0-Sair");
            opcao = testaOpcao(0, lista_exames.size());
            if (opcao != 0) {
                lista_exames.remove(lista_exames.get(opcao - 1));
                escreveExamesFicheiro();
            }
        } else {
            out.println("Não existe Exames criados!");
        }
    }

    /**
     *
     */
    public void eliminaSala() {
        Sala sala, sala1;
        ArrayList<Exame> exames_eliminar = new ArrayList<>();
        int i = 0, opcao, numero_salas, opcao_sala = 0, remove = 0, existe = 1;
        if (lista_salas.isEmpty()) {
            out.println("Não existem salas para eliminar!");
        } else {
            for (Sala x : lista_salas) {
                out.println(++i + "-" + x.toString());
            }
            out.print("0-Sair");
            opcao = testaOpcao(0, i);
            if (opcao != 0) {
                sala = lista_salas.get(opcao - 1);
                for (Exame y : lista_exames) {
                    if (y.getSala().equals(sala)) {
                        existe = 0;
                        out.println("Foi encontrado um Exame com a sala que quer eliminar.");
                        out.print("Exame:");
                        imprimeExames(y);
                        out.println("1-Atribuir nova sala");
                        out.println("2-Eliminar Exame");
                        out.print("0-Sair");
                        opcao_sala = testaOpcao(0, 2);
                        if (opcao_sala != 0) {
                            if (opcao_sala == 1) {
                                numero_salas = imprimeSalas(y.getDataInicio(), y.getDataFinal(), y.getAlunos().size());
                                if (numero_salas != 0) {
                                    out.print("0-Sair");
                                    opcao_sala = testaOpcao(0, numero_salas);
                                    if (opcao_sala != 0) {
                                        sala1 = buscarSala(opcao_sala, y.getDataInicio(), y.getDataFinal());
                                        y.setSala(sala1);
                                        remove = 1;
                                    }
                                } else {
                                    out.println("Nao existe salas disponiveis!");
                                    out.println("1-Criar nova sala");
                                    out.println("2-Elinimar Exame");
                                    out.print("0-Sair");
                                    opcao_sala = testaOpcao(0, 2);
                                    if (opcao_sala != 0) {
                                        if (opcao_sala == 1) {
                                            criaSalas();
                                            numero_salas = imprimeSalas(y.getDataInicio(), y.getDataFinal(), y.getAlunos().size());
                                            if (numero_salas != 0) {
                                                out.print("0-Sair");
                                                opcao_sala = testaOpcao(0, numero_salas);
                                                if (opcao_sala != 0) {
                                                    sala1 = buscarSala(opcao_sala, y.getDataInicio(), y.getDataFinal());
                                                    y.setSala(sala1);
                                                    remove = 1;
                                                }
                                            } else {
                                                out.println("Nao há mais salas disponiveis!");
                                            }
                                        } else {
                                            exames_eliminar.add(y);
                                            remove = 1;
                                        }
                                    }
                                }
                            } else {
                                exames_eliminar.add(y);
                                remove = 1;
                            }
                        }
                    }
                }
                for (Exame x : exames_eliminar) {
                    lista_exames.remove(x);
                }
                if (remove == 1 || existe == 1) {
                    lista_salas.remove(sala);
                }
                escreveExamesFicheiro();
            }
        }
    }

    /**
     *
     */
    public void removePessoaExame() {
        ArrayList<Docente> docentes = new ArrayList<>();
        int opcao, i = 0;
        Exame exame;
        if (!lista_exames.isEmpty()) {
            imprimeContadorExame();
            out.print("0-Sair");
            opcao = testaOpcao(0, lista_exames.size());
            if (opcao != 0) {
                exame = lista_exames.get(opcao - 1);
                out.print("1-Docentes\n"
                        + "2-Funcionarios de apoio\n"
                        + "3-Alunos\n"
                        + "0-Sair");
                opcao = testaOpcao(0, 3);
                if (opcao != 0) {
                    switch (opcao) {
                        case 1:
                            Docente docente;
                            for (Docente x : exame.getDocentes()) {
                                out.println(++i + "-" + x.toString());
                            }
                            out.println(++i + "-" + exame.getDocenteResponsavel().toString() + "-Docente Responsavel da Disciplina que esta o exame!");
                            out.print("0-Sair");
                            opcao = testaOpcao(0, exame.getDocentes().size() + 1);
                            if (opcao != 0) {
                                if (opcao != exame.getDocentes().size() + 1) {
                                    docente = exame.getDocentes().get(opcao - 1);
                                    exame.removeVigilantes(docente);
                                } else {
                                    out.println("Docente que quer eliminar é o responsavel pela Disciplina que quer eliminar");
                                    out.println("1-Mudar de Responsável(Só irá imprimir os sem exames sobreposto com os da Disciplinae ira tambem modificar o responsavel da disciplina)");
                                    out.println("2-Eliminar exame");
                                    out.print("0-Sair");
                                    opcao = testaOpcao(0, 2);
                                    if (opcao != 0) {
                                        if (opcao != 2) {
                                            docente = exame.getDocenteResponsavel();
                                            for (Pessoa x : lista_pessoas) {
                                                if (x instanceof Docente) {
                                                    docentes.add((Docente) x);
                                                }
                                            }
                                            opcao = substituiResponsavel(exame.getDisciplina(), docentes, docente);
                                            if (opcao == 1) {
                                                lista_exames.remove(exame);
                                            }

                                        } else {
                                            lista_exames.remove(exame);
                                        }
                                    }
                                }
                            }
                            break;
                        case 2:
                            NaoDocentes nao_docente;
                            for (NaoDocentes x : exame.getApoio()) {
                                out.println(++i + "-" + x.toString());
                            }
                            if (i != 0) {
                                out.print("0-Sair");
                                opcao = testaOpcao(0, exame.getApoio().size());
                                if (opcao != 0) {
                                    nao_docente = exame.getApoio().get(opcao - 1);
                                    exame.removeApoio(nao_docente);
                                }
                            } else {
                                out.println("Nao existem Funcionario de apoio ao exame!");
                            }
                            break;
                        case 3:
                            ArrayList<Aluno> alunos = new ArrayList<>();
                            Aluno aluno;
                            for (Entry<Aluno, Integer> x : exame.getAlunos().entrySet()) {
                                out.println(++i + "-" + x.getKey().toString());
                                alunos.add(x.getKey());
                            }
                            if (i != 0) {
                                out.print("0-Sair");
                                opcao = testaOpcao(0, exame.getAlunos().size());
                                if (opcao != 0) {
                                    aluno = alunos.get(opcao - 1);
                                    exame.removeAluno(aluno);
                                }
                            } else {
                                out.println("Nao existem alunos no exame!");
                            }
                            break;
                        default:
                            out.println("Opcao invalida!");
                    }
                    escreveExamesFicheiro();
                }
            }
        } else {
            out.println("Nao existem exames!");
        }
    }

    /**
     *
     */
    public void removePessoaDisciplina() {
        int i = 0, opcao;
        ArrayList<Docente> docentes = new ArrayList<>();
        Disciplina disciplina;
        if (!lista_disciplinas.isEmpty()) {
            imprimeContadorDisciplinas();
            out.print("0-Sair");
            opcao = testaOpcao(0, lista_disciplinas.size());
            if (opcao != 0) {
                disciplina = lista_disciplinas.get(opcao - 1);
                out.print("1-Docentes\n"
                        + "2-Alunos\n"
                        + "0-Sair");
                opcao = testaOpcao(0, 2);
                if (opcao != 0) {
                    switch (opcao) {
                        case 1:
                            Docente docente;
                            for (Docente x : disciplina.getArrayDocentes()) {
                                out.println(++i + "-" + x.toString());
                            }
                            out.println(++i + "-" + disciplina.getResponsavelTotal().toString() + "-Docente Responsavel da disciplina");
                            out.print("0-Sair");
                            opcao = testaOpcao(0, disciplina.getArrayDocentes().size() + 1);
                            if (opcao != 0) {
                                if (opcao != disciplina.getArrayDocentes().size() + 1) {
                                    docente = disciplina.getArrayDocentes().get(opcao - 1);
                                    disciplina.removeDocentes(docente);
                                } else {
                                    out.println("Docente que quer eliminar é o responsavel pela Disciplina");
                                    out.println("1-Mudar de Responsável(Só irá imprimir os sem exames sobreposto com os da Disciplina)");
                                    out.println("2-Eliminar Disciplina(irá eliminar todos os exames desta disciplina)");
                                    out.print("0-Sair");
                                    opcao = testaOpcao(0, 2);
                                    if (opcao != 0) {
                                        if (opcao != 1) {
                                            eliminarDisciplina(disciplina);
                                        } else {
                                            docente = disciplina.getResponsavelTotal();
                                            for (Pessoa x : lista_pessoas) {
                                                if (x instanceof Docente) {
                                                    docentes.add((Docente) x);
                                                }
                                            }
                                            opcao = substituiResponsavel(disciplina, docentes, docente);
                                            if (opcao == 1) {
                                                eliminarDisciplina(disciplina);
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        case 2:
                            Aluno aluno;
                            for (Aluno x : disciplina.getArrayAlunos()) {
                                out.println(++i + "-" + x.toString());
                            }
                            if (i != 0) {
                                out.print("0-Sair");
                                opcao = testaOpcao(0, disciplina.getArrayAlunos().size());
                                if (opcao != 0) {
                                    aluno = disciplina.getArrayAlunos().get(opcao - 1);
                                    disciplina.removeAlunos(aluno);
                                }
                            } else {
                                out.println("Nao existem alunos na disciplina");
                            }
                            break;
                        default:
                            out.println("opcao invalida!");
                    }
                }
                escreveExamesFicheiro();
            }
        } else {
            out.println("Nao existem disciplinas!");
        }
    }

    //LISTAGENS

    /**
     *
     */
        public void listaExames() {
        if (!lista_exames.isEmpty()) {
            for (Exame x : lista_exames) {
                imprimeExames(x);
            }
        } else {
            out.println("Nao existem exames!");
        }
    }

    /**
     *
     */
    public void listarAlunosExame() {
        int opcao;
        Exame exame;
        if (!lista_exames.isEmpty()) {
            imprimeContadorExame();
            out.print("0-sair");
            opcao = testaOpcao(0, lista_exames.size());
            if (opcao != 0) {
                exame = (Exame) lista_exames.get(opcao - 1);
                if (!exame.getAlunos().isEmpty()) {
                    for (Entry<Aluno, Integer> e : exame.getAlunos().entrySet()) {
                        out.println("Aluno: " + ((Aluno) e.getKey()).toString());
                    }
                } else {
                    out.println("Nao existem alunos no exame!");
                }
            }
        } else {
            out.println("Nao existem exames!");
        }
    }

    /**
     *
     */
    public void listaExamesAluno() {
        int i = 0, test = 0, opcao;
        if (!lista_exames.isEmpty()) {
            for (Pessoa x : lista_pessoas) {
                if (x instanceof Aluno) {
                    out.println(++i + "-" + ((Aluno) x).toString());
                }
            }
            if (i != 0) {
                out.print("0-Sair");
                opcao = testaOpcao(0, i);
                if (i != 0) {
                    i = 0;
                    for (Pessoa x : lista_pessoas) {
                        if (x instanceof Aluno) {
                            ++i;
                            if (i == opcao) {
                                out.println("O aluno: " + ((Aluno) x).toString());
                                out.println("Esta inscrito nos exames:");
                                for (Exame k : lista_exames) {
                                    if (k.getAlunos().containsKey((Aluno) x)) {
                                        imprimeExames(k);
                                        test = 1;
                                    }
                                }
                                if (test == 0) {
                                    out.println("Nao esta inscrito a nenhum exame!");
                                }
                            }
                        }
                    }
                }
            } else {
                out.print("Nao existem alunos!");
            }
        } else {
            out.println("Impossivel algum aluno estar inscrito em exames, nao existem exames!");
        }
    }

    /**
     *
     */
    public void listaDocentesFuncionariosExame() {
        int opcao;
        if (!lista_exames.isEmpty()) {
            imprimeContadorExame();
            out.print("0-Sair");
            opcao = testaOpcao(0, lista_exames.size());
            if (opcao != 0) {
                Exame exame = (Exame) lista_exames.get(opcao - 1);
                out.println("Docente responsavel:" + ((Docente) exame.getDocenteResponsavel()).toString());
                if (!exame.getDocentes().isEmpty()) {
                    out.println("Outros docentes vigilantes:");
                    for (Docente x : exame.getDocentes()) {
                        out.println(((Docente) x).toString());
                    }
                } else {
                    out.println("Nao existem mais docentes no exame!");
                }
                if (!exame.getApoio().isEmpty()) {
                    out.println("Funcionarios de apoio:");
                    for (NaoDocentes x : exame.getApoio()) {
                        out.println(((NaoDocentes) x).toString());
                    }
                } else {
                    out.println("Nao existem funcionarios de apoio no exame!");
                }
            }
        } else {
            out.println("Nao existem exames!");
        }
    }

    /**
     *
     */
    public void listaExamesDocentesFuncionario() {
        int opcao, i = 0, existe = 0;
        if (!lista_exames.isEmpty()) {
            out.print("1-Docente\n2-Funcionario\n0-Sair");
            opcao = testaOpcao(0, 2);
            if (opcao != 0) {
                if (opcao == 1) {
                    for (int j = 0; j < 2; j++) {
                        i = 0;
                        for (Pessoa x : lista_pessoas) {
                            if (x instanceof Docente) {
                                if (j == 0) {
                                    out.println(++i + "-" + ((Docente) x).toString());
                                } else {
                                    ++i;
                                    if (i == opcao) {
                                        out.println("O docente: " + ((Docente) x).toString() + "\nEsta nos exames:");
                                        for (Exame k : lista_exames) {
                                            if (k.getDocentes().contains((Docente) x) || x.equals(k.getDocenteResponsavel())) {
                                                imprimeExames(k);
                                                existe = 1;
                                            }
                                        }
                                        if (existe == 0) {
                                            out.println("O docente nao se encontra em nenhum exame!");
                                        }
                                    }
                                }
                            }
                        }
                        if (i != 0) {
                            if (j == 0) {
                                out.print("0-Sair");
                                opcao = testaOpcao(0, i);
                            }

                        } else {
                            out.println("Nao existem Docentes!");
                            j++;
                        }
                    }
                } else {
                    for (int j = 0; j < 2; j++) {
                        i = 0;
                        for (Pessoa x : lista_pessoas) {
                            if (x instanceof NaoDocentes) {
                                if (j == 0) {
                                    out.println(++i + "-" + ((NaoDocentes) x).toString());
                                } else {
                                    ++i;
                                    if (i == opcao) {
                                        out.println("O ajudante: " + ((NaoDocentes) x).toString() + "\nEsta nos exames:");
                                        for (Exame k : lista_exames) {
                                            if (k.getApoio().contains((NaoDocentes) x)) {
                                                imprimeExames(k);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (i != 0) {
                            if (j == 0) {
                                out.print("0-Sair");
                                opcao = testaOpcao(0, i);
                            }

                        } else {
                            out.println("Nao existem Funcionarios!");
                            j++;
                        }
                    }
                }
            }
        } else {
            out.println("Opcao impossivel de executar nao existem exames!");
        }
    }

    /**
     *
     */
    public void listaNotas() {
        int opcao;
        if (!lista_exames.isEmpty()) {
            imprimeContadorExame();
            out.print("0-Sair");
            opcao = testaOpcao(0, lista_exames.size());
            if (opcao != 0) {
                Exame exame = (Exame) lista_exames.get(opcao - 1);
                if (!exame.getAlunos().isEmpty()) {
                    for (Entry<Aluno, Integer> e : exame.getAlunos().entrySet()) {
                        out.print("Aluno: " + ((Aluno) e.getKey()).toString());
                        if (e.getValue() == -1) {
                            out.println("-Nota: nao lançadas");
                        } else {
                            out.println("-Nota:" + e.getValue());
                        }
                    }
                } else {
                    out.println("Nao existem alunos inscritos no exame!");
                }
            }
        } else {
            out.println("Nao existem exames!");
        }
    }

    /**
     *
     */
    public void listaDisciplinas() {
        if (!lista_disciplinas.isEmpty()) {
            String outros_docentes_e_alunos;
            for (Disciplina x : lista_disciplinas) {
                outros_docentes_e_alunos = x.getDocentesDisciplina();
                out.println("----------Disciplina---------");
                out.println(x.toString());
                out.println("Outros Docentes da Disciplina:");
                out.println(outros_docentes_e_alunos);
                outros_docentes_e_alunos = x.getAlunosDisciplina();
                out.println("Alunos incritos na Disciplina:");
                out.println(outros_docentes_e_alunos);
            }
        } else {
            out.println("Nao existe disciplinas!");
        }
    }

    /**
     *
     */
    public void listaCursos() {
        if (!lista_cursos.isEmpty()) {
            for (Curso x : lista_cursos) {
                out.println(x.toString());
                out.println(x.getStringDisciplinasCurso());
            }
        } else {
            out.println("Nao existem cursos!");
        }
    }

    /**
     *
     */
    public void listaPessoas() {
        if (!lista_pessoas.isEmpty()) {
            int opcao, existe = 0;
            out.print("1-Funcionarios\n"
                    + "2-Alunos\n"
                    + "0-Sair");
            opcao = testaOpcao(0, 2);
            if (opcao != 0) {
                switch (opcao) {
                    case 1:
                        out.print("1-Docentes\n"
                                + "2-Nao Docentes\n"
                                + "0-Sair");
                        opcao = testaOpcao(0, 2);
                        if (opcao != 0) {
                            switch (opcao) {
                                case 1:
                                    for (Pessoa x : lista_pessoas) {
                                        if (x instanceof Docente) {
                                            out.println("---------------------");
                                            out.println(((Docente) x).toString());
                                            existe = 1;
                                        }
                                    }
                                    if (existe != 1) {
                                        out.println("Nao existem Docentes!");
                                    }
                                    break;
                                case 2:
                                    for (Pessoa x : lista_pessoas) {
                                        if (x instanceof NaoDocentes) {
                                            out.println("---------------------");
                                            out.println(((NaoDocentes) x).toString());
                                            existe = 1;
                                        }
                                    }
                                    if (existe != 1) {
                                        out.println("Nao existem Nao Docentes!");
                                    }
                                    break;
                                default:
                                    out.println("Opcao invalida");
                            }
                        }
                        break;
                    case 2:
                        for (Pessoa x : lista_pessoas) {
                            if (x instanceof Aluno) {
                                out.println("---------------------");
                                out.println(((Aluno) x).toString());
                                existe = 1;
                            }
                        }
                        if (existe != 1) {
                            out.println("Nao existem Docentes!");
                        }
                        break;
                    default:
                        out.println("Opcao invalida!");
                }
            }
        } else {
            out.println("Nao existem pessoas!");
        }
    }

    /**
     *
     */
    public void listaSalas() {
        if (!lista_salas.isEmpty()) {
            for (Sala x : lista_salas) {
                out.println(x.toString());
            }
        } else {
            out.println("Nao existem salas!");
        }
    }

    /**
     *
     */
    public void listaAlunoCursos() {
        int opcao = 0, i = 0;
        for (int j = 0; j < 2; j++) {
            i = 0;
            for (Pessoa x : lista_pessoas) {
                if (x instanceof Aluno) {
                    if (j == 0) {
                        out.println(++i + "-" + ((Aluno) x).toString());
                    } else {
                        ++i;
                        if (i == opcao) {
                            if (!((Aluno) x).getCursos().entrySet().isEmpty()) {
                                for (Entry<Curso, Integer> k : ((Aluno) x).getCursos().entrySet()) {
                                    out.println(((Curso) k.getKey()).toString());
                                }
                            } else {
                                out.println("O aluno escolhido nao esta inscrito em nenhum curso!");
                            }
                        }
                    }
                }
            }
            if (i != 0) {
                if (j == 0) {
                    out.print("0-Sair");
                    opcao = testaOpcao(0, i);
                }

            } else {
                out.println("Nao existem alunos!");
                j++;
            }
            if (opcao == 0) {
                j++;
            }
        }
    }

    //ADDS

    /**
     *
     * @param aluno
     */
        public void addAluno(Aluno aluno) {
        lista_pessoas.add(aluno);
    }

    /**
     *
     * @param docente
     */
    public void addDocente(Docente docente) {
        lista_pessoas.add(docente);
    }

    /**
     *
     * @param nao_docentes
     */
    public void addNaoDocente(NaoDocentes nao_docentes) {
        lista_pessoas.add(nao_docentes);
    }

    /**
     *
     * @param disciplina
     */
    public void addDiscipla(Disciplina disciplina) {
        lista_disciplinas.add(disciplina);
    }

    /**
     *
     * @param curso
     */
    public void addCurso(Curso curso) {
        lista_cursos.add(curso);
    }

    /**
     *
     * @param exame
     */
    public void addExame(Exame exame) {
        lista_exames.add(exame);
    }

    //OUTRAS FUNÇOES

    /**
     *
     * @param disciplina
     * @param docentes
     * @param docente
     * @return
     */
        public int substituiResponsavel(Disciplina disciplina, ArrayList<Docente> docentes, Docente docente) {
        int i, elimina = 0;
        for (int j = 0; j < 2; j++) {
            i = 0;
            for (Docente y : docentes) {
                if (!y.equals(docente)) {
                    if (testaDocenteNovoResponsavel(y, disciplina) == 0) {
                        if (j == 0) {
                            out.println(++i + "-" + ((Docente) y).toString());
                        } else {
                            ++i;
                            if (i == elimina) {
                                disciplina.setDocenteResponsavel(y);
                                if (disciplina.getArrayDocentes().contains(y)) {
                                    disciplina.removeDocentes(y);
                                }
                                for (Exame k : lista_exames) {
                                    if (k.getDisciplina().equals(disciplina)) {
                                        k.setResponsavel(y);
                                        if (k.getDocentes().contains(y)) {
                                            k.removeVigilantes(y);
                                        }
                                    }
                                }
                                return 2;
                            }
                        }
                    }
                }
            }
            if (i != 0) {
                if (j == 0) {
                    out.print("0-Sair");
                    elimina = testaOpcao(0, i);
                }
            } else {
                j++;
                out.println("Nao existem Docentes disponiveis!");
                out.println("1-Eliminar Disciplina/Exame(irá eliminar todos os exames desta disciplina)");
                out.print("0-Sair");
                elimina = testaOpcao(0, 1);
                if (elimina != 0) {
                    return 1;
                }
            }
            if (elimina == 0) {
                return 0;
            }
        }
        return 3;
    }

    /**
     *
     * @param disciplina
     * @param tipo_exame
     * @return
     */
    public int contaAlunos(Disciplina disciplina, int tipo_exame) {
        int conta_alunos = 0;
        for (Aluno x : disciplina.getArrayAlunos()) {
            if (tipo_exame == 1 || tipo_exame == 2
                    || (tipo_exame == 3 && (x.getRegime().equals("trabalhador-estudante")
                    || x.getRegime().equals("atleta") || x.getRegime().equals("dirigente associativo")
                    || testaFinalista(x, disciplina) == 1))) {
                conta_alunos++;
            }
        }
        return conta_alunos;
    }

    /**
     *
     * @param exame
     * @return
     */
    public Aluno removeAlunoExcesso(Exame exame) {
        for (Entry<Aluno, Integer> e : exame.getAlunos().entrySet()) {
            return e.getKey();
        }
        return null;
    }

    /**
     *
     * @param exame_inicio
     * @param exame_final
     * @param alunos_total
     * @return
     */
    public int imprimeSalas(Date exame_inicio, Date exame_final, int alunos_total) {
        int i = 0;
        out.println("Salas Disponeis:");
        for (Sala x : lista_salas) {
            if (testaSala(x, exame_inicio, exame_final) == 0) {
                out.print(++i + "-");
                out.print(x.toString());
                if (alunos_total > x.getMaximo()) {
                    out.println(" -Alguns alunos ficarao sem lugar e nao poderam realizar o exame.");
                } else {
                    out.println(" -Sala com lugares disponiveis para todos os alunos possiveis de se inscrever.");
                }
            }
        }
        return i;
    }

    /**
     *
     * @param opcao
     * @param exame_inicio
     * @param exame_final
     * @return
     */
    public Sala buscarSala(int opcao, Date exame_inicio, Date exame_final) {
        int i = 0;
        for (Sala x : lista_salas) {
            if (testaSala(x, exame_inicio, exame_final) == 0) {
                ++i;
                if (i == opcao) {
                    return x;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param sala
     * @param data_inicial
     * @param data_final
     * @return
     */
    public int testaSala(Sala sala, Date data_inicial, Date data_final) {
        for (Exame k : lista_exames) {
            if (sala.equals(k.getSala())
                    && (((data_final.before(k.getDataFinal())
                    && data_final.after(k.getDataInicio()))
                    || (data_inicial.before(k.getDataFinal()) && data_inicial.after(k.getDataInicio()))
                    || data_inicial.equals(k.getDataInicio())) || data_final.equals(k.getDataFinal())
                    || (data_inicial.before(k.getDataInicio()) && data_final.after(k.getDataFinal())))) {
                return 1;
            }
        }
        return 0;
    }

    /**
     *
     * @param data_inicial
     * @param data_final
     * @param docente_responsavel
     * @param vigilantes
     * @param ajudantes
     */
    public void adicionaDocenteNaoDOcentesExame(Date data_inicial, Date data_final, Docente docente_responsavel,
            ArrayList<Docente> vigilantes, ArrayList<NaoDocentes> ajudantes) {
        int opcao = 1, test, i;
        while (opcao != 0) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    out.println("Docentes disponiveis para serem adicionados ao vigilantes:");
                }
                i = 0;
                for (Pessoa x : lista_pessoas) {
                    if (x instanceof Docente) {
                        test = testaDocente((Docente) x, data_inicial, data_final);
                        if (!docente_responsavel.equals((Docente) x) && !vigilantes.contains((Docente) x)) {
                            if (test == 0 && j == 0) {
                                out.print(++i + "-");
                                out.println(((Docente) x).toString());
                            }
                            if (test == 0 && j == 1) {
                                ++i;
                                if (i == opcao) {
                                    vigilantes.add((Docente) x);
                                }
                            }
                        }
                    }
                }
                if (i != 0) {
                    if (j == 0) {
                        out.print("0-Sair");
                        opcao = testaOpcao(0, i);
                    }
                } else {
                    out.println("Nao existem Docentes disponiveis á hora marcada para serem convocados como vigilantes!");
                    opcao = 0;
                    j++;
                }
            }
        }
        opcao = 1;
        while (opcao != 0) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    out.println("Funcionarios disponiveis para serem adicionados aos ajudantes:");
                }
                i = 0;
                for (Pessoa x : lista_pessoas) {
                    if (x instanceof NaoDocentes) {
                        if (!ajudantes.contains((NaoDocentes) x)) {
                            if (j == 0) {
                                out.println(++i + "-" + ((NaoDocentes) x).toString());
                            } else {
                                ++i;
                                if (i == opcao) {
                                    ajudantes.add((NaoDocentes) x);
                                }
                            }
                        }
                    }
                }
                if (i != 0) {
                    if (j == 0) {
                        out.print("0-Sair");
                        opcao = testaOpcao(0, i);
                    }
                } else {
                    out.println("Nao existem mais ajudantes para adicionar.");
                    opcao = 0;
                    j++;
                }
            }
        }
    } //acabar este test

    /**
     *
     * @param aluno
     * @param disciplina
     * @return
     */
    public int testaFinalista(Aluno aluno, Disciplina disciplina) {
        for (Entry<Curso, Integer> e : aluno.getCursos().entrySet()) {
            if (e.getKey().getDuracaoCurso() == e.getValue() && e.getKey().getDisciplinasCurso().contains(disciplina)) {
                return 1;
            }
        }
        return 0;
    }

    /**
     *
     * @param aluno
     */
    public void selecionaCursoAluno(Aluno aluno) {
        int i = 0, opcao = 0;
        Curso curso = null;
        for (int j = 0; j < 2; j++) {
            i = 0;
            for (Entry<Curso, Integer> x : aluno.getCursos().entrySet()) {
                if (j == 0) {
                    out.println(++i + "-" + x.getKey());
                } else {
                    ++i;
                    if (i == opcao) {
                        curso = x.getKey();
                    }
                }
            }
            if (i != 0) {
                if (j == 0) {
                    out.print("0-Sair");
                    opcao = testaOpcao(0, i);
                }
            } else {
                out.println("O aluno nao esta em nenhum curso!");
                j++;
            }
            if (opcao == 0) {
                j++;
            }
        }
        if (i != 0) {
            removeCurso(curso, aluno);
        }
    }

    /**
     *
     * @param curso
     * @param aluno
     */
    public void removeCurso(Curso curso, Aluno aluno) {
        ArrayList<Disciplina> lista_disciplinas_elimina = new ArrayList<>();
        ArrayList<Disciplina> remover = new ArrayList<>();
        for (Disciplina x : curso.getDisciplinasCurso()) {
            lista_disciplinas_elimina.add(x);
        }
        aluno.removeCurso(curso);
        for (Disciplina k : lista_disciplinas_elimina) {
            for (Entry<Curso, Integer> x : aluno.getCursos().entrySet()) {
                if (x.getKey().getDisciplinasCurso().contains(k));
                remover.add(k);
            }
        }
        for (Disciplina x : remover) {
            lista_disciplinas_elimina.remove(x);
        }
        for (Disciplina x : lista_disciplinas_elimina) {
            for (Exame k : lista_exames) {
                if (k.getDisciplina().equals(x)) {
                    if (k.getAlunos().containsKey(aluno)) {
                        k.removeAluno(aluno);
                    }
                }
            }
            if (x.getArrayAlunos().contains(aluno)) {
                x.removeAlunos(aluno);
            }
        }
    }

    /**
     *
     * @param disciplina
     */
    public void eliminarDisciplina(Disciplina disciplina) {
        ArrayList<Exame> elimina_exames = new ArrayList<>();
        for (Curso x : lista_cursos) {
            if (x.getDisciplinasCurso().contains(disciplina)) {
                x.removeDisciplinas(disciplina);
            }
        }
        for (Exame x : lista_exames) {
            if (x.getDisciplina().equals(disciplina)) {
                elimina_exames.add(x);
            }
        }
        for (Exame x : elimina_exames) {
            lista_exames.remove(x);
        }
        lista_disciplinas.remove(disciplina);
        out.println("Disciplina eliminada com sucesso");
    }

    /**
     *
     * @param docente
     * @param disciplina
     * @return
     */
    public int testaDocenteNovoResponsavel(Docente docente, Disciplina disciplina) {
        int test;
        for (Exame k : lista_exames) {
            if (!k.getDocentes().contains(docente) && k.getDisciplina().equals(disciplina)) {
                test = testaDocente(docente, k.getDataInicio(), k.getDataFinal());
                if (test != 0) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     *
     * @param nome_sala
     * @return
     */
    public boolean testaNomeSala(String nome_sala) {
        for (Sala x : lista_salas) {
            if (nome_sala.equals(x.getNomeSala())) {
                out.println("Nome de Sala ja existente!");
                out.println("Nome da sala:");
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param nome_disciplina
     * @return
     */
    public boolean testaNomeDisciplina(String nome_disciplina) {
        for (Disciplina x : lista_disciplinas) {
            if (nome_disciplina.equals(x.getDisciplina())) {
                out.println("Nome de Disciplina ja existente!");
                out.println("Nome disciplina:");
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param nome_curso
     * @return
     */
    public boolean testaNomeCurso(String nome_curso) {
        for (Curso x : lista_cursos) {
            if (nome_curso.equals(x.getCurso())) {
                out.println("Nome de curso ja existente!");
                out.println("Nome do Curso:");
                return false;
            }
        }
        return true;
    }

    /**
     *
     */
    public void imprimeContadorDisciplinas() {
        int i = 1;
        for (Disciplina x : lista_disciplinas) {
            out.println(i++ + "-" + x.toString());
        }
    }

    /**
     *
     * @param disciplina
     * @return
     */
    public int testaExames(Disciplina disciplina) {
        int numero_exames = 0;
        for (Exame x : lista_exames) {
            if (disciplina.equals(x.getDisciplina())) {
                numero_exames++;
            }
        }
        return numero_exames;
    }

    /**
     *
     * @param x
     * @param data_inicial
     * @param data_final
     * @return
     */
    public int testaDocente(Docente x, Date data_inicial, Date data_final) {
        for (Exame k : lista_exames) {
            if ((((Docente) x).equals(k.getDocenteResponsavel())
                    || k.getDocentes().contains((Docente) x))
                    && (((data_final.before(k.getDataFinal())
                    && data_final.after(k.getDataInicio()))
                    || (data_inicial.before(k.getDataFinal()) && data_inicial.after(k.getDataInicio()))
                    || data_inicial.equals(k.getDataInicio())) || data_final.equals(k.getDataFinal())
                    || (data_inicial.before(k.getDataInicio()) && data_final.after(k.getDataFinal())))) {
                return 1;
            }
        }
        return 0;
    }

    /**
     *
     * @param minimo
     * @param maximo
     * @return
     */
    public int testaOpcao(int minimo, int maximo) {
        int opcao = 0, test;
        String numero;
        while (true) {
            test = 0;
            try {
                while (test != 1) {
                    try {
                        out.print("\n[" + minimo + "-" + maximo + "]Escolha:");
                        numero = inputS.nextLine();
                        opcao = Integer.parseInt(numero);
                        test = 1;
                    } catch (Exception e) {
                        out.print("Escolha invalida!");
                    }
                }
                if (opcao < minimo || opcao > maximo) {
                    out.print("Escolha invalida!");
                } else {
                    return opcao;
                }
            } catch (InputMismatchException exception) {
                out.print("Escolha invalida!");
                inputI.next();
            }
        }
    }

    /**
     *
     */
    public void imprimeContadorExame() {
        int i = 1;
        for (Exame x : lista_exames) {
            out.print(i++ + "-");
            imprimeExames(x);
        }
    }

    /**
     *
     * @param exame
     */
    public void imprimeExames(Exame exame) {
        if (exame instanceof Normal) {
            out.println(((Normal) exame).toString());
        }
        if (exame instanceof Recurso) {
            out.println(((Recurso) exame).toString());
        }
        if (exame instanceof Especial) {
            out.println(((Especial) exame).toString());
        }
    }

    /**
     *
     * @param p
     * @return
     */
    public boolean verificaString(String p) { // testar só para espaços
        int i = 0;
        char[] array = p.toCharArray();
        while (p.length() != i) {
            if ((int) array[0] == 32 || ((int) array[i] > 90 && (int) array[i] < 97) || (int) array[array.length - 1] == 32 || (((int) array[i] > 122 || (int) array[i] < 65) && (int) array[i] != 32)) {
                out.println("Só letras sao aceitaveis e nao se pode comecar com um espaco nem acabar com um espaco");
                return false;
            }
            i++;
        }
        if (p.length() == 0) {
            out.println("Só letras sao aceitaveis e nao se pode comecar com um espaco nem acabar com um espaco");
            return false;
        }
        return true;
    }

    /**
     *
     * @param p
     * @return
     */
    public boolean verificaStringComNumeros(String p) {
        int i = 0;
        char[] array = p.toCharArray();
        while (p.length() != i) {
            if ((int) array[0] == 32 || (int) array[array.length - 1] == 32 || ((int) array[i] > 90 && (int) array[i] < 97) || ((((int) array[i] > 122 || (int) array[i] < 65) && ((int) array[i] > 57 || (int) array[i] < 48)) && (int) array[i] != 32)) {
                out.println("Nao pode comecar com um espaco nem acabar com um espaco e só pode conter letras e numeros!");
                return false;
            }
            i++;
        }
        if (p.length() == 0) {
            out.println("Nao pode comecar com um espaco nem acabar com um espaco e só pode conter letras e numeros!");
            return false;
        }
        return true;
    }

    /**
     *
     * @param aluno
     * @return
     */
    public boolean verificaNumeroAluno(int aluno) {
        for (Pessoa x : lista_pessoas) {
            if (x instanceof Aluno) {
                if (((Aluno) x).getNumeroAluno() == aluno) {
                    out.println("Numero de Aluno já existente!");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @param funcionario_numero
     * @return
     */
    public boolean verificaNumeroFuncionario(int funcionario_numero) {
        for (Pessoa x : lista_pessoas) {
            if (x instanceof Funcionario) {
                if (((Funcionario) x).getNumeroFuncionario() == funcionario_numero) {
                    out.println("Numero de Funcionario já existente!");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @param num
     * @return
     */
    public boolean verificaNumeros(String num) {
        int i = 0;
        char[] array = num.toCharArray();
        while (num.length() != i) {
            if ((int) array[i] > 57 || (int) array[i] < 48) {
                out.println("Nao pode comecar com um espaco nem acabar com um espaco e só pode conter numeros!");
                return false;
            }
            i++;
        }
        if (num.length() == 0) {
            out.println("Nao pode comecar com um espaco nem acabar com um espaco e só pode conter numeros!");
            return false;
        }
        return true;
    }

    /**
     *
     */
    public void escreveExamesFicheiro() {
        String convert_string = "";
        FileWriter escreve;
        try {
            File file_rescrita = new File("Exames.txt");
        } catch (Exception e) {
            out.println("Ficheiro nao localizado novo ficheiro criado!");
        }
        File fnew = new File("Exames.txt");
        try {
            escreve = new FileWriter(fnew, false);
            for (Exame x : lista_exames) {
                escreve.write("Exame:\r\n");
                escreve.write(x.toString() + "\r\n");
                if (!x.getDocentes().isEmpty()) {
                    escreve.write("Outros Docentes:\r\n");
                    for (Docente k : x.getDocentes()) {
                        convert_string += "Nome:" + k.getNome() + " Numero mecanografico:" + k.getNumeroFuncionario();
                        escreve.write(convert_string + "\r\n");
                        convert_string = "";
                    }
                }
                if (!x.getApoio().isEmpty()) {
                    escreve.write("Funcionarios de Apoio:\r\n");
                    for (NaoDocentes k : x.getApoio()) {
                        convert_string += "Nome:" + k.getNome() + " Numero mecanografico:" + k.getNumeroFuncionario();
                        escreve.write(convert_string + "\r\n");
                        convert_string = "";
                    }
                }
                if (!x.getAlunos().isEmpty()) {
                    escreve.write("Alunos:\r\n");
                    for (Entry<Aluno, Integer> k : x.getAlunos().entrySet()) {
                        convert_string += "Nome:" + k.getKey().getNome() + " Numero Aluno:" + k.getKey().getNumeroAluno();
                        if (k.getValue() == -1) {
                            convert_string += " Nota: Nota nao lançada";
                        } else {
                            convert_string += " Nota: " + k.getValue();
                        }
                        escreve.write(convert_string + "\r\n");
                        convert_string = "";
                    }
                }
            }
            escreve.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void escrever() { //FUNÇAO DE RECURSO
        try {
            FileOutputStream os = new FileOutputStream("Pessoas.dat");
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(lista_pessoas);
            oos.close();
            os = new FileOutputStream("Salas.dat");
            oos = new ObjectOutputStream(os);
            oos.writeObject(lista_salas);
            oos.close();
            os = new FileOutputStream("Lista_exames.dat");
            oos = new ObjectOutputStream(os);
            oos.writeObject(lista_exames);
            oos.close();
            os = new FileOutputStream("Cursos.dat");
            oos = new ObjectOutputStream(os);
            oos.writeObject(lista_cursos);
            oos.close();
            os = new FileOutputStream("lista_disciplinas.dat");
            oos = new ObjectOutputStream(os);
            oos.writeObject(lista_disciplinas);
            oos.close();
 
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a escrever para ficheiro de objecto");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Erro a aceder ao ficheiro");
            ex.printStackTrace();
        }
       
    }
    public void ler(){
        try {
            FileInputStream is = new FileInputStream("Pessoas.dat");
            ObjectInputStream ois = new ObjectInputStream(is);
            lista_pessoas = (ArrayList<Pessoa>) ois.readObject();
            ois.close();
            is = new FileInputStream("Salas.dat");
            ois = new ObjectInputStream(is);
            lista_salas = (ArrayList<Sala>) ois.readObject();
            ois.close();
            is = new FileInputStream("Lista_exames.dat");
            ois = new ObjectInputStream(is);
            lista_exames = (ArrayList<Exame>) ois.readObject();
            ois.close();
            is = new FileInputStream("Cursos.dat");
            ois = new ObjectInputStream(is);
            lista_cursos = (ArrayList<Curso>) ois.readObject();
            ois.close();
            is = new FileInputStream("lista_disciplinas.dat");
            ois = new ObjectInputStream(is);
            lista_disciplinas = (ArrayList<Disciplina>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a ler para o ficheiro1");
        } catch (IOException ex) {
            System.out.println("Erro a aceder ao ficheiro1");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a ler objecto1");
        }
    }*/

}
