package org.lee.thread;

public class DaemonThread {

    // setDaemon(true)はstart()も前にする
    // DaemonのサブはDaemon


    // sleep() 現在のスレッドをブロック状態にする
    // ＣＰＵが空いても実行しない

    // yield(), ready状態にする
    // 現在のスレッドよりpriorityが同等が高いものが実行される
}
