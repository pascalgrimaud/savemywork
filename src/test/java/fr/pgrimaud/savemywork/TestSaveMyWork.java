package fr.pgrimaud.savemywork;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.security.Permission;

public class TestSaveMyWork {

    protected static class ExitException extends SecurityException
    {
        public final int status;
        public ExitException(int status)
        {
            super("There is no escape!");
            this.status = status;
        }
    }

    private static class NoExitSecurityManager extends SecurityManager
    {
        @Override
        public void checkPermission(Permission perm)
        {
            // allow anything.
        }
        @Override
        public void checkPermission(Permission perm, Object context)
        {
            // allow anything.
        }
        @Override
        public void checkExit(int status)
        {
            super.checkExit(status);
            throw new ExitException(status);
        }
    }

    @Before
    public void setUp() {
        System.setSecurityManager(new NoExitSecurityManager());
    }

    @Test(expected = TestSaveMyWork.ExitException.class)
    public void testMainNoArg() {
        SaveMyWork.main(null);
        Assert.fail("Should exit");
    }

    @Test(expected = TestSaveMyWork.ExitException.class)
    public void testMainArgEmpty() {
        String args[] = {""};
        SaveMyWork.main(args);
        Assert.fail("Should exit");
    }

    @Test(expected = TestSaveMyWork.ExitException.class)
    public void testMainBadNumberArgs() {
        String args[] = {"1"};
        SaveMyWork.main(args);
        Assert.fail("Should exit");
    }

    @Test(expected = TestSaveMyWork.ExitException.class)
    public void testMainBadTime() {
        String args[] = {"aa","aa"};
        SaveMyWork.main(args);
        Assert.fail("Should exit");
    }

    @Test(expected = TestSaveMyWork.ExitException.class)
    public void testMainBadTimeToZero() {
        String args[] = {"0","aa"};
        SaveMyWork.main(args);
        Assert.fail("Should exit");
    }

    @Test(expected = TestSaveMyWork.ExitException.class)
    public void testMainBadFile() {
        String args[] = {"1","/aaa.txt"};
        SaveMyWork.main(args);
        Assert.fail("Should exit");
    }

    @Test(expected = TestSaveMyWork.ExitException.class)
    public void testMainGoodAndBadFile() {
        String args[] = {"1",".//test//resources//tmp//test.txt",".//test//resources//tmp//test2.txt"};
        SaveMyWork.main(args);
        Assert.fail("Should exit");
    }

    @Test(expected = TestSaveMyWork.ExitException.class)
    public void testDisplaySyntax() {
        SaveMyWork.displaySyntax();
        Assert.fail("Should exit");
    }

    @Test
    @Ignore
    public void testMain() {
        String args[] = {"1",".//test//resources//tmp//test.txt"};
        SaveMyWork.main(args);
        Assert.fail("Should exit");
    }
}