package org.lee.java.demo1;

import com.sun.javafx.binding.StringFormatter;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * ラムダ式
 */
public class Demo1 {
    public static void main(String[] args) {

        // なぜラムダ式は必要なのか
        demo1();

        // ラムダ式の書き方
        demo2();

        // ラムダ式の参照は値参照
        demo3();

        // javaの定義済み functional interface
        demo4();


        // まとめ
        // 1. ラムダ式は、匿名メソッド
        // 2. 本質は、@FunctionalInterface, 一つの抽象メソッドを持つインタフェース
    }

    private static void demo1() {
        System.out.println("---- 従来の書き方とラムダ式 ----");
        // 従来の書き方
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("別スレッドでこの行為を実行します。");
            }
        }).start();

        // どうゆう問題があるのか？
        // 1. シンプルでない、可読性がよくない。
        //    出力以外は無駄なコードである。
        // 2. 意図が正しく伝わってない。
        //    スレッドに渡したいのは、行為であり、オブジェクトではない。

        // ラムダ式で書いてみよう
        // オブジェクトではなくて、行為を渡す
        new Thread(() -> {
            System.out.println("別スレッドで行為1を実行します。");
        }).start();
    }

    private static void demo2() {
        System.out.println("---- ラムダ式の色んな書き方 ----");
        // 引数なし
        Runnable runnable1 = () -> System.out.println("引数なし");
        runnable1.run();

        // 複数ステートメント
        Runnable runnable2 = () -> {
            System.out.println("引数なし");
            System.out.println("複数ステートメント");
        };
        runnable2.run();

        // 引数一つ
        Consumer<String> consumer = arg1 -> System.out.println("引数一つ : " + arg1);
        consumer.accept("value1");

        // 引数二つ & 型推理
        BinaryOperator<String> binaryOperator1 = (arg1, arg2) -> "引数二つ連結：" + arg1 + arg2;
        System.out.println(binaryOperator1.apply("param1", "param2"));

        // 引数二つ & 型指定
        BinaryOperator<Long> binaryOperator2 = (Long arg1, Long arg2) -> arg1 + arg2;
        System.out.println(binaryOperator2.apply(10L, 20L));
    }

    private static void demo3() {
        System.out.println("---- 値参照 ----");
        String outer = "outerString";

        // 値参照
        Runnable runnable = () -> System.out.println("外部変数 : " + outer) ;
        demo3Sub(runnable);

        // エラー：ラムダ式から参照されるローカル変数は、finalまたは事実上のfinalである必要があります
        // outer = "change to something will be error";

    }

    private static void demo3Sub(Runnable runnable) {
        String outer = "demo3Sub";
        runnable.run();
    }

    private static void demo4() {
        //  IF                引数     戻り値
//        Predicate<T>        T       boolean
//        Consumer<T>         T       void
//        Function<T,R>       T       R
//        Supplier<T>         None    T
//        UnaryOperator<T>    T       T
//        BinaryOperator<T>   (T, T)  T
    }
}

