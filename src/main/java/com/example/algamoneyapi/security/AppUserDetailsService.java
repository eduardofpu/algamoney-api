package com.example.algamoneyapi.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Usuario;
import com.example.algamoneyapi.repository.UsuarioRepository;
@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuario e/ou senha incorretos"));
		//return new User(email, usuario.getSenha(), getPermissoes(usuario));
		return new UsuarioSistema(usuario,getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		
		//Sera passado as permissoes para o Set
		Set<SimpleGrantedAuthority> authority = new HashSet<>();
		
		//Garante que vai Colocar em letras maiusculas as roles toUpperCase e forEach do Java 8
		usuario.getPermissoes().forEach(p -> authority.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
		return authority;
	}

}
