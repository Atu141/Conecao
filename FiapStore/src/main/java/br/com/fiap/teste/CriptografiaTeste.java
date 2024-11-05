package br.com.fiap.teste;

import org.junit.Test;
import br.com.fiap.util.CriptografiaUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class CriptografiaTeste {

	@Test
	public void testeCriptografar() {
		try {
			String senha = "123456";
			String criptografada = CriptografiaUtils.criptografar(senha);
			assertNotNull("A senha criptografada não deve ser nula", criptografada);
			assertEquals("A criptografia deve ser consistente", criptografada, CriptografiaUtils.criptografar(senha));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
