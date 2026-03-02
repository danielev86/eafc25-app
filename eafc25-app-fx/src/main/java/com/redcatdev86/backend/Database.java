package com.redcatdev86.backend;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {

    private final Path dbPath;

    public Database(Path dbPath) {
        this.dbPath = dbPath;
    }

    public static Database fromInstalledDb() {
        try {
            Path path = DbInstaller.installIfMissing();
            return new Database(path);
        } catch (IOException e) {
            throw new RuntimeException("Impossibile installare/caricare DB", e);
        }
    }

    public Connection openConnection() throws SQLException {
        String url = "jdbc:sqlite:" + dbPath.toAbsolutePath();
        return DriverManager.getConnection(url);
    }

    public Path getDbPath() {
        return dbPath;
    }
}