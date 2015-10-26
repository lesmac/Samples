# Author: Leslie MacMillan
# Date: 10/05/15
# This script establishes the command "CreateAcct" which creates new users in the mysql database.
# To use on your system, change the password as appropriate.

domysql()
{
    mysql -BcNnqs --disable-pager -u root -ppassword <<-EOF
    $@;
    exit
EOF
}

help()
{
    echo "This program adds users to the mysql database"
    echo "The available options are"
    echo "-f followed by a filename will add the users in the file"
    echo "or directly write usernames of users to be added."
    echo "If using a file, it must go first."
    exit 0
}

CreateAcct()
{
    for u in $(ShowUsers); do
         if [[ $1 == $u ]]; then
            # The user exists so exit and do not add it.
             exit 0
         fi
    done
    domysql "use mysql; create user '$1' identified by 'changeme'"
    echo "Added user: $1"
}

while (( "$#" )); do
  if [[ $1 == "-f" ]]; then
      while read line; do
          CreateAcct "$line"
      done < $2
      shift 2
  else
      CreateAcct "$1"
      shift
  fi
done


