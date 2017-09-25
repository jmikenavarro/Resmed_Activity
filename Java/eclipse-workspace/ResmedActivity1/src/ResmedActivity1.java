/********************************
 * Author: Jan Michael Navarro
 * Date: September 25,2017 
 * Purpose: Resmed Activity 1: GET and POST exercise
 * OUTPUTS: GET_SAMPLE get sample function prints the response returned by api
 *          POST_SAMPLE post sample function prints the response returned by api
 * ******************************/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ResmedActivity1 {

	//
	private static final String URL_GET_SAMPLE = "https://reqres.in/api/users/2";
	private static final String URL_POST_SAMPLE = "https://reqres.in/api/users";
	private static final String USER_AGENT = "Chrome/41.0.2228.0";
	private static final String POST_DATA = "name=Jason Data";

	public static void main(String[] args) throws IOException {
	//Call GET function sample
		GET_SAMPLE();
	//Call POST function sample
		POST_SAMPLE();
	}
	
	/********************************
	 * Function: DisplayInputStream
	 * Purpose: Displays the response data for both Get & Post Test
	 * ******************************/
	//Function to Display Response
	private static void DisplayInputStream(HttpURLConnection URLObject) throws IOException
	{
		String GetOutput;
		BufferedReader InputStream = new BufferedReader(new InputStreamReader(
				URLObject.getInputStream()));
		while ((GetOutput = InputStream.readLine()) != null) {
			System.out.println(GetOutput.toString());
		}
		System.out.println("\n");
		InputStream.close();	
		
	}

	private static void GET_SAMPLE() throws IOException {
		//Setup Connection to URL
		int responseCode;
		String GetOutput;
		URL URLObject = new URL(URL_GET_SAMPLE);
		HttpURLConnection URLGetSampleConnection = (HttpURLConnection) URLObject.openConnection();
		URLGetSampleConnection.setRequestMethod("GET");
		
		//Set request user agent to Chrome
		URLGetSampleConnection.setRequestProperty("User-Agent", USER_AGENT);
		System.out.println("Testing GET.........\n");
		System.out.println("Connection Response Code Returned: " + (responseCode = URLGetSampleConnection.getResponseCode()));
		//If Successful Print Response
		if (HttpURLConnection.HTTP_OK == responseCode) { 
			System.out.println("Connection OK\n");
			System.out.println("Returned Data:\n");
			DisplayInputStream(URLGetSampleConnection);
		} 
	}
	
	private static void POST_SAMPLE() throws IOException {
		//Setup Connection to URL
		int responseCode;
		String GetOutput;
		URL URLObject = new URL(URL_POST_SAMPLE);
		HttpURLConnection URLPostSampleConnection = (HttpURLConnection) URLObject.openConnection();
		URLPostSampleConnection.setRequestMethod("POST");
		//Set request user agent to Chrome
		URLPostSampleConnection.setRequestProperty("User-Agent", USER_AGENT);
		URLPostSampleConnection.setDoOutput(true);
		OutputStream Outputstream = URLPostSampleConnection.getOutputStream();
		Outputstream.write((POST_DATA).getBytes());
		Outputstream.flush();
		Outputstream.close();
		System.out.println("Testing POST.........\n");
		System.out.println("Connection Response Code Returned: " + (responseCode = URLPostSampleConnection.getResponseCode()));
		//If Successful Print Response
		if(HttpURLConnection.HTTP_CREATED == responseCode)
		{
			System.out.println("POST Returned OK\n");
			System.out.println("Returned Data:\n");
			DisplayInputStream(URLPostSampleConnection);
		}
		} 
}

