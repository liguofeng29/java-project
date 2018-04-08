import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SampleB {

    public List<String> getList(boolean isEmpty) {

        if (isEmpty) {
            return Arrays.asList("a", "b", "c");
        } else {
            return Collections.<String>emptyList();
        }

    }
}
