package br.com.fiap.garrovision.infra.dao;

import br.com.fiap.garrovision.dominio.Residencia;
import br.com.fiap.garrovision.exceptions.APIException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResidenciaDAO {

    private final Connection connection;
    ConnectionFactory connectionfactory = new ConnectionFactory();

    public ResidenciaDAO() {
        this.connection = connectionfactory.getConnection();
    }

    public Residencia findById(int id) {
        String sql = "SELECT * FROM residencias WHERE id_residencia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Residencia(
                            rs.getInt("id_residencia"),
                            rs.getString("nome_responsavel"),
                            rs.getString("endereco"),
                            rs.getDouble("capacidade_geracao"),
                            rs.getString("tipo_fonte"),
                            rs.getDouble("limite_consumo"),
                            rs.getDate("data_cadastro")
                    );
                }
            }
        } catch (SQLException e) {
            throw new APIException("Erro ao buscar residência por ID.", e);
        }
        return null;
    }

    public void save(Residencia residencia) throws SQLException {
        String sql = "INSERT INTO residencias (id_residencia, nome_responsavel, endereco, capacidade_geracao, tipo_fonte, limite_consumo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectionfactory.getConnection();
             PreparedStatement seqStmt = conn.prepareStatement("SELECT RESIDENCIA_SEQ.NEXTVAL FROM DUAL");
             ResultSet rs = seqStmt.executeQuery();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (rs.next()) {
                int nextId = rs.getInt(1);
                residencia.setIdResidencia(nextId);
            }

            stmt.setInt(1, residencia.getIdResidencia());
            stmt.setString(2, residencia.getNomeResponsavel());
            stmt.setString(3, residencia.getEndereco());
            stmt.setDouble(4, residencia.getCapacidadeGeracao());
            stmt.setString(5, residencia.getTipoFonte());
            stmt.setDouble(6, residencia.getLimiteConsumo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new APIException("Erro ao salvar residência: " + e.getMessage(), e);
        }
    }

    public List<Residencia> findAll() throws SQLException {
        List<Residencia> residencias = new ArrayList<>();
        String sql = "SELECT * FROM residencias";

        try (Connection conn = connectionfactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Residencia residencia = new Residencia();
                residencia.setIdResidencia(rs.getInt("id_residencia"));
                residencia.setNomeResponsavel(rs.getString("nome_responsavel"));
                residencia.setEndereco(rs.getString("endereco"));
                residencia.setCapacidadeGeracao(rs.getDouble("capacidade_geracao"));
                residencia.setTipoFonte(rs.getString("tipo_fonte"));
                residencia.setLimiteConsumo(rs.getDouble("limite_consumo"));
                residencia.setDataCadastro(rs.getDate("data_cadastro"));
                residencias.add(residencia);
            }
        }
        return residencias;
    }

    public void update(Residencia residencia) throws SQLException {
        String sql = "UPDATE residencias SET nome_responsavel = ?, endereco = ?, capacidade_geracao = ?, tipo_fonte = ?, limite_consumo = ? WHERE id_residencia = ?";

        try (Connection conn = connectionfactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, residencia.getNomeResponsavel());
            stmt.setString(2, residencia.getEndereco());
            stmt.setDouble(3, residencia.getCapacidadeGeracao());
            stmt.setString(4, residencia.getTipoFonte());
            stmt.setDouble(5, residencia.getLimiteConsumo());
            stmt.setInt(6, residencia.getIdResidencia());

            stmt.executeUpdate();
        }
    }

    public void delete(int idResidencia) throws SQLException {
        String sql = "DELETE FROM residencias WHERE id_residencia = ?";

        try (Connection conn = connectionfactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idResidencia);
            stmt.executeUpdate();
        }
    }
}
