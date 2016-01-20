
よく聞くやつ。LeftとかRightとか。

# scala.util.Either

らしい。
どうやら例外処理で利用するのが多いのか。

http://yuroyoro.hatenablog.com/entry/20100719/1279519961


## とりあえず公式ドキュメントっぽいものを

みよう。

http://www.scala-lang.org/api/current/index.html#scala.util.Either

      Represents a value of one of two possible types (a disjoint union.) 
      Instances of Either are either an instance of scala.util.Left or scala.util.Right.

つまりRightとLeft持ったやつってことだな。


あぁー、なんか分かってきた。tryブロックがLeftでcatchがRightみたいな使い方するのね。

     val in = Console.readLine("Type Either a string or an Int: ")
     val result: Either[String,Int] = try {
         Right(in.toInt)
       } catch {
         case e: Exception =>
           Left(in)
     }


## FutureとEitherの組み合わせはplayとか由来なのかな

などと適当なことを言ってみます。

よしださんが何か書いてる。

http://d.hatena.ne.jp/xuwei/20140919/1411136788

scalazにはまだ踏み込めない。とりあえずscalaの基本的なところおさえないと。

## わかりやすそうな記事

ここらへんかな

http://blog.shibayu36.org/entry/2015/08/31/103000


…とここまでググって上の方に出てきたのを並べただけである。



## とりあえずREPLさわってみよう
