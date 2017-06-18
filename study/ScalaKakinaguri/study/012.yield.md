
たしかyieldなるものがあったと記憶している。


## どういうものか

http://www.ne.jp/asahi/hishidama/home/tech/scala/for.html#h_for_yield


なんとなくは覚えていたのだけどfor文処理を評価して値を返す…って感じなのかな。

なんとも、このyieldってやつは説明がしにくいというか、なんとなくスッキリしないところがある。

## メリット的なものは？

Javaと比較して考えるなら、
たぶんforブロックの外に変数宣言する必要がないとか。
そういうお得感はあるのかもしれない。

あとは型推論で柔軟な対応をとってくれるとかそんな感じだろうか。

## 公式ドキュメント

http://docs.scala-lang.org/tutorials/FAQ/yield.html

yieldはRubyやPythonのそれとは違う、と書いてある。

同じキーワードでありながら違うのか。
Ruby、Pythonあたり学ぶときに混乱するのかもな。
たしかにPythonにもyieldがあった気がする。


さて引用。


    Scala’s “for comprehensions” are equivalent to Haskell’s “do” notation, and it is nothing more than a syntactic sugar for composition of multiple monadic operations.


Haskellのdoを真似てて、モナディックな処理にはこの上ないシンタックスシュガーだろう、とな。
そんな便利なのかな。



## コード


    for(x <- c; if cond) yield {...}


is translated into



    c.withFilter(x => cond).map(x => {...})


なるほど、変換してくれるのね。


## そもそもyieldってどういう意味？


http://ejje.weblio.jp/content/yield


* 〈作物・製品などを〉産する.
* 〈利子・収益などを〉もたらす，生む.
* 〈結果などを〉引き起こす.


なるほど、なんとなく分かる気もする。
