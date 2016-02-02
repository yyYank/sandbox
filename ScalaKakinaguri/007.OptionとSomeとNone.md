
ふむふむ。
http://yuroyoro.hatenablog.com/entry/20100710/1278763193

とりあえずこれを読んでなんか書いてみる。

## Option

scala.Option

http://www.scala-lang.org/files/archive/nightly/docs/library/index.html#scala.Option

sealedな抽象クラスらしい。

## Some

Optionを継承したなんか値が入ってるやつ。
caseクラスらしい。

## None

Optionを継承した値が入ってないやつ。
case objectなクラス。

## 使い方

パターンマッチが多いと。
match式であれこれするのね。よく見るやつだ。

## コード

    scala> val a = if(true){Some("a") }else{None}
    a:Option[String] = Some(a)


    scala> val b = Some("b")
    b: Some[String] = Some(b)


    scala> val c = None
    c: None.type = None
  

ふむ。Optionはこうやって出来るらしい。
こうやってみるとSomeとNoneとの関係性も分かりやすい。


## Optionの実体から使えるメソッド

    scala> a.
    asInstanceOf   flatMap     isDefined      orNull            toRight      
    canEqual       flatten     isEmpty        productArity      toString     
    collect        fold        isInstanceOf   productElement    withFilter   
    contains       forall      iterator       productIterator                
    exists         foreach     map            productPrefix                  
    filter         get         nonEmpty       toLeft                         
    filterNot      getOrElse   orElse         toList    
    
なるほどなるほど。



## 結局は

ひしだまさんのサイトにたどりついてしまう。

http://www.ne.jp/asahi/hishidama/home/tech/scala/option.html

知りたいことが書かれすぎている。。

