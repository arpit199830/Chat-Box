package ECUtils;
public class ECConst {
	public static String DB_NAME ="chat_db";
	public static String DB_HOST="localhost";
	public static String DB_USER="root";
	public static String DB_PASS ="";
	public static String SQLS[] = 
	{
		"create table chat (id INT NOT NULL AUTO_INCREMENT, msg text, user_name VARCHAR(100), cr_date datetime, seen_by text, for_user varchar(100), PRIMARY KEY (id))",	
		"create table app_users (id INT NOT NULL AUTO_INCREMENT, name varchar(40), pass varchar(50), role VARCHAR(50), status VARCHAR(50), PRIMARY KEY (id))",	
		"insert into app_users (name, pass, role, status) values ('admin', 'admin', 'admin', 'unmute')",	
		"insert into app_users (name, pass, role, status) values ('user', 'user', 'user', 'unmute')",	
	};
}
