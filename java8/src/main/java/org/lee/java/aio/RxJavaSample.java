package org.lee.java.aio;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

public class RxJavaSample {


    public static void main(String[] args) {

        Observable.range(1, 10)
            .filter(i -> i % 2 == 0)
            .map(i -> i * 10)
            .subscribe(new Observer<Integer>() {
                @Override
                public void onNext(Integer integer) {
                    System.out.println(integer.toString());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Completed");
                }

                @Override
                public void onError(Throwable e) {
                    System.out.println(e.getMessage());
                }
            });
    }

}
