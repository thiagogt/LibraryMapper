package library.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import sun.net.www.protocol.http.HttpURLConnection;
public class ServiceWeb {
		static Logger log = LoggerFactory.getLogger(ServiceWeb.class);
		
	public static String InicioURLServicos() throws Exception {
			String urlString = "http://" +GlobalUtils.DEFAULT_HOST;
			urlString += ":" + GlobalUtils.DEFAULT_PORT + "/";
			urlString += GlobalUtils.APPLICATION_NAME + "/servicos/";
			return urlString;
		}
		
	public static String chamadaStringURL(String urlString) throws Exception {
		String urlStringPrefixo = ServiceWeb.InicioURLServicos();
		URL url = new URL(urlStringPrefixo + urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null)
		{
			sb.append(line);
		}
		rd.close();
		return sb.toString();
	}
	
	public static String chamaPost(String urlString, String urlParametros)  throws Exception{
		URL url;
		HttpURLConnection connection = null;
		// Create connection
		url = new URL(urlString);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		
		connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParametros.getBytes("UTF-8").length));
		
		connection.setUseCaches(false);
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		// Send request
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.write(urlParametros.getBytes("UTF-8"));
		wr.flush();
		wr.close();
		
		// Get Response
		BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
		sb.append(line);
		}
		rd.close();
		return sb.toString();
	}
	
	public static String querySearchOnColmeia(String query) throws Exception{
		String urlParametros = "buscaObraExtjs="+query+"&" +
				"fieldId=1&" +
				"limit=10&" +
				"start=0";
		String response = chamaPost(GlobalUtils.COLMEIA_SERVICE, urlParametros);
		
		return response;
	}
	

}
