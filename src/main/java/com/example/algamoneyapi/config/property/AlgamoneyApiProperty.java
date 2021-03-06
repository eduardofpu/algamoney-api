package com.example.algamoneyapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {
	
	private String orinPermitida ="http://localhost:4200";
	
	private final Seguranca seguranca = new Seguranca();
	
	
	public Seguranca getSeguranca() {
		return seguranca;
	}
    

	public String getOrinPermitida() {
		return orinPermitida;
	}


	public void setOrinPermitida(String orinPermitida) {
		this.orinPermitida = orinPermitida;
	}


	public static class Seguranca{
	private boolean enableHttps;

	public boolean isEnableHttps() {
		return enableHttps;
	}

	public void setEnableHttps(boolean enableHttps) {
		this.enableHttps = enableHttps;
	}
	
	
	}
}
