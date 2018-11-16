package com.mycorp;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.TestCase;
import util.datos.UsuarioAlta;


/**
 * Unit test for simple App.
 */
public class RealizarSimulacionTest extends TestCase {
	
	@Autowired
	public ZendeskService zendeskService;

    /**
     * Rigourous Test :-)
     */
    @Test
    public void testUsuarioWithCardNumber() {
    	UsuarioAlta usuarioAlta = new UsuarioAlta();
    	usuarioAlta.setNumTarjeta("35627835238950");
    	String userAgent = "";
    	
    	String result = zendeskService.altaTicketZendesk(usuarioAlta, userAgent);
        assertTrue( true );
    }

}
