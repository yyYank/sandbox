

# 演算子を考える

考えよう！と思ってググるとまぁ、ひしだまさんだ。
http://www.ne.jp/asahi/hishidama/home/tech/scala/expression.html


ということでまぁ、気になるものだけ思ったことを書いてみる。

## isInstanceOf

JavaでいうinstanceofがisInstanceOfというメソッドになってるっぽい。
Anyクラスに属している。


## eq と ne

equalとnot equalのようだ。
なるほど、そういう演算子はあったら便利だな。


    scala> val a = "a"
    a: String = a

    scala> val b = "b"
    b: String = b

    scala> a ne b
    res32: Boolean = true
    
    scala> a eq b
    res33: Boolean = false
    
    
    
## 演算子オーバーロード

Scalaでは演算子オーバーロードができる。




