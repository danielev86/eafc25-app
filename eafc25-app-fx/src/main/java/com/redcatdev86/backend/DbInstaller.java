package com.redcatdev86.backend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.*;

public final class DbInstaller {

    private static final String RESOURCE_DB_PATH = "/db/eafc_issues.db";
    private static final String DB_FILE_NAME = "eafc_issues.db";

    private DbInstaller() {}

    public static Path installIfMissing() throws IOException {
        Path installRoot = resolveInstallRoot();

        Path dbDir = installRoot.resolve("db");
        Path dbPath = dbDir.resolve(DB_FILE_NAME);

        Files.createDirectories(dbDir);

        if (!Files.exists(dbPath)) {
            try (InputStream is = DbInstaller.class.getResourceAsStream(RESOURCE_DB_PATH)) {
                if (is == null) {
                    throw new FileNotFoundException("Resource DB non trovato: " + RESOURCE_DB_PATH);
                }
                Files.copy(is, dbPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }

        return dbPath;
    }

    private static Path resolveInstallRoot() {
        // jpackage: path assoluto dell’exe
        String jpAppPath = System.getProperty("jpackage.app-path");
        if (jpAppPath != null && !jpAppPath.isBlank()) {
            return Paths.get(jpAppPath).getParent();
        }

        // fallback: cartella del jar / classpath
        try {
            Path codeSource = Paths.get(DbInstaller.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI());

            // se è un jar: .../EAFCIssueGenerator/app/app.jar -> root = parent(parent)
            if (Files.isRegularFile(codeSource) && codeSource.toString().toLowerCase().endsWith(".jar")) {
                Path appDir = codeSource.getParent(); // .../app
                Path root = appDir != null ? appDir.getParent() : null;
                return root != null ? root : appDir;
            }

            // IDE: usa user.dir
            return Paths.get(System.getProperty("user.dir")).toAbsolutePath();

        } catch (URISyntaxException e) {
            return Paths.get(System.getProperty("user.dir")).toAbsolutePath();
        }
    }
}