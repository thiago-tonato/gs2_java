package br.com.fiap.garrovision.BO;

import br.com.fiap.garrovision.dominio.HistoricoEnergia;
import br.com.fiap.garrovision.exceptions.APIException;

public class HistoricoEnergiaBO {

    public void validarHistorico(HistoricoEnergia historico) {
        if (historico.getProducao() < 0) {
            throw new APIException("A produção de energia não pode ser negativa.");
        }
        if (historico.getConsumo() < 0) {
            throw new APIException("O consumo de energia não pode ser negativo.");
        }
        if (historico.getIdResidencia() <= 0) {
            throw new APIException("O ID da residência deve ser válido.");
        }
    }
}