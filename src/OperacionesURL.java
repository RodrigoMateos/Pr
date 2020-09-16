import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class OperacionesURL {
	
	public List<String> dameURL(String enlace) {

		URL url;
		List<String> urls = new ArrayList<>();

		try {
			url = new URL(enlace);
			String inputLine;
			
			URLConnection con = url.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String[] trozos;
	        String[] trozos2;
	
	        while((inputLine = in.readLine()) != null){
	        	
                if (inputLine.contains("href=\"http://") || inputLine.contains("http://")  || 
                		inputLine.contains("ftp://") || inputLine.contains("file://") ) {
     	           trozos = inputLine.split("href=\"");
     	           if(trozos.length>=2) {
    	        	   for(int i=1;i<trozos.length;i++) {
    	                   if (trozos[i].contains("https://") || trozos[i].contains("http://")  || 
    	                		   trozos[i].contains("ftp://") || trozos[i].contains("file://") ) {
	    		        	   trozos2=trozos[i].split("\"");
	    		        	   urls.add(trozos2[0]);
    	                   }
    	        	   }
    	           }
                }
            }	        
	        in.close();

		} catch (MalformedURLException e) {
			//System.out.println("La URL "+enlace+" no cumple con el protocolo URL");
		} catch (IOException e) {
			//System.out.println("La pagina "+enlace+" no tiene codigo HTML");
		}
		return urls;

	}
	
	public List<Enlaces> dameURLS(List<String> urls, int nivelMax, List<Enlaces> urlsFinal, int nivel) throws MalformedURLException {
		
		for(String en:urls) {
			urlsFinal.add(new Enlaces(en, nivel));
			if(nivel!=nivelMax)
				dameURLS(dameURL(en),nivelMax, urlsFinal, nivel+1);
		}
		
		return urlsFinal;
	}
	
	
	public void mostrarURLS(String url, int nivel) throws MalformedURLException {

		if(url.length()>5 && rutaCorrecta(url)) {
			List<String> listaUrls = dameURL(url);
			if(nivel==1)
				for (String enlaces: listaUrls)
					System.out.println(enlaces);
			else
				pintarArbol(dameURL(url), nivel);
		}
		else {
			System.out.println("La ruta esta mal formada");
		}
	}
	
	private boolean rutaCorrecta(String url) {
		return url.substring(0, 6).equals("ftp://") || url.substring(0, 7).equals("file://") || url.substring(0, 7).equals("http://") || url.substring(0, 8).equals("https://");
	}

	public void pintarArbol(List<String> url, int nivel) throws MalformedURLException {
		List<Enlaces> enlaces = dameURLS(url, nivel, new ArrayList<Enlaces>(), 1);
		for (Enlaces en: enlaces)
			System.out.println("- "+en.getNivel()+": "+en.getUrl());
	}
}
