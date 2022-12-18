# todo-api

Spring Bootの練習として作成した、TODOの情報を扱うREST API。

## エンドポイントと機能一覧

| エンドポイント | メソッド | 処理 | 備考 |
| ---- | ---- | ---- | ---- |
| /todos | GET | 全てのTODOの取得 ||
| /todos/{id} | GET | 特定のTODOの取得 ||
| /todos | POST | 新しいTODOの登録 | ログインした状態でのみ可能 |
| /todos/{id} | PUT | 特定のTODOの更新 | ログインした状態でのみ可能 |
| /todos/{id} | DELETE | 特定のTODOの削除 | ログインした状態でのみ可能 |
| /register | POST | アカウント作成 ||
| /login | POST | ログイン ||
| /logout | POST | ログアウト ||

## 使用技術

* Spring Boot
* Spring Data JPA
* Spring Security
* JUnit / Mockito
