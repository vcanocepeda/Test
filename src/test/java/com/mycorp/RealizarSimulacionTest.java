package com.mycorp;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mycorp.support.DatosCliente;
import com.mycorp.support.MensajeriaService;

import junit.framework.TestCase;
import portalclientesweb.ejb.interfaces.PortalClientesWebEJBRemote;
import util.datos.UsuarioAlta;


/**
 * Unit test for simple App.
 */
public class RealizarSimulacionTest extends TestCase {
		
	@Autowired
	ZendeskService zendeskService;
	
	@Mock
	@Qualifier("restTemplateUTF8")
	RestTemplate restTemplate;
	
	@Mock
    @Qualifier( "emailService" )
    MensajeriaService emailService;
	
	@Mock
	@Qualifier("portalclientesWebEJB")
	private PortalClientesWebEJBRemote portalclientesWebEJBRemote;
	

    /**
     * Test ZendeskService with an user with CardNumber
     */
    @Test
    public void testUserWithCardNumber() {
    	UsuarioAlta usuarioAlta = new UsuarioAlta();
    	usuarioAlta.setNumTarjeta("35627835238950");
    	 			
    	ResponseEntity<String> mockIdCliente =	new ResponseEntity<String>(new String("67883"),
    					HttpStatus.OK);		
    	
    	DatosCliente mockDatosCliente = new DatosCliente();
    	
    	//Moqueamos la llamada al restTemplate de (tarjetas.getDatos)
    	when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(mockIdCliente);
    	
    	//Mockeamos la llamada "http://localhost:8080/test-endpoint" para obtener DatosCliente
    	when(restTemplate.getForObject(anyString(), eq(DatosCliente.class), anyString())).thenReturn(mockDatosCliente);
    	
    	
    	//Faltaria moquear la llamada al servicio Zendesk y devolver los datos de usuario
    	
    	String datosUsuario = zendeskService.altaTicketZendesk(usuarioAlta, "userAgent");
    //	assertThat(datosUsuario).isEqualTo("67883");
    }
    
    /**
     * Test ZendeskService with an user with PolicyNumber
     */
    @Test
    public void testUserWithPolicyNumber() {
    	UsuarioAlta usuarioAlta = new UsuarioAlta();
    	usuarioAlta.setNumPoliza("598247895");
    	 			
    	ResponseEntity<String> mockIdCliente =	new ResponseEntity<String>(new String("67883"),
    					HttpStatus.OK);	
    	
    	DatosCliente mockDatosCliente = new DatosCliente();
    	
    	//Moqueamos la llamada a portalclientesWebEJB (cliente.getDatos)
    	when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(mockIdCliente);
    	
    	//Mockeamos la llamada "http://localhost:8080/test-endpoint" para obtener DatosCliente
    	when(restTemplate.getForObject(anyString(), eq(DatosCliente.class), anyString())).thenReturn(mockDatosCliente);
    	
    	//Faltaria moquear la llamada al servicio Zendesk y devolver los datos de usuario
    	
    	String datosUsuario = zendeskService.altaTicketZendesk(usuarioAlta, "userAgent");
//    	assertThat(datosUsuario).isEqualTo("67883");
    }

}
