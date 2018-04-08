import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class SampleATest {

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    private SampleB sampleB;

    @InjectMocks
    private SampleA sampleA = new SampleA();

    @Test
    public void methodA_一般() throws Exception {
        sampleA = new SampleA();

        assertThat(sampleA.method(), is(true));
    }

    @Test
    public void methodA_mock() throws Exception {
        when(sampleB.getList(anyBoolean()))
            .thenReturn(Arrays.asList("1", "2"))
            .thenReturn(Collections.<String>emptyList());

        assertFalse(sampleA.method());
        assertTrue(sampleA.method());
    }
}