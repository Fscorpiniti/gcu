package com.edu.untref.gcu.security;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Generador de tokens para validacion de seguridad.
 * 
 * @author Fernando S.
 *
 */
public class TokenGenerator {
	
	//TODO FER externalizar la longitud del token
	public static String generate() {
		return RandomStringUtils.randomAlphabetic(16);
	}
	
}
