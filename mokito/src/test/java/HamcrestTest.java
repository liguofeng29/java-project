import lombok.Builder;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.CoreMatchers.theInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.object.HasToString.hasToString;

public class HamcrestTest {

    @Test
    public void aboutObject() {
        // Object#equalsで比較
        assertThat(asList(1, 2), is(asList(1, 2)));
        // objectがnullでない
        assertThat(asList(1, 2), notNullValue());
        assertThat(asList(1, 2), notNullValue(List.class));
        // objectがnullである
        assertThat(null, nullValue());

        // 同じインスタンスか
        List<Integer> list = Arrays.asList(1, 2);
        assertThat(list, sameInstance(list));
        assertThat(asList(1, 2), is(not(sameInstance(asList(1, 2)))));
        assertThat(list, theInstance(list));

        // インスタンスの型
        assertThat(asList(1, 2), instanceOf(List.class));
        assertThat(asList(1, 2), instanceOf(Collection.class));
        assertThat(asList(1, 2), isA(List.class));
        assertThat(asList(1, 2), isA(Collection.class));
        assertThat(asList(1, 2), any(List.class));

        // object.toString()を比較
        assertThat(asList(1, 2), hasToString("[1, 2]"));
        assertThat(asList(1, 2), hasToString(not("[2, 3]")));

        // 継承関係
        assertThat(ArrayList.class, is(typeCompatibleWith(List.class)));
        assertThat(List.class, not(typeCompatibleWith(ArrayList.class)));

        // eventFrom
    }

    @Test
    public void aboutString() {
        // aから始まる
        assertThat("abc", startsWith("a"));
        // aを含む
        assertThat("abc", containsString("a"));
        // eを含まない
        assertThat("abc", is(not(containsString("e"))));
        // ｃで終わる
        assertThat("abc", endsWith("c"));
        // 空白
        assertThat("", isEmptyString());
        // 空白 OR null
        assertThat("", isEmptyOrNullString());
        // null
        assertThat(null, isEmptyOrNullString());
        // 前後スペース除いて比較
        assertThat(" abc ", equalToIgnoringWhiteSpace("abc"));
        // 大文字、小文字無視して比較
        assertThat("ABC", equalToIgnoringCase("abc"));
        // 文字順（aの後にc）
        assertThat("abc", stringContainsInOrder(asList("a", "c")));
        assertThat("abc", not(stringContainsInOrder(asList("c", "a"))));
    }

    @Test
    public void aboutNumber() {

        assertThat(1, comparesEqualTo(1));
        assertThat(1, lessThan(2));
        assertThat(1, lessThanOrEqualTo(2));
        assertThat(1, lessThanOrEqualTo(1));
        assertThat(1, greaterThan(0));
        assertThat(1, greaterThanOrEqualTo(1));

        // |n−operand|≦error
        assertThat(Double.valueOf("1"), closeTo(2, 1.0));
    }

    @Data
    @Builder
    public static class Bean {
        private String a;
        private String b;
    }

    @Test
    public void aboutBean() {
        Bean bean = new Bean("aValue", "bValue");

        assertThat(bean, hasProperty("a"));
        assertThat(bean, hasProperty("b"));

        assertThat(bean, hasProperty("a", is("aValue")));
        assertThat(bean, hasProperty("b", is("bValue")));

        Bean beanB = new Bean("aValue", "bValue");
        assertThat(bean, samePropertyValuesAs(beanB));
    }
}
