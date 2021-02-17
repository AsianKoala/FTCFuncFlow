package control;

public class Functions {
    public interface function {
        Results.baseResult result();
    }

    public interface hardwareFunction extends function {
        Results.simpleResult runHardware();
    }

    public interface testResult extends function {
        Results.testResult runTest();
    }
}


