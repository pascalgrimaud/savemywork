package fr.pgrimaud.savemywork;

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
 * Class to auto save files
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
            String file = args[i];
            String dateString = formater.format(new Date());
            String fileDest = file + '.' + dateString;

            Path base = Paths.get(file);
            if (Files.exists(base)) {
                Path dest = Paths.get(fileDest);
                try {
                    Files.copy(base, dest);
                    System.out.println("Copy : " + fileDest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error - The file doesn't exist anymore : " + args[i]);
            }
        }
    }
}
