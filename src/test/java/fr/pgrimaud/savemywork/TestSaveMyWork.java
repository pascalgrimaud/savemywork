package fr.pgrimaud.savemywork;

import org.junit.Assert;
import org.junit.Test;

public class TestSaveMyWork {

    @Test
    public void testCheckArgsNull() {
        Assert.assertFalse(SaveMyWork.checkArgs(null));
    }

    @Test
    public void testCheckArgsEmpty() {
        String args[] = {""};
        Assert.assertFalse(SaveMyWork.checkArgs(args));
    }

    @Test
    public void testCheckArgsBadLength() {
        String args[] = {"1"};
        Assert.assertFalse(SaveMyWork.checkArgs(args));
    }

    @Test
    public void testCheckArgsBadTime() {
        String args[] = {"aa", "aa"};
        Assert.assertFalse(SaveMyWork.checkArgs(args));
    }

    @Test
    public void testCheckArgsBadFile() {
        String args[] = {"1", "test.doc"};
        Assert.assertFalse(SaveMyWork.checkArgs(args));
    }

    @Test
    public void testCheckArgsBadSecondFile() {
        String args[] = {"1", "./src/test/resources/tmp/test.txt", "test.txt"};
        Assert.assertFalse(SaveMyWork.checkArgs(args));
    }

    @Test
    public void testCheckArgsCorrect() {
        String args[] = {"1", "./src/test/resources/tmp/test.txt"};
        Assert.assertTrue(SaveMyWork.checkArgs(args));
    }
}