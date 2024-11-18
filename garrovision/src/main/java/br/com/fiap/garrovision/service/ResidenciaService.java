package br.com.fiap.garrovision.service;

import br.com.fiap.garrovision.BO.ResidenciaBO;
import br.com.fiap.garrovision.dominio.Residencia;
import br.com.fiap.garrovision.exceptions.APIException;
import br.com.fiap.garrovision.exceptions.NotFoundException;
import br.com.fiap.garrovision.infra.dao.ResidenciaDAO;

import java.sql.SQLException;
import java.util.List;

public class ResidenciaService {

    private final ResidenciaDAO residenciaDAO;
    private final ResidenciaBO residenciaBO;

    public ResidenciaService() {
        this.residenciaDAO = new ResidenciaDAO();
        this.residenciaBO = new ResidenciaBO();
    }

    public List<Residencia> buscarTodasResidencias() {
        try {
            return residenciaDAO.findAll();
        } catch (SQLException e) {
            throw new APIException("Erro ao buscar todas as residências", e);
        }
    }

    public Residencia buscarResidenciaPorId(int id) {
        Residencia residencia = residenciaDAO.findById(id);
        if (residencia == null) {
            throw new NotFoundException("Residência não encontrada para o ID: " + id);
        }
        return residencia;
    }

    public void salvarResidencia(Residencia residencia) {
        try {
            residenciaBO.validarResidencia(residencia);
            residenciaDAO.save(residencia);
        } catch (SQLException e) {
            throw new APIException("Erro ao salvar residência", e);
        }
    }

    public void atualizarResidencia(Residencia residencia) {
        try {
            residenciaBO.validarResidencia(residencia);
            residenciaDAO.update(residencia);
        } catch (SQLException e) {
            throw new APIException("Erro ao atualizar residência", e);
        }
    }

    public void excluirResidencia(int id) {
        try {
            residenciaDAO.delete(id);
        } catch (SQLException e) {
            throw new APIException("Erro ao excluir residência", e);
        }
    }
}
