import java.util.List;

public class SampleA {


    private SampleB sampleB = new SampleB();

    public boolean method() {
        List<String> list = sampleB.getList(false);
        return list.isEmpty();
    }
}
