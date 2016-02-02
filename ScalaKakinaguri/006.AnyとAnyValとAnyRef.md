
## scala.Any

JavaでいうところのObjectクラス。


## scala.AnyVal

Javaでいうところのプリミティブ型らしい。値の参照的な。
scala.Anyを継承する。

## scala.AnyRef

参照型を表す。
scala.Anyを継承する。

## 出典

http://www.ne.jp/asahi/hishidama/home/tech/scala/any.html


## もうちょっと深堀りしたい

と思って調べてるとここにたどり着いた。

http://stackoverflow.com/questions/2335319/what-are-the-relationships-between-any-anyval-anyref-object-and-how-do-they-m

Any、AnyVal、AnyRefはbytecodeには現れないと書いてある。
で、なんだかオートボクシングのタイミングが違うとかなんとか。
時間あるときにちゃんと読もう。


これら3つのクラスはScalaの世界で全てをクラスオブジェクトとして扱うみたいな試みという感じがしてきた。
