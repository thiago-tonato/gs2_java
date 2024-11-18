package br.com.fiap.garrovision.BO;

import br.com.fiap.garrovision.dominio.Residencia;
import br.com.fiap.garrovision.exceptions.ValidationException;

public class ResidenciaBO {

    // Lógica para validar o consumo de energia
    public boolean validarConsumo(double capacidadeGeracao, double limiteConsumo) {
        // Valida se a capacidade de geração está dentro do limite de consumo
        if (capacidadeGeracao <= 0) {
            throw new ValidationException("Capacidade de geração deve ser maior que zero.");
        }
        return capacidadeGeracao <= limiteConsumo;
    }

    // Validar a residência - pode ter regras específicas de negócios
    public void validarResidencia(Residencia residencia) {
        if (residencia.getCapacidadeGeracao() <= 0) {
            throw new ValidationException("Capacidade de geração deve ser maior que zero.");
        }
        if (residencia.getNomeResponsavel() == null || residencia.getNomeResponsavel().isEmpty()) {
            throw new ValidationException("O nome do responsável é obrigatório.");
        }
    }
}
