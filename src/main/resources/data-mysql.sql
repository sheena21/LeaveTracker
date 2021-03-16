INSERT into roles(roles_id,roles) values(1,'SUPER ADMIN');

INSERT into user (id,username,full_name,password)
            values (1,'superadmin@gmail.com','Super Admin','$2a$10$50wjddDBVr0K5rnoBA0U8eBQQ0kIIp65X.zkluV.qRwFSXma0b4ce');

Insert into user_roles(user_id,roles_roles_id) values (1,1);