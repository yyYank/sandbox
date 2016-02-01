
scalike jdbcとかいうのしか知らんが、果たして。

## 例のごとくググる

ScalaのDBアクセスライブラリまとめ | break the code!! | codebreak

というのを見つけ開いてみると…
さよならページにリダイレクトされてしまった。切ない。
http://sayonara.codebreak.com

ということで、自力でまとめてみようかしら。

## Squeryl

Squeryl - A Scala ORM for SQL Databases
http://squeryl.org

     A Scala ORM and DSL for talking with Databases with minimum verbosity and maximum type safety


と書いてある。

    //All is validated at compile time here :
    
    var avg: Option[Float] = // The compiler 'knows' that this query returns an Option[Float]
        from(grades)(g =>
        // mathId has to by type compatible with g.subjectId to compile
        where(g.subjectId === mathId)  
        compute(avg(g.scoreInPercentage))
    )



