# 準備

### application.properties の値設定

- spring.datasource.url=jdbc:mysql://localhost/ここに schema 名
- spring.datasource.username=ユーザーネーム
- spring.datasource.password=パスワード

### MySQL にデータを作成

```
CREATE TABLE contracts_list (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    name_kana VARCHAR(50),
    birthday DATE,
    company VARCHAR(50),
    contract_num INT,
    contract_name VARCHAR(10),
    contract_amount INT
);
```

```
INSERT INTO employee (name, name_kana, birthday, company, contract_num, contract_name, contract_amount)
VALUES ('山田 太郎', 'ヤマダ タロウ', '1980-01-01', 'Company A', 123456, 'Type A', 1000);
```

```
INSERT INTO employee (name, name_kana, birthday, company, contract_num, contract_name, contract_amount)
VALUES ('田中 花子', 'タナカ ハナコ', '1990-05-15', 'Company B', 654321, 'Type B', 1500);
```

http://localhost:3000/
