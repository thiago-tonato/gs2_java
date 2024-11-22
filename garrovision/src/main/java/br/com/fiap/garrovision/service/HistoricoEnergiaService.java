package br.com.fiap.garrovision.service;

import br.com.fiap.garrovision.BO.HistoricoEnergiaBO;
import br.com.fiap.garrovision.dominio.HistoricoEnergia;
import br.com.fiap.garrovision.exceptions.APIException;
import br.com.fiap.garrovision.infra.dao.HistoricoEnergiaDAO;

import java.util.List;

public class HistoricoEnergiaService {

    private final HistoricoEnergiaDAO historicoEnergiaDAO;
    private final HistoricoEnergiaBO historicoEnergiaBO;

    public HistoricoEnergiaService() {
        this.historicoEnergiaDAO = new HistoricoEnergiaDAO();
        this.historicoEnergiaBO = new HistoricoEnergiaBO();
    }

    public List<HistoricoEnergia> buscarTodos() {
        return historicoEnergiaDAO.findAll();
    }

    public HistoricoEnergia buscarPorId(int id) {
        HistoricoEnergia historico = historicoEnergiaDAO.findById(id);
        if (historico == null) {
            throw new APIException("Histórico de energia com ID " + id + " não encontrado.");
        }
        return historico;
    }

    public void salvar(HistoricoEnergia historico) {
        historicoEnergiaBO.validarHistorico(historico);
        historicoEnergiaDAO.save(historico);
    }

    public void atualizar(HistoricoEnergia historico) {
        if (historicoEnergiaDAO.findById(historico.getIdHistorico()) == null) {
            throw new APIException("Histórico de energia com ID " + historico.getIdHistorico() + " não encontrado.");
        }
        historicoEnergiaBO.validarHistorico(historico);
        historicoEnergiaDAO.update(historico);
    }

    public void remover(int id) {
        if (historicoEnergiaDAO.findById(id) == null) {
            throw new APIException("Histórico de energia com ID " + id + " não encontrado.");
        }
        historicoEnergiaDAO.delete(id);
    }
}
