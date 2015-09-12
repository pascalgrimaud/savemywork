package fr.pgrimaud.savemywork;

import org.junit.Test;

public class TestSaveMyWork {
    @Test
    public void testMainNoArg() {
        SaveMyWork.main(null);
    }

    @Test
    public void testMainArgEmpty() {
        String args[] = {""};
        SaveMyWork.main(args);
    }

    @Test
    public void testMainBadNumberArgs() {
        String args[] = {"1"};
        SaveMyWork.main(args);
    }

    @Test
    public void testMainBadTime() {
        String args[] = {"aa", "aa"};
        SaveMyWork.main(args);
    }
}