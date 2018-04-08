package org.lee.design.mediator;

import java.sql.Statement;


/**
 * ＵＮとＵＮ加入国
 * 1. ＵＮ加入国はＵＮへメッセージを送信する
 * 2. ＵＮは国レベルでメッセージを転送する
 */
public class Main {

    public static void main(String[] args) {
        StateMediator mediator = new UN();

        StateColleague china = new China(mediator);
        StateColleague usa = new USA(mediator);
        StateColleague korea = new Korea(mediator);
        StateColleague stateA = new StateA(mediator);

        // mediator管理下にcolleague配置
        mediator.addState(china);
        mediator.addState(usa);
        mediator.addState(korea);
        mediator.addState(stateA);

        china.send("This is china.");
        System.out.println("-------");

        usa.send("This is usa.");
        System.out.println("-------");

        korea.send("This is korea.");
        System.out.println("-------");

        stateA.send("This is state-a.");
        System.out.println("-------");
    }
}
