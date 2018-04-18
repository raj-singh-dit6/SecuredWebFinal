package com.securedweb.configuration;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
	
public class CustomMySQLDialect extends MySQLDialect{
	       public CustomMySQLDialect()
	       {
	              super();
	              registerFunction("charset", new StandardSQLFunction("charset"));
	       }

}

