import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

public class BDDMockitoTest {

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    private List<Integer> mockedList;

    @Test
    public void a() {
        // given
        given(mockedList.size()).willReturn(99);

        // when
        int size;
        size = mockedList.size();
        size = mockedList.size();

        // then
        then(mockedList)
            .should(times(2))
            .size();

        assertThat(size, is(99));
    }
}
