package com.example.algamoneyapi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//Gerador de senha para testar
public class GeradorSenha {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//mostra a senha gerada no console
		System.out.print(encoder.encode("maria"));
	}

}
