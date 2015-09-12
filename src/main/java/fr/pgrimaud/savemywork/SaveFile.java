package fr.pgrimaud.savemywork;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Classe permettant de réaliser des sauvegardes régulières automatique
 *
 * @author pgrimaud
 */
public class SaveFile implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap data = context.getJobDetail().getJobDataMap();
        String[] args = (String[]) data.get("args");

        String format = "yyyyMMdd-HHmmss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);

        for (int i = 1; i < args.length; i++) {
            String fichier = args[i];
            String dateString = formater.format(new Date());
            String fichierDest = fichier + '.' + dateString;
//            File testFichier = new File(fichier);
            Path base = Paths.get(fichier);
            if (Files.exists(base)) {

                Path dest = Paths.get(fichierDest);
                try {
                    Files.copy(base, dest);
                    System.out.println("Copy : " + fichierDest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error - The file doesn't exist : " + args[i]);
            }
        }
    }
}
