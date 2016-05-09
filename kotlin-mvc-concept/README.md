
# kotlin-mvc-concept


KotlinのKotlinらしさを最大限に活かすためのMVCベースのフレームワーク構想。  


## アプローチ

KotlinはJavaのフレームワークやライブラリを利用出来ることに利点がある。  
ただし、Javaの弱い点を補うDIコンテナの挙動と相性が悪い部分がある。  

例）
インジェクションの際に変数がfinalであってはならない。  
クラスがfinalであってはならない、など。
  

## スローガン

Javaのフレームワークを利用出来るcompatibilityはJetBrainsに任せるとして、  
このコンセプトでは以下のスローガンを設定する。

* 気持よくKotlinのコードを書ける
* 素早く簡単にWebアプリケーションを構築する


## 機能

フレームワークとして以下の機能を提供するものとする。  
それぞれは独立しておりpluggableになることが理想。

* MVC
* ORM
* JSON Parser
* View Template
* Testing


