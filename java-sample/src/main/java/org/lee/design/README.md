# 基礎
## simple factory pattern
simple factoryは、どのオブジェクトを生成するかのロジックはfactory側が持つ

ＯＣ原則に反する。

## Iterator



## Adapter



# サブクラスに任せる
## Template Method
基底クラスで処理枠（テンプレート）を定義し、サブクラスで具体的な処理を実装する。

1. Factory methodは典型的なTemplate Methodの一つ
2. Strategyは、委譲による処理置き換え、Template Methodは継承による処理置き換え


# インスタンスを作る
## Singleton
https://qiita.com/liguofeng29/items/c653e9b82d3a7e9656c2

## prototype


## Builder



## factory method pattern
factory methodは、どのオブジェクトを生成するかの実行は使用側が持つ


## Abstract Factory



# 分けて考える
## Bridge


## Strategy


# 同一視
## Composite



## Decorator




# 構造を渡り歩く
## Visitor
データ構造と処理(visitor)を分離する。

1. visitorはelementをvisit()し、elementはvisitorをaccept()する
⇒　double dispatch
2. visitor(処理)を追加するのは良い
3. acceptor(データ構造)を追加するのは難しい
4. acceptorはvisitorに必要な情報を公開する必要があります。

## Chain of responsibility
要求(request)と処理(handler)の結合度を弱くする

1. 自分の仕事の集中する、処理できなかったら次の人に回せる
2. リソースをつかったり、遅かったり可能性はあるが、トレードオフの問題


# シンプルにする
## Facade

## Mediator
複数のオブジェクト間のやり取りをまとめるMediator。
処理が集中されるのがメリットでもあり、デメリットでもある。

Colleagueは再利用、拡張しやすいが、Mediatorは再利用が難しい。

関連パタンーん：
Observer - Mediatorの通知仕組みに使われることがある
Facade - Facadeは一方向、Mediatorは双方向である


# 状態を管理する
## Observer
観察される対象が変更されると観察者に通知し、変化に対応する処理を行う。
観察パターンと書いてあるが、実は通知パターン。

関連パターン：
Mediator - Mediatorに通知する仕組みとしてobserverが使われる場合がある


## Memento



## State



# 無駄をなくす
## flyweight
同じものは共有させる。

1. なにを共有させるかは慎重に選ぶ
2. intrinsic（本質的な） -> 共有させる情報
3. extrinsic（非本質的な）-> 共有させない情報

## proxy
必要な時に生成する(遅延生成)

# クラスを表現する
## Command


## Interpreter










