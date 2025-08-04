package org.example.clinic_system.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class BackupJob {

    // Ajusta estas variables según tu entorno
    private static final String MYSQLDUMP_PATH = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DATABASE = "system_clinic";
    private static final String OUTPUT_DIR = "C:\\backups\\";

    @Scheduled(cron = "*/15 * * * * *")
    public void generarBackup() {
        // Crear nombre del archivo con la fecha y hora
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String backupFile = OUTPUT_DIR + "backup_" + timestamp + ".sql";

        // Crear comando
        String comando = "\"" + MYSQLDUMP_PATH + "\" -u " + USER + " -p" + PASSWORD + " " + DATABASE + " -r \""
                + backupFile + "\"";

        try {
            // Crear carpeta si no existe
            new File(OUTPUT_DIR).mkdirs();

            Process proceso = Runtime.getRuntime().exec(comando);
            int estado = proceso.waitFor();

            if (estado == 0) {
                System.out.println("[✔] Backup creado: " + backupFile);
            } else {
                System.err.println("[✖] Error al generar el backup.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}