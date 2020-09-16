import java.net.MalformedURLException;
import java.util.ArrayList;

public class Main {

	
	public static void main(String[] args) throws MalformedURLException {

		OperacionesURL op=new OperacionesURL();
		int nivel=0;
		
		
		if(args.length ==1) {
			nivel=1;
		}
		else if(args.length == 2){
            nivel=Integer.parseInt(args[1]);
            	
        }	
		else {
			System.out.println("Numero de parametros erroneos");
			System.exit(0);
		}

		//String url = "https://www.marca.com";
		//String url = "file:///Users/rodrigo/Desktop/PRUEBA/index.html";
		String url = args[0];

		System.out.println("Numero de parametros: "+args.length+" args[0]="+url+" args[1]="+nivel);

		if(nivel==0)
			System.out.println("- 0: "+url);
		else
			op.mostrarURLS(url,nivel);

	}

}
