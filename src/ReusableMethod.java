public class ReusableMethod {
    public class ReusableMethods {

    }

    public void myWait(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}

