
scalike jdbcとかいうのしか知らんが、果たして。

## 例のごとくググる

ScalaのDBアクセスライブラリまとめ | break the code!! | codebreak

というのを見つけ開いてみると…
さよならページにリダイレクトされてしまった。切ない。
http://sayonara.codebreak.com

ということで、自力でまとめてみようかしら。

## Slick

こんな感じの。
http://www.mwsoft.jp/programming/scala/slick_query.html

    import scala.slick.driver.H2Driver.simple._
    Database.forURL(url, driver = "org.h2.Driver") withSession { implicit session: Session =>
      Query(Coffees).list
    }

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


## Skiny-ORM

Skinyの中にORMが含まれているということか。

http://skinny-framework.org/documentation/orm.html



    UselessData.createWithAttributes('a -> "foo", 'b -> Some(123), 'createdTimestamp -> DateTime.now)
    UselessData.findAll()
    
なんか良さげに思える。


## ScalikeJDBC

一番名前は聞くような。

http://scalikejdbc.org/

    // use paste mode (:paste) on the Scala REPL
    val m = Member.syntax("m")
    val name = "Alice"
    val alice: Option[Member] = withSQL {
      select.from(Member as m).where.eq(m.name, name)
    }.map(rs => Member(rs)).single.apply()
    

このぐらいあっさりしてる方が好きかもしれない。


## SORM

http://sorm-framework.org/

    object Db extends Instance(
      entities = Set( Entity[Artist](), Entity[Genre]() ),
      url = "jdbc:h2:mem:test"
    )


    // Store values in the db:
    val metal = Db.save( Genre("Metal") )
    val rock = Db.save( Genre("Rock") )
    Db.save( Artist("Metallica", Set(metal, rock) ) )
    Db.save( Artist("Dire Straits", Set(rock) ) )


    // Retrieve values from the db:
    // Option[Artist with Persisted]:
    val metallica = Db.query[Artist].whereEqual("name", "Metallica").fetchOne() 
    // Stream[Artist with Persisted]:
    val rockArtists = Db.query[Artist].whereEqual("genres.item.name", "Rock").fetch()




## scala-activerecord

などと言っていたら本当にActive Recoredと名のつくものが出てきた。

https://github.com/aselab/scala-activerecord



    import com.github.aselab.activerecord.dsl._
    import models._
    object App extends App {
      Tables.initialize
     
      Person("person1", 25).save
      Person("person2", 18).save
      Person("person3", 40).save
      Person("person4", 18).save
     
      Person.findBy("name", "person1") //=> Some(Person("person1", 25))
      Person.findBy("age", 55) //=> None
      Person.findAllBy("age", 18).toList //=> List(Person("person2", 18), Person("person4", 18))
      Person.where(_.age.~ >= 20).orderBy(_.age desc).toList //=> List(Person("person3", 40), Person("person1", 25))
      Tables.cleanup
     }


## 所感

結局何がスタンダードなのだろうか。
