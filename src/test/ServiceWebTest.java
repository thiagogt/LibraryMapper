package test;

import static org.junit.Assert.*;
import library.utils.GlobalUtils;
import library.utils.ServiceWeb;

import org.junit.Test;

public class ServiceWebTest {

	@Test
	public void testChamaPost() throws Exception {
		String urlParametros = "buscaObraExtjs=Calculo&" +
				"fieldId=1&" +
				"limit=10&" +
				"start=0";
		String response = ServiceWeb.chamaPost(GlobalUtils.COLMEIA_SERVICE, urlParametros);
		System.out.println(response);
	}

}
