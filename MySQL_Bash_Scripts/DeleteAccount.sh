# Author: Leslie MacMillan
# Date: 10/05/15
# This script establishes the command "DeleteAcct" which deletes specified users in the 'mysql' database.
# To use on your system, change the password as appropriate.

domysql()
{
    mysql -BcNnqs --disable-pager -u root -pmanager <<-EOF
    $@;
    exit
EOF
}

help()
{
    echo "This program deletes users from the mysql database"
    echo "The available options are"
    echo "-f followed by a filename will delete the users in the file"
    echo "or directly write usernames of users to be deleted."
    echo "If using a file, it must go first."
    exit 0
}

DeleteAcct()
{
    for u in $(ShowUsers); do
         if [[ $1 == $u ]]; then
            # The user exists so you can delete it.
            domysql "use mysql; drop user '$1'"
            echo "We deleted user: $1"
         fi
    done
}

while (( "$#" )); do
  if [[ $1 == "-f" ]]; then
      while read line; do
          DeleteAcct "$line"
      done < $2
      shift 2
  else
      DeleteAcct "$1"
      shift
  fi
done

