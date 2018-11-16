package com.mycorp;

import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mycorp.support.MensajeriaService;

import junit.framework.TestCase;
import util.datos.UsuarioAlta;


/**
 * Unit test for simple App.
 */
public class RealizarSimulacionTest extends TestCase {
	
	@Autowired
	public ZendeskService zendeskService;
	
	@Mock
	@Qualifier("restTemplateUTF8")
	RestTemplate restTemplate;
	
	@Autowired
    @Qualifier( "emailService" )
    MensajeriaService emailService;

    /**
     * Test ZendeskService with an user with CardNumber
     */
    @Test
    public void testUserWithCardNumber() {
    	UsuarioAlta usuarioAlta = new UsuarioAlta();
    	usuarioAlta.setNumTarjeta("35627835238950");
    	String userAgent = "";
    	
    	String mockResult = new String("datosResult");
    			
    	ResponseEntity<String> mock =	new ResponseEntity<String>(mockResult,
    					HttpStatus.OK);		
    			
    	when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(mock);
    	
    	String result = zendeskService.altaTicketZendesk(usuarioAlta, userAgent);
        assertTrue( true );
    }

}
