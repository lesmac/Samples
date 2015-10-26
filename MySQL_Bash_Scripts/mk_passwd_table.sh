# Author: Leslie MacMillan
# Date: 10/16/15
# This script takes the information in the /etc/passwd file and makes it into a table in the mysql database.

domysql()
{
    mysql -Bcnq --disable-pager -u user -ppassword <<-EOF
    $@;
    exit
EOF
}

#For some things we don't want column headers
domysqlnocol()
{
    mysql -BcNnqs --disable-pager -u lmacmil1 -psqllmacmil1 <<-EOF
    $@;
    exit
EOF
}

help()
{
    echo "This program creates a table from the password file in /etc/passwd"
    echo "then runs a few queries to demonstrate that it has been created."
    exit 0
}

if [[ $1 == -h ]]; then
    help
fi

#We want to delete the old table if there is one, because we want the most up to date information.
domysql "use mysql; drop table passwordtable"
echo "We will now make a table from the password file in /etc/passwd."
domysql "use mysql; create table passwordtable (loginid varchar (15),
   password varchar(1), uid int primary key, gid int, comment varchar(32), homedir varchar(32), shell varchar(32))"
domysql "use mysql; load data local infile '/etc/passwd' into table passwordtable fields terminated by ':'"
echo "Here is a description of the fields in the table: "
domysql "use mysql; Describe passwordtable"
echo "Here is the whole table: "
domysql "use mysql; select * from passwordtable"
echo "The number of users is: "
domysqlnocol "use mysql; select count(uid) from passwordtable"
exit 0
