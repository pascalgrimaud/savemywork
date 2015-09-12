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

public class SaveMyWork {

    public static void main(String[] args) {
        if (args != null && args.length >= 2 && args[0] != null && args[1] != null) {
            // check the first argument
            try {
                Integer time = Integer.valueOf(args[0]);
                startSave(args, time);
            } catch (NumberFormatException ex) {
                displaySyntax();
            }
        } else {
            displaySyntax();
        }
    }

    public static void startSave(String[] args, Integer time) {
        System.out.println("Starting SaveMyWork...");
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler scheduler = sf.getScheduler();

            // define the job
            JobDetail job = JobBuilder.newJob(SaveFile.class)
                    .withIdentity("job", "group").build();
            job.getJobDataMap().put("args", args);
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(time * 60).repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
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
}