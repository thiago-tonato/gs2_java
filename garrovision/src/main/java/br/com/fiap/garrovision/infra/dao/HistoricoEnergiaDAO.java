package br.com.fiap.garrovision.infra.dao;

import br.com.fiap.garrovision.dominio.HistoricoEnergia;
import br.com.fiap.garrovision.exceptions.APIException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoricoEnergiaDAO {

    private final Connection connection;
    ConnectionFactory connectionfactory = new ConnectionFactory();

    public HistoricoEnergiaDAO() {
        this.connection = connectionfactory.getConnection();
    }

    public List<HistoricoEnergia> findAll() {
        List<HistoricoEnergia> historicos = new ArrayList<>();
        String sql = "SELECT * FROM historico_energia";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                HistoricoEnergia historico = new HistoricoEnergia(
                        rs.getInt("id_historico"),
                        rs.getInt("id_residencia"),
                        rs.getDate("data_registro"),
                        rs.getDouble("producao"),
                        rs.getDouble("consumo"),
                        rs.getDouble("saldo_energetico")
                );
                historicos.add(historico);
            }
        } catch (SQLException e) {
            throw new APIException("Erro ao buscar histórico de energia.", e);
        }
        return historicos;
    }

    public HistoricoEnergia findById(int id) {
        String sql = "SELECT * FROM historico_energia WHERE id_historico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new HistoricoEnergia(
                            rs.getInt("id_historico"),
                            rs.getInt("id_residencia"),
                            rs.getDate("data_registro"),
                            rs.getDouble("producao"),
                            rs.getDouble("consumo"),
                            rs.getDouble("saldo_energetico")
                    );
                }
            }
        } catch (SQLException e) {
            throw new APIException("Erro ao buscar histórico de energia por ID.", e);
        }
        return null;
    }

    public List<HistoricoEnergia> findByResidenciaId(int idResidencia) {
        String sql = "SELECT * FROM historico_energia WHERE id_residencia = ?";
        List<HistoricoEnergia> historicos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idResidencia); // Definindo o id da residência como parâmetro
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    historicos.add(new HistoricoEnergia(
                            rs.getInt("id_historico"),
                            rs.getInt("id_residencia"),
                            rs.getDate("data_registro"),
                            rs.getDouble("producao"),
                            rs.getDouble("consumo"),
                            rs.getDouble("saldo_energetico")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new APIException("Erro ao buscar histórico de energia por ID da residência.", e);
        }
        return historicos;
    }


    public void save(HistoricoEnergia historico) {
        String sql = "INSERT INTO historico_energia (id_historico, id_residencia, data_registro, producao, consumo, saldo_energetico) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (PreparedStatement seqStmt = connection.prepareStatement("SELECT HISTORICO_SEQ.NEXTVAL FROM DUAL");
                 ResultSet rs = seqStmt.executeQuery()) {
                if (rs.next()) {
                    int nextId = rs.getInt(1);
                    historico.setIdHistorico(nextId);
                }
            }

            stmt.setInt(1, historico.getIdHistorico());
            stmt.setInt(2, historico.getIdResidencia());
            stmt.setDate(3, new java.sql.Date(historico.getDataRegistro().getTime()));
            stmt.setDouble(4, historico.getProducao());
            stmt.setDouble(5, historico.getConsumo());
            stmt.setDouble(6, historico.getSaldoEnergetico());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new APIException("Erro ao salvar histórico de energia: " + e.getMessage(), e);
        }
    }

    public void update(HistoricoEnergia historico) {
        String sql = "UPDATE historico_energia SET id_residencia = ?, data_registro = ?, producao = ?, consumo = ?, saldo_energetico = ? WHERE id_historico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, historico.getIdResidencia());
            stmt.setDate(2, new java.sql.Date(historico.getDataRegistro().getTime()));
            stmt.setDouble(3, historico.getProducao());
            stmt.setDouble(4, historico.getConsumo());
            stmt.setDouble(5, historico.getSaldoEnergetico());
            stmt.setInt(6, historico.getIdHistorico());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new APIException("Erro ao atualizar histórico de energia.", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM historico_energia WHERE id_historico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new APIException("Erro ao remover histórico de energia.", e);
        }
    }
}
