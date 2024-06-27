package studentinfo;

public class Performance {
    private int[] tests = {};

    public void setNumberOfTests(int nUmberOfTests) {
        tests = new int[nUmberOfTests];
    }

    public void set(int testNumber, int score){
        tests[testNumber] = score;
    }

    public int get(int testNumber){
        return tests[testNumber];
    }

    public double average(){
        if(tests.length == 0)
            return 0.0;

        int total = 0;
        for (int score: tests)
            total += score;

        return (double)total/tests.length;
    }

    public void setScores(int [] tests) {
       this.tests = tests;
    }
}
