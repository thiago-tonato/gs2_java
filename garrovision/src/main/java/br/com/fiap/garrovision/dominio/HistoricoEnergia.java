package br.com.fiap.garrovision.dominio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class HistoricoEnergia {
    private int idHistorico;
    private int idResidencia;
    private Date dataRegistro;
    private double producao;
    private double consumo;
    private double saldoEnergetico;

    @JsonCreator
    public HistoricoEnergia(@JsonProperty("idHistorico") int idHistorico, @JsonProperty("idResidencia") int idResidencia, @JsonProperty("dataRegistro") Date dataRegistro, @JsonProperty("producao") double producao, @JsonProperty("consumo") double consumo, @JsonProperty("saldoEnergetico") double saldoEnergetico) {
        this.idHistorico = idHistorico;
        this.idResidencia = idResidencia;
        this.dataRegistro = dataRegistro;
        this.producao = producao;
        this.consumo = consumo;
        this.saldoEnergetico = saldoEnergetico;
    }

    public HistoricoEnergia() {
    }

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public int getIdResidencia() {
        return idResidencia;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public double getProducao() {
        return producao;
    }

    public double getConsumo() {
        return consumo;
    }

    public double getSaldoEnergetico() {
        return saldoEnergetico;
    }

}

