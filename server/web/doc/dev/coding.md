# コーディングルール
## 全体
<details>
<summary>パッケージ構成</summary>

```
.
├── interfaceAdapter
├── domain
└── useCase
```
</details>

- クリーンアーキテクチャに則り、上位層から順に `domain`, `useCase`, `interfaceAdapter` とする
  - 層ごとにパッケージを分ける
- 上位層の変更は下位層に影響を与えるが、上位層は下位層の変更による影響は受けないものとする
- コンストラクタで依存性の注入に使用する interface は、メソッドの型定義だけの記載とする
  - Kotlin の interface は、型定義だけでなく、Getterとメソッドの実装ができるものの、両者を1つのinterfaceとして使用すると目的を見失いやすくなるため回避する
  - コンストラクタで依存性の注入に使用する interface の実現クラスの命名は、「interface 名」+ 「Impl」とする
- 汎用クラスや、定数をまとめたクラスは極力作成しないようにする
  - ドメインモデルに仕様をまとめるため
  - 記載箇所が分散することで、影響範囲が見えづらくなるのを予防するため
- DTOは data class として定義する
  - DTOとenum class 以外は通常の class で定義する
- 外部のクラスからアクセスできるプロパティは、イミュータブルにする（DTOを除く）
  - ミュータブルなプロパティの値を取得する場合には、専用のメソッドを使ってイミュータブルとして取得できるようにする
  - Collection 型の場合も同様に、専用のメソッドで取得できるようにする
- 条件分岐を行う場合には、OR条件を使用しない
  - OR条件を使用すると条件が複雑になり、可読性を下げる原因になる
  - 内部的にはOR条件だが、呼び出しが真偽値のメソッドでOR条件を回避できる場合は例外とする

## domain層
<details>
<summary>パッケージ構成</summary>

```
domain/
└── sample/
    ├── model/
    │   ├── (class) SampleEntity
    │   └── (class) SampleValueObject
    └── repository/
        ├── (interface) SampleRepository
        └── (class) SampleRepositoryException
```
</details>

関心ごとを定義する
- アプリケーション仕様の根幹
- ほかの層では防御型プログラミングを行わずに、`domain` ですべてのバリデーションのチェックを担う
- `model` には1つのエンティティが含まれるようにする
- エンティティの class 名は、末尾に `Entity` をつける
- `repository` は、エンティティの永続化に関する操作を行う
  - CQRS法則で読み書きを分離するかは検討中
- `repository` には、interface を用意する
  - `repository` の interface 名には、末尾に `Repository` をつける
  - 実現クラスは、`interfaceAdapter` に用意する
- `repository` 単位で例外クラスを作成する
- バリューオブジェクトは、コンストラクタを `value` として、Null許容型とする
  - バリューオブジェクトのプロパティ名も `value` とする
- バリューオブジェクトは原則イミュータブルとし、インスタンス生成後は値が変化しないものとする
  - ただしセキュリティ観点で値を削除する必要がある場合は、`value` をミュータブルで宣言し、外部のクラスからアクセスできないようにする
- バリューオブジェクトのインスタンス生成時には、バリデーションを行う
- エンティティのプロパティに Collection 型を扱う場合には、private として外部からアクセスできないようにする

## useCase層
<details>
<summary>パッケージ構成</summary>

```
useCase/
└── sample/
    ├── (interface) GetSampleUseCase
    ├── (class) GetSampleUseCaseImpl
    ├── input/
    │   └── (class) GetSampleInput
    └── output/
        └── (class) GetSampleOutput
```
</details>

- `interfaceAdapter` と `domain` の中間層
- ユーザーのやりたいことを実現するのが責務
- ユースケース単位で interface と class を用意する
  - interface / class の命名で操作内容が明確になるようし、interface は `UseCase` を末尾につける
  - メソッド名は`execute` 固定とする
  - メソッドの引数名は、`input` 固定で DTO として用意する
    - DTO名の末尾には、`Input` をつける
  - メソッドの戻り値の型も DTO として用意する
    - DTO名の末尾には、`Output` をつける
- Bean登録のために、class に `@UseCase` をつける
- ユースケースは、`interfaceAdapter` から呼び出されるものとする
  - ユースケースから別のユースケースを呼び出さないようにする
    - 依存性が循環するのを避けるため
- ロジックの実装は `domain` に委ねて、ユースケースにはロジックが入りこまないようにする
  - 条件分岐を行う場合は、`domain` に定義された真偽値のメソッドを呼び出して判断する
  - エラーハンドリングは、ユースケース内で別の処理を行いたい場合に限定する
- ユースケース内の処理が複雑になってきた場合には、ユースケースの分割を検討する
- `input`, `output` のプロパティは、`domain` で定義された型のみを使用する
  - プリミティブ型 と Collection 型を使用しないようにする

## interfaceAdapter層
<details>
<summary>パッケージ構成</summary>

```
interfaceAdapter/
├── api/
│   └── path/
│       └── sample/
│           ├── controller/
│           │   ├── (class) SampleController
│           │   └── (class) SampleControllerAdvice
│           └── model/
│               ├── (data class) PostSampleReqDTO
│               └── (data class) PostSampleResDTO
├── gateway/
│   └── serviceName/
│       └── sample/
│           ├── client/
│           │   ├── (interface) SampleClient
│           │   ├── (class) SampleClientImpl
│           │   └── (class) SampleClientException
│           └── model/
│               ├── PostSampleReq
│               └── PostSampleRes
└── repository/
    └── sample/
        └── (class) SampleRepositoryImpl
```
</details>

`api`、`gateway`、`repository` で構成される

### api
REST API の窓口
- `controller` を使って外部からリクエストを受け付ける

### gateway
外部とのやり取りを行う
- 連携先の付け替えをしやすいように、サービスごとにフォルダを分ける
- `client` の実現クラスを用意する
  - Bean登録のため、class に `@Client` をつける
- `client` の interface を用意する
- `client` ごとに 例外クラスを用意する
- 
### repository
依存性の逆転を回避するために、`domain` に用意した `repository` の interface の実現クラス
