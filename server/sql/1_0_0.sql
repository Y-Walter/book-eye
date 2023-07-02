CREATE TABLE IF NOT EXISTS user(
  user_id BIGINT AUTO_INCREMENT NOT NULL COMMENT 'ID',
  user_name VARCHAR(50) NOT NULL COMMENT 'ユーザ表示名',

  created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT '作成日時',
  updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新日時',

  CONSTRAINT user_PK PRIMARY KEY(user_id)

) COMMENT 'ユーザ情報';

CREATE TABLE IF NOT EXISTS user_account(
  user_account_id BIGINT AUTO_INCREMENT NOT NULL COMMENT 'ID',
  principal_name VARCHAR(254) NOT NULL COMMENT 'ログインアカウント名',
  account_password VARCHAR(60) NOT NULL COMMENT 'パスワード',
  is_enabled BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'アカウント有効',
  user_id BIGINT NOT NULL COMMENT 'ユーザID',

  created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT '作成日時',
  updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新日時',

  CONSTRAINT user_account_PK PRIMARY KEY(user_account_id),
  CONSTRAINT user_account_UK_principal_name UNIQUE KEY(principal_name),
  CONSTRAINT user_account_FK_user_id FOREIGN KEY(user_id) REFERENCES user(user_id) ON UPDATE NO ACTION ON DELETE NO ACTION

) COMMENT 'ユーザアカウント情報';

INSERT INTO sql_version(version_label, version_comment) VALUES('1_0_0', 'user, user_account のテーブル追加');
