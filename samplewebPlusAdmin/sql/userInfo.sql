insert into user_info(
user_id,password, family_name,
first_name, family_name_kana,
first_name_kana,
sex,
email,
status,
logined,
regist_date,
update_date)
values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), 0);