package model;

import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Relatorio {
    
    private int id;
    private String hora;
    private String data;
    private String nome;
    private int matricula;
    private String maquina;
    private int patrimonio;
    private String marca;
    private double localizacao;
    private double manutencao;
    private String relatorio;

    public Relatorio() {
    }

    public Relatorio(int id, String hora, String data, String nome, int matricula, String maquina, int patrimonio, String marca, double localizacao, double manutencao, String relatorio) {
        this.id = id;
        this.hora = hora;
        this.data = data;
        this.nome = nome;
        this.matricula = matricula;
        this.maquina = maquina;
        this.patrimonio = patrimonio;
        this.marca = marca;
        this.localizacao = localizacao;
        this.manutencao = manutencao;
        this.relatorio = relatorio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public int getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(int patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(double localizacao) {
        this.localizacao = localizacao;
    }

    public double getManutencao() {
        return manutencao;
    }

    public void setManutencao(double manutencao) {
        this.manutencao = manutencao;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }
    

   }
