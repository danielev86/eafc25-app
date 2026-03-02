package com.redcatdev86.backend;

import com.redcatdev86.backend.model.IssueEntity;
import com.redcatdev86.backend.model.IssueTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class IssueRepository {

    private final Database database;

    public IssueRepository(Database database) {
        this.database = database;
    }

    public int getMaxId(IssueTable table) throws SQLException {
        String sql = "SELECT MAX(id) AS max_id FROM " + table.tableName();
        try (Connection c = database.openConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            int max = rs.getInt("max_id");
            return rs.wasNull() ? 0 : max;
        }
    }

    public Optional<IssueEntity> findById(IssueTable table, int id) throws SQLException {
        String sql = "SELECT id, issue_type, issue_description FROM " + table.tableName() + " WHERE id = ?";
        try (Connection c = database.openConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();

                int foundId = rs.getInt("id");
                String type = rs.getString("issue_type");
                String desc = rs.getString("issue_description");

                return Optional.of(new IssueEntity(table, foundId, type, desc));
            }
        }
    }

    public int insertIssueWithNextId(IssueTable table, String issueType, String issueDescription) throws SQLException {
        // 1) max id
        int nextId = getMaxId(table) + 1;

        // 2) insert
        String sql = "INSERT INTO " + table.tableName() + " (id, issue_type, issue_description) VALUES (?, ?, ?)";
        try (Connection c = database.openConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, nextId);
            ps.setString(2, issueType);
            ps.setString(3, issueDescription);

            ps.executeUpdate();
            return nextId;
        }
    }

    /**
     * Inserisce N righe identiche, con ID sequenziali (max+1 ... max+N).
     * Ritorna l'ultimo ID inserito.
     */
    public int insertManyIssuesWithNextIds(IssueTable table, String issueType, String issueDescription, int quantity) throws SQLException {
        if (quantity <= 0) return 0;

        int startId = getMaxId(table) + 1;

        String sql = "INSERT INTO " + table.tableName() + " (id, issue_type, issue_description) VALUES (?, ?, ?)";
        try (Connection c = database.openConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            for (int i = 0; i < quantity; i++) {
                int id = startId + i;
                ps.setInt(1, id);
                ps.setString(2, issueType);
                ps.setString(3, issueDescription);
                ps.addBatch();
            }
            ps.executeBatch();
        }

        return startId + quantity - 1;
    }
}