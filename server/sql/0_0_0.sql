CREATE TABLE IF NOT EXISTS sql_version(
  sql_version_id BIGINT AUTO_INCREMENT NOT NULL COMMENT 'ID',
  version_label VARCHAR(10) NOT NULL COMMENT 'バージョン',
  version_comment VARCHAR(255) NOT NULL COMMENT 'バージョンコメント',

  created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT '作成日時',
  updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新日時',

  CONSTRAINT sql_version_PK PRIMARY KEY(sql_version_id)

) COMMENT 'SQLのバージョン管理';

INSERT INTO sql_version(version_label, version_comment) VALUES('0_0_0', 'sql_version テーブルの作成');
