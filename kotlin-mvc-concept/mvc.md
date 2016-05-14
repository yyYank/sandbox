

# MVC

三つのinterfaceとabstractなDefaultクラスを提供かな。

* Modelクラス
* Viewクラス
* Controllerクラス

## Model

POJO(POKO?)的なものと
ロジックを分けたい。

BeanとServiceクラスとかかなぁ。


## View

Viewの部分はフレームワークでレンダリングまでうまくやってあげたい。
テンプレートエンジンの設定、指定するhtmlファイルの設定とかだけを実装者の関心事としたい


## Controller

ルーティングとModelとの連携、サービスとの連携。
put get post deleteを受け付ける。
