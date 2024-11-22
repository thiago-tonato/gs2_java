package br.com.fiap.garrovision.dominio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Residencia {
    private int idResidencia;
    private String nomeResponsavel;
    private String endereco;
    private double capacidadeGeracao;
    private String tipoFonte;
    private double limiteConsumo;
    private Date dataCadastro;

    @JsonCreator
    public Residencia(@JsonProperty("idResidencia") int idResidencia, @JsonProperty("nomeResponsavel") String nomeResponsavel, @JsonProperty("endereco") String endereco, @JsonProperty("capacidadeGeracao") double capacidadeGeracao, @JsonProperty("tipoFonte") String tipoFonte, @JsonProperty("limiteConsumo") double limiteConsumo, @JsonProperty("dataCadastro") Date dataCadastro) {
        this.idResidencia = idResidencia;
        this.nomeResponsavel = nomeResponsavel;
        this.endereco = endereco;
        this.capacidadeGeracao = capacidadeGeracao;
        this.tipoFonte = tipoFonte;
        this.limiteConsumo = limiteConsumo;
        this.dataCadastro = dataCadastro;
    }

    public Residencia() {
    }

    public int getIdResidencia() {
        return idResidencia;
    }

    public void setIdResidencia(int idResidencia) {
        this.idResidencia = idResidencia;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getCapacidadeGeracao() {
        return capacidadeGeracao;
    }

    public void setCapacidadeGeracao(double capacidadeGeracao) {
        this.capacidadeGeracao = capacidadeGeracao;
    }

    public String getTipoFonte() {
        return tipoFonte;
    }

    public void setTipoFonte(String tipoFonte) {
        this.tipoFonte = tipoFonte;
    }

    public double getLimiteConsumo() {
        return limiteConsumo;
    }

    public void setLimiteConsumo(double limiteConsumo) {
        this.limiteConsumo = limiteConsumo;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}


