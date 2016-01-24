
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
     


さて    

    A common use of Either is as an alternative to scala.Option for dealing with possible missing values.
    
    
Option型のようにmissing valueを扱う時にEitherは使われると。たぶん。

    In this usage, scala.None is replaced with a scala.util.Left which can contain useful information. 

Noneの代わりがLeftになりますと。

    scala.util.Right takes the place of scala.Some.

RightがSomeです。と。


つまり

Either = Option
Left = None
Right = Some
みたいになるよと言っているぽい。



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



    scala> val either : Either[String, Int] = Right(3)
    either: Either[String,Int] = Right(3)


    scala> either.right
    res9: scala.util.Either.RightProjection[String,Int] = RightProjection(Right(3))


    scala> either.left
    res10: scala.util.Either.LeftProjection[String,Int] = LeftProjection(Right(3))


ふむ、、、よくわからんがleftとrightの参照はProjectionという型の継承クラスを返す感じかな。
じゃなかった。Productというtraitを継承している。

http://www.scala-lang.org/api/current/index.html#scala.util.Either$$LeftProjection


関係性を調べたいなぁ。

either.right、either.leftの戻り値のProjectionはOptionのように扱える。getOrElseとかね。


    scala> either.right.
    asInstanceOf   exists    foreach        map               productPrefix   
    canEqual       filter    get            productArity      toOption        
    copy           flatMap   getOrElse      productElement    toSeq           
    e              forall    isInstanceOf   productIterator   toString 
    
 
いろいろmapなりfilterなりできる。



