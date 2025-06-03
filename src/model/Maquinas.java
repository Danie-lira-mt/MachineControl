package model;

import java.io.FileWriter;
import java.io.IOException;

public class Maquinas {
    private int id;
    private int patrimonio;
    private String tipo;
    private String marca;
    private String funcionario;
    private int matricula;
    private String data;
    private String relatorio;

    public Maquinas() {
    }

    public Maquinas(int id, int patrimonio, String tipo, String marca, String funcionario, int matricula, String data, String relatorio) {
        this.id = id;
        this.patrimonio = patrimonio;
        this.tipo = tipo;
        this.marca = marca;
        this.funcionario = funcionario;
        this.matricula = matricula;
        this.data = data;
        this.relatorio = relatorio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(int patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }
    
}