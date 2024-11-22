package br.com.fiap.garrovision.BO;

import br.com.fiap.garrovision.dominio.Residencia;
import br.com.fiap.garrovision.exceptions.ValidationException;

public class ResidenciaBO {

    public void validarResidencia(Residencia residencia) {
        if (residencia.getCapacidadeGeracao() <= 0) {
            throw new ValidationException("Capacidade de geração deve ser maior que zero.");
        }
        if (residencia.getNomeResponsavel() == null || residencia.getNomeResponsavel().isEmpty()) {
            throw new ValidationException("O nome do responsável é obrigatório.");
        }
    }
}
