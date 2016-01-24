
## Tupleあるんだよね

Tuple好きです。なんか知らんけど。

    scala> (1,1)
    res38: (Int, Int) = (1,1)
    
    scala> (1,2,3)
    res39: (Int, Int, Int) = (1,2,3)
    
    scala> (1,2,3,4)
    res40: (Int, Int, Int, Int) = (1,2,3,4)
    
    scala> (1,"2","3",4)
    res41: (Int, String, String, Int) = (1,2,3,4)
    
    
    ## Tupleの要素へのアクセス
    
    
    scala> val t = (1,2,3,4,5,6)
t: (Int, Int, Int, Int, Int, Int) = (1,2,3,4,5,6)

    scala> t._1
    res42: Int = 1
    
    scala> t._2
    res43: Int = 2
    
    scala> t._3
    res44: Int = 3
    
    scala> t._4
    res45: Int = 4
    
    
## 23個以上の要素数はダメ

これ、なんか理由あった気がするけど、忘れちゃった。

http://www.ne.jp/asahi/hishidama/home/tech/scala/tuple.html


## Tupleを返すメソッド

    scala> def makeT(a : Int, b : Int) :(Int, Int) = {
         | println("createTuple")
         | return (a, b)
         | }
    makeT: (a: Int, b: Int)(Int, Int)
    
    scala> val t = makeT(1,2)
    createTuple
    t: (Int, Int) = (1,2)
    scala> t._1
    res48: Int = 1

