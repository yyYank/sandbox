
# kotlin-mvc-concept


KotlinのKotlinらしさを最大限に活かすためのMVCベースのフレームワーク構想。  


## アプローチ

KotlinはJavaのフレームワークやライブラリを利用出来ることに利点がある。  
ただし、Javaの弱い点を補うDIコンテナの挙動と相性が悪い部分がある。

例）
インジェクションの際に変数がfinalであってはならない。  
クラスがfinalであってはならない、など。

```Kotlin
@Component
open class Hoge @Autowired (val injectedValue:String) {
}
```


※JSR269を用いたライブラリとの相性も悪い  
（個人的にアノテーションベースと相性が悪いと感じている）ので  
それにJSR269の代替も何か考えられれば良いのだが、、、そっちは難しそう。  


## スローガン

Javaのフレームワークを利用出来るcompatibilityはJetBrainsに任せるとして、  
このコンセプトでは以下のスローガンを設定する。

* 気持ちよくKotlinのコードを書ける
* 素早く簡単にWebアプリケーションを構築する


## 機能

フレームワークとして以下の機能を提供するものとする。  
それぞれは独立しておりpluggableになることが理想。

* MVC
* ORM
* JSON Parser
* View Template
* Testing
* Logging


## ポータビリティーとか考える

最近マイクロフレームワークとか流行ってるらしいし

* 組み込みサーバーに対応していること
* もちろんjarやwarへのパーケージングも可能なこと
* 実行可能jarであること


