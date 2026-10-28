package dao;

import factory.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Aluno;

public class AlunoDAO {

    public void adiciona(Aluno aluno) {
        String sql = "INSERT INTO alunos(nome, sexo, nota1, nota2, media, situacao, apagado) VALUES (?,?,?,?,?,?,?)";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getSexo());
            stmt.setDouble(3, aluno.getNota1());
            stmt.setDouble(4, aluno.getNota2());
            stmt.setDouble(5, aluno.getMedia());
            stmt.setInt(6, aluno.getSituacao());
            stmt.setInt(7, aluno.getApagado());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Aluno> lerTudo() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alunos WHERE apagado=0";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getLong("cod"));
                aluno.setNome(rs.getString("nome"));
                aluno.setSexo(rs.getInt("sexo"));
                aluno.setNota1(rs.getDouble("nota1"));
                aluno.setNota2(rs.getDouble("nota2"));
                aluno.setMedia(rs.getDouble("media"));
                aluno.setSituacao(rs.getInt("situacao"));
                aluno.setApagado(rs.getInt("apagado"));
                lista.add(aluno);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na leitura do banco: " + ex, "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }

    public void exclui(int cod) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE alunos SET apagado = 1 WHERE cod = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cod);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}