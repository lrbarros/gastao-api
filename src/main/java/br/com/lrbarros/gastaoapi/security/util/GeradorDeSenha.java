package br.com.lrbarros.gastaoapi.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorDeSenha {

	public static void main(String[] args) {
		System.out.println("@ngul@r0 = "+new BCryptPasswordEncoder().encode("@ngul@r0"));
		
		System.out.println("m0b1l30 = "+new BCryptPasswordEncoder().encode("m0b1l30"));
		
		
	}
}
