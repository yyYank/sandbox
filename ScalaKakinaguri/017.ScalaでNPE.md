いや、Kotlinだと簡単にはNPE出せないので。


## 実践

    scala> val a = null
    a: Null = null

    scala> a.toString()
    java.lang.NullPointerException
      ... 33 elided
      
      


あら、すんなり。


## 結論

nullの変数への代入も普通にできるし
Option、Some、Noneあたりを意識して使わなければ
プロダクトコードでもNPEしちゃうのかもなぁ。
