﻿# テストコードルール
振る舞い駆動を意識して作成する

## テストコードの意義
- 不具合を検出できる
- 仕様を満たしていることの確認ができる

## 作成するテストコード
- ユニットテスト
- インテグレーションテスト

### ユニットテスト
依存性のチェックと、コンポーネントごとが仕様通りに動いているかを確認する
- 依存性のチェックは、Archunit を使用して作成する
- 仕様は、domain層に集中させているので、domain層のユニットテストの粒度は細かくなってもよい

### インテグレーションテスト
Spring Boot を使って実際に起動できるかを確認する
- テストクラスは、Controllerのメソッド単位で作成する
