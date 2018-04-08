import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class MockitoTest {

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    // base class, test runner, 初期化が必要
    @Mock
    private List<String> listString;

    @Test
    public void スタブを作成と戻り値を設定する() {
        // mock作成
        List<String> mockedList = mock(List.class);

        when(mockedList.get(anyInt()))
            .thenReturn("1回目戻り値", "2回目戻り値") // 連続で書ける
            .thenReturn("3回目以降戻り値");

        assertThat("1回目", mockedList.get(0), is("1回目戻り値"));
        assertThat("2回目", mockedList.get(1), is("2回目戻り値"));
        assertThat("3回目", mockedList.get(2), is("3回目以降戻り値"));
        assertThat("4回目", mockedList.get(99), is("3回目以降戻り値"));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    // @Test(expected=RuntimeException.class)
    public void 例外をテストする() {
        // mock作成
        List<String> mockedList = mock(List.class);

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("a");

        when(mockedList.get(anyInt()))
            .thenThrow(new RuntimeException("a"));

        mockedList.get(1);
    }

    @Test
    public void 引数の一致() {
        // mock作成
        List<String> mockedList = mock(List.class);

        when(mockedList.get(anyInt())).thenReturn("1");

        // 下のほうが上書きされるっぽい
        // anyIntの上に移動すると、anyIntに上書きされる
        when(mockedList.get(99)).thenReturn("99");

        assertThat("1", mockedList.get(0), is("1"));
        assertThat("2", mockedList.get(99), is("99"));
        assertThat("99", mockedList.get(1), is("1"));
    }

    @Test
    public void 振る舞いの検証する() {
        // mock作成
        List<String> mockedList = mock(List.class);

        listString.add("1");

        mockedList.add("1");
        mockedList.add("2");
        mockedList.clear();
        mockedList.add("3");
        mockedList.add("4");

        // メソッドが呼ばれたことを検証する
        // 順番は関係ない
        verify(mockedList).clear();
        verify(mockedList).add("2");
        verify(mockedList).add("1");
    }

    @Test
    public void 呼び出し回数を検証する() {
        // mock作成
        List<String> mockedList = mock(List.class);

        mockedList.add("1");
        mockedList.add("2");
        mockedList.add("2");
        mockedList.add("3");
        mockedList.add("3");
        mockedList.add("3");

        // 呼び出し回数
        verify(mockedList).add("1");
        verify(mockedList, times(1)).add("1");
        verify(mockedList, times(2)).add("2");
        verify(mockedList, times(3)).add("3");

        // 2回以上
        verify(mockedList, atLeast(2)).add("2");

        // 3回以下
        verify(mockedList, atMost(3)).add("3");

        // 呼び出されてない
        verify(mockedList, never()).clear();
        verify(mockedList, never()).add("99");
    }

    @Test
    public void 呼び出し順序を検証する() {
        // mock作成
        List<String> mockedList = mock(List.class);

        mockedList.add("1");
        mockedList.add("2");
        mockedList.add("3");

        InOrder inOrder = inOrder(mockedList);

        // 順序検証
        inOrder.verify(mockedList).add("1");
        inOrder.verify(mockedList).add("2");
        inOrder.verify(mockedList).add("3");
    }

    @Test
    public void モックが作用してないことを検証する() {
        // mock作成
        List<String> mockedList1 = mock(List.class);
        List<String> mockedList2 = mock(List.class);
        List<String> mockedList3 = mock(List.class);

        mockedList1.add("1");

        // 次の作用は一度も無かったことを検証
        verify(mockedList2, never()).add("2");
        verify(mockedList3, never()).add("3");

        // 作用してないモック
        verifyZeroInteractions(mockedList2, mockedList3);
        verifyNoMoreInteractions(mockedList2, mockedList3);
    }

    @Test
    public void モックタイムアウトを検証する() {
        // mock作成
        List<String> mockedList = mock(List.class);

        when(mockedList.size()).thenReturn(0);

        mockedList.size();
        mockedList.size();

        // 2秒内に2回呼ばれた
        verify(mockedList, timeout(2000).times(2)).size();
    }

    @Test
    public void answerの実装() {

        when(listString.get(1)).thenAnswer(invocation -> {
                System.out.println(invocation);
                return "99";
            }
        );

        assertThat("99", listString.get(1), is(99));
    }

    @Test(expected = RuntimeException.class)
    public void voidメソッドのmock化() {
        doReturn(true).when(listString).isEmpty();
        assertTrue(listString.isEmpty());

        doThrow(new RuntimeException()).when(listString).clear();
        listString.clear();

        doNothing().when(listString).clear();
        listString.clear();

        doAnswer(invocationOnMock -> 99).when(listString).get(1);
        assertThat(listString.get(1), is(99));
    }

    @Test
    public void リアルオブジェクトSPY() {
        List<Integer> spiedList = spy(new ArrayList<Integer>());

        when(spiedList.size()).thenReturn(99);

        spiedList.add(1);
        spiedList.add(2);

        assertThat(spiedList.size(), is(99));

        verify(spiedList).add(1);
        verify(spiedList).add(2);
        verify(spiedList).size();
    }

    @Test
    public void スタブ化されたないメソッドのデフォルト値戦略() {
        List<Integer> mockedList = mock(List.class, Mockito.RETURNS_SMART_NULLS);
        assertFalse(mockedList.isEmpty());
    }

    @Test
    public void リアルメソッド呼び出し() {
        List<Integer> mockedList = mock(ArrayList.class);

        when(mockedList.size()).thenCallRealMethod();
        mockedList.add(1);

        // リアル呼び出し
        assertThat(mockedList, is(0));
    }

    @Test
    public void リセット() {
        List<Integer> mockedList = mock(ArrayList.class);
        when(mockedList.size()).thenReturn(99);

        assertThat(mockedList.size(), is(99));

        reset(mockedList);
        assertThat(mockedList.size(), is(0));
    }

    @Test
    public void serializableMocks() {
        List list = new ArrayList();
        List spy = mock(ArrayList.class, withSettings()
            .spiedInstance(list)
            .defaultAnswer(CALLS_REAL_METHODS)
            .serializable());
    }


    @Test
    public void 引数キャプチャ() {
        List<Integer> mockedList = mock(List.class);
        mockedList.add(1);
        mockedList.add(2);

        // Integer引数
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);

        verify(mockedList, times(2)).add(argument.capture());

        assertThat(argument.getAllValues().get(0), is(1));
        assertThat(argument.getAllValues().get(1), is(2));
    }
}


