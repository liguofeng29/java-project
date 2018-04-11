import lombok.Builder;
import lombok.Data;
import org.junit.Test;

import java.util.*;

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

    @Test
    public void aboutCollection() {

        assertThat(Arrays.asList(1, 2, 3), hasItem(1));
        assertThat(Arrays.asList(1, 2, 3), hasItem(greaterThan(2)));
        assertThat(Arrays.asList(1, 2, 3), hasItems(1, 2, 3));
        assertThat(Arrays.asList(1, 2, 3), hasItems(greaterThan(2)));

        assertThat(Arrays.asList(1, 2, 3), everyItem(lessThan(4)));

        assertThat(1, isIn(asList(1, 2, 3)));

        assertThat(Arrays.asList(1, 2, 3), isOneOf(asList(1, 2, 3), asList(4, 5, 6)));

        assertThat(Arrays.asList(1, 2, 3), not(empty()));

        assertThat(Arrays.asList(1, 2, 3), not(emptyCollectionOf(Integer.class)));

        assertThat(Arrays.asList(1, 2, 3), hasSize(3));
        assertThat(Arrays.asList(1, 2, 3), hasSize(greaterThan(2)));

        assertThat(Arrays.asList(1, 2, 3), not(emptyIterable()));
        assertThat(Arrays.asList(1, 2, 3), iterableWithSize(3));
        assertThat(Arrays.asList(1, 2, 3), iterableWithSize(greaterThan(2)));
        assertThat(Arrays.asList(1, 2, 3), contains(1, 2, 3));
        assertThat(Arrays.asList(1, 2, 3),
            contains(comparesEqualTo(1), comparesEqualTo(2), comparesEqualTo(3)));

        assertThat(Arrays.asList(1, 2, 3), containsInAnyOrder(3, 2, 1));
        assertThat(Arrays.asList(1, 2, 3),
            containsInAnyOrder(comparesEqualTo(3), comparesEqualTo(2), comparesEqualTo(1)));
    }

    @Test
    public void aboutArray() {
        Integer[] intArray = {1, 2, 3};

        assertThat(intArray, not(emptyArray()));

        assertThat(intArray, arrayWithSize(3));
        assertThat(intArray, arrayWithSize(greaterThan(2)));

        assertThat(intArray, is(array(is(1), is(2), is(3))));

        assertThat(intArray, hasItemInArray(3));

        assertThat(intArray, arrayContaining(1, 2, 3));

        assertThat(intArray, arrayContainingInAnyOrder(1, 2, 3));
    }

    @Test
    public void aboutMap() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "aa");
        map.put("b", "bb");
        map.put("c", "cc");

        assertThat(map, hasEntry("a", "aa"));
        assertThat(map, hasEntry("b", "bb"));
        assertThat(map, hasEntry("c", "cc"));

        assertThat(map, hasKey("a"));
        assertThat(map, hasKey("b"));
        assertThat(map, hasKey("c"));
        assertThat(map, hasKey(startsWith("b")));
        assertThat(map, not(hasKey(startsWith("d"))));

        assertThat(map, hasValue("aa"));
        assertThat(map, hasValue("bb"));
        assertThat(map, hasValue("cc"));
        assertThat(map, hasValue(endsWith("a")));
        assertThat(map, not(hasValue(startsWith("d"))));
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

    @Test
    public void aboutLogicalOperation() {
        assertThat(1, anything());
        assertThat(1, not(2));
        assertThat(1, not(is(2)));

        assertThat(1, allOf(comparesEqualTo(1), comparesEqualTo(1)));
        assertThat(1, anyOf(comparesEqualTo(1), comparesEqualTo(2)));

        assertThat("JUnit", either(is("JUnit"))
            .or(is("ScalaTest"))
            .or(is("Spock")));
    }
}
