名前は聞く。けど難しそう。

## implicit

暗黙という意味。

せらさんがblog記事を書いていた。
http://seratch.hatenablog.jp/entry/20110502/1304320277

Scalaではimplicit parameter とかimplicit conversionとかという言葉がよく使われるみたい。

暗黙の型変換、暗黙のパラメーター。

## ソース読んでみる

Periodってなんだ？と思ったら
Joda  timeのクラスか
http://joda-time.sourceforge.net/apidocs/org/joda/time/Period.html


DateTimeWithActiveSupportというクラスで演算子オーバーロードをしてJoda timeのplusとminusを渡すようにしている。

クラスのインスタンス生成をimplicitなメソッドで行っている。
なんだか繋がりが難しい。

 PeriodWithActiveSupportに関しても、Periodというクラスの操作をくるんでmatch式でなんかやってる。

このクラスの生成もimplicitにすることで
int値にビリオドをチェインしてこのクラスを暗黙的に呼び出す。みたいな感じかなぁ。むずかしー。
