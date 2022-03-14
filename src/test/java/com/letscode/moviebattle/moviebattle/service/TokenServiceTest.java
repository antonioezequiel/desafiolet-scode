package com.letscode.moviebattle.moviebattle.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class TokenServiceTest {
	TokenService tokenService;
	String expirecaoToken;
	String secret;
	String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJqb2dvIGRlIGZpbG1lcyIsInN1YiI6Impvc2UiLCJpYXQiOjE2NDcyODEwMDcsImV4cCI6MTY0NzMxMTAwN30.c1PXd_8rB91cqn6WI7feOaQsuGsDqatvv-QY8EYHAX4";
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);		
		this.tokenService = new TokenService();
		this.tokenService.setExpirecaoToken("300000");
		this.tokenService.setSecret("rm'!@N=Ke!~p8VTA2ZRK~nMDQX5Uvm!m'D&]{@Vr?G;2?XhbC:Qa#9#eMLN\\}x3?JR3.2zr~v)gYF^8\\:8>:XfB:Ww75N/emt9Yj[bQMNCWwW\\J?N,nvH.<2\\.r~w]*e~vgak)X\"v8H`MH/7\"2E`,^k@n<vE-wD3g9JWPy;CrY*.Kd2_D])=><D?YhBaSua5hW%{2]_FVXzb9`8FH^b[X3jzVER&:jw2<=c38=>L/zBq`}C6tT*cCSVC^c]-L}&/");
	}

	@Test
	void testIsValid() {		
		assertTrue(tokenService.isValid(token));
	}
	
	@Test
	void testGetNomeUsuario() {
		String nome = tokenService.getNomeUsuario(token);
		assertEquals("jose", nome);
	}	

}
