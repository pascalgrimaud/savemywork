package fr.pgrimaud.savemywork;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveMyWork {

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        if (!checkArgs(args)) {
            displaySyntax();
            System.exit(0);
        }
        startSaveMyWork(args, Integer.valueOf(args[0]));
    }

    /**
     * Method to check the arguments
     *
     * @param args
     * @return true if the arguments are correct
     */
    public static boolean checkArgs(String[] args) {
        if (args != null && args.length >= 2 ) {
            try {
                Integer.valueOf(args[0]);
                for (int i = 1; i < args.length; i++) {
                    if (!Files.exists(Paths.get(args[i]))) {
                        return false;
                    }
                }
            } catch (NumberFormatException ex) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Method to display the syntax
     */
    public static void displaySyntax() {
        System.out.println("Syntax :");
        System.out.println("  java -jar SaveMyWork.jar <time in minutes> <file1> [file2] [...]");
        System.out.println("Examples :");
        System.out.println("  java -jar SaveMyWork.jar 10 /home/dev/test.doc /home/dev/test.xls");
    }

    /**
     * Method to start the save job
     *
     * @param args
     * @param time
     */
    public static void startSaveMyWork(String[] args, Integer time) {
        System.out.println("Starting SaveMyWork...");
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler scheduler = sf.getScheduler();

            // define the job
            JobDetail job = JobBuilder.newJob(SaveFile.class).withIdentity("job", "group").build();
            job.getJobDataMap().put("args", args);
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(time * 60).repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}