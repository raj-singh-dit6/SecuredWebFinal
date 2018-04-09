package com.securedweb.configuration;

import java.util.Locale;
import java.util.Map;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomNamingStrategy extends PhysicalNamingStrategyStandardImpl {
 
	/*private static final Map<String,String> ABBREVIATIONS = buildAbbreviationMap();*/

	
	   @Override
	    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		   Identifier iden= new Identifier(addUnderscores(name.getText()), name.isQuoted());
		   System.err.println("TABLE : "+iden.toString()); ;
		   return iden;
	    }
	    @Override
	    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
	    	Identifier iden= new Identifier(addUnderscores(name.getText()), name.isQuoted());
	    	System.err.println("COLUMN : "+iden.toString()); ;
	    	return iden;
	    }
	    protected static String addUnderscores(String name) {
	        final StringBuilder buf = new StringBuilder(name.replace('.', '_'));
	        for (int i = 1; i < buf.length() - 1; i++) {
	            if (Character.isLowerCase(buf.charAt(i - 1)) &&
	                Character.isUpperCase(buf.charAt(i)) &&
	                Character.isLowerCase(buf.charAt(i + 1))) {
	                buf.insert(i++, '_');
	            }
	        }
	        return buf.toString().toLowerCase(Locale.ROOT);
	    }
	
	/*@Override
	public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		// Acme naming standards do not apply to catalog names
		return name;
	}

	@Override
	public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		// Acme naming standards do not apply to schema names
		return null;
	}

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		final List<String> parts = splitAndReplace( name.getText() );
		return jdbcEnvironment.getIdentifierHelper().toIdentifier(
				join( parts ),
				name.isQuoted()
		);
	}

	@Override
	public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		final LinkedList<String> parts = splitAndReplace( name.getText() );
		// Acme Corp says all sequences should end with _seq
		if ( !"seq".equalsIgnoreCase( parts.getLast() ) ) {
			parts.add( "seq" );
		}
		return jdbcEnvironment.getIdentifierHelper().toIdentifier(
				join( parts ),
				name.isQuoted()
		);
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		final List<String> parts = splitAndReplace( name.getText() );
		return jdbcEnvironment.getIdentifierHelper().toIdentifier(
				join( parts ),
				name.isQuoted()
		);
	}

	*//** If want to prepare abbreviations for some special cases , write key- value pair **//*
	private static Map<String, String> buildAbbreviationMap() {
		TreeMap<String,String> abbreviationMap = new TreeMap<> ( String.CASE_INSENSITIVE_ORDER );
		return abbreviationMap;
	}	
	private LinkedList<String> splitAndReplace(String name) {
		LinkedList<String> result = new LinkedList<>();
		for ( String part : StringUtils.splitByCharacterTypeCamelCase( name ) ) {
			if ( part == null || part.trim().isEmpty() ) {
				// skip null and space
				continue;
			}
			part = applyAbbreviationReplacement( part );
			result.add( part.toLowerCase( Locale.ROOT ) );
		}
		return result;
	}

	private String applyAbbreviationReplacement(String word) {
		if ( ABBREVIATIONS.containsKey( word ) ) {
			return ABBREVIATIONS.get( word );
		}

		return word;
	}

	private String join(List<String> parts) {
		System.err.println("TABLE Name : "+parts.toString());
		boolean firstPass = true;
		String separator = "";
		StringBuilder joined = new StringBuilder();
		for ( String part : parts ) {
			joined.append( separator ).append( part );
			if ( firstPass ) {
				firstPass = false;
				separator = "_";
			}
		}
		System.err.println(joined.toString());
		return joined.toString();
	}*/
	
}
		
 
	
