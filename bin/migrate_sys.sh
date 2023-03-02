export MIGRATIONS_HOME=./tools/mybatis-migrations-3.3.11
export MIGRATIONS=$MIGRATIONS_HOME/bin
export PATH=$MIGRATIONS:$PATH
migrate --path=migrations/01-sys $*
