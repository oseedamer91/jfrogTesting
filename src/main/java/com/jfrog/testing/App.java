package com.jfrog.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.jfrog.testing.userOp.CreateApiKeyStrategy;
import com.jfrog.testing.userOp.GetApiKeyStrategy;
import com.jfrog.testing.userOp.ReGenerateApiKeyStrategy;
import com.jfrog.testing.userOp.RevokeApiKeyStrategy;
import com.jfrog.testing.userOp.UploadStrategy;


public class App {

		/******************************** 
		 *   		Deploy Jar File
		 ********************************   
		 */
		public static void deployMvnFile() {
			System.out.println("============== Deploying a file ==============");
			ProcessBuilder processBuilder = new ProcessBuilder();
	    	String restApiCall = new UploadStrategy().restApiCall();
	    	String response = runProcessAPi(processBuilder, restApiCall);
	    	try {

	    		String UriResponeHeader = response.toString().substring(response.toString().indexOf("uri"), response.toString().length()).replace("\"" , "").replace("}", "");
	    		String file = UriResponeHeader.split("/")[UriResponeHeader.split("/").length-1];
	    		String repo = UriResponeHeader.split("/")[UriResponeHeader.split("/").length-2];

	    		System.out.println("System Log>> " + "["+ file +"]" + " File was deployed to artifactory repo: " + "<"+ repo +">" );
	        } catch(Exception e) {
	        	System.out.println("System Log>> File failed to uploaded to Artifactory!");
	        }
		}


	
		/*********************************************************************************
		 *  REST API - Generate ApiKey
		 *  You can't regenerate if this is no key already , I'm testing here assuming there will be
		 *  so that the regenerated has different key api.
		 *  
		 *********************  Regenerate API Key ***************************************
		 */
		public static String regenerateApiKey() {
			System.out.println("============== Regenerating API KEY ==============");
			String oldKey = getApiKey();
			
			if(oldKey == null) {
		        // create one then regenerate to check if regenerate works.
		        oldKey = createApiKey();
		    }
			
			String newKey = null;
			ProcessBuilder processBuilder = new ProcessBuilder();
	   	 	String restApiCall = new ReGenerateApiKeyStrategy().restApiCall();
	   	 	String response = runProcessAPi(processBuilder, restApiCall).replaceAll("[{|}]+", "");
	   	 	String pattern = "(\"apiKey\"):(.*)";
	   	 	
	   	 	// Create a Pattern object
	        Pattern r = Pattern.compile(pattern);
	        Matcher m = r.matcher(response);
	        if(m.find()) {
	        	newKey = m.group(2).toString();
	        }else {
	        	System.out.println("System Log>> Error: There is no key api!");
	        }
	        
	         
        	System.out.println("System Log>> old key: "+ oldKey);
        	System.out.println("System Log>> new generated key: "+ newKey);
        	boolean isNewKeyGenerated = !oldKey.equals(newKey);
        	System.out.println(isNewKeyGenerated ? "System Log>> Successfully generated new key : " + newKey : " System Log>> Failed to regenerate key");
        	return  newKey;
    
	       
		}
		
		/******************************** 
		 *   		Create API Key
		 ********************************   
		 */
		public static String createApiKey() {
			System.out.println("============== Creating API KEY ==============");
			String key = null;
			ProcessBuilder processBuilder = new ProcessBuilder();
	   	 	String restApiCall = new CreateApiKeyStrategy().restApiCall();
	   	 	String response = runProcessAPi(processBuilder, restApiCall).replaceAll("[{|}]+", "");
	   	 	String pattern = "(\"apiKey\"):(.*)";
	   	 	
	   	 	// Create a Pattern object
	        Pattern r = Pattern.compile(pattern);
	        Matcher m = r.matcher(response);
	        if(m.find()) {
	        	key = m.group(2).toString();
	        	System.out.println("System Log>> Created a key: " + key);
	        }else {
	        	System.out.println("System Log>> Can't create because Api already exist, so we return it");
	        	System.out.println("System Log>> fetching API Key...");
	        }
	        
	        // Api already exist so return it.
	        if(key == null) {
	        	key = getApiKey();
	        }
	        
	        return key;
		}
		
		/******************************** 
		 *   		GET API Key
		 ********************************   
		 */
		public static String getApiKey() {
			System.out.println("============== Getting API KEY ==============");
			String key = null;
			ProcessBuilder processBuilder = new ProcessBuilder();
	   	 	String restApiCall = new GetApiKeyStrategy().restApiCall();
	   	 	String response = runProcessAPi(processBuilder, restApiCall).replaceAll("[{|}]+", "");
	   	 	String pattern = "(\"apiKey\"):(.*)";
	   	 	
	   	 	// Create a Pattern object
	        Pattern r = Pattern.compile(pattern);
	        Matcher m = r.matcher(response);
	        if(m.find()) {
	        	key = m.group(2).toString();
	        	System.out.println("System Log>> Api key: " + key);
	        }else {
	        	System.out.println("System Log>> There is no key api!");
	        }
	        
	        return key;
		}
		
		/******************************** 
		 *   		Revoke API Key
		 ********************************   
		 */
		public static void revokeApiKey() {
			System.out.println("============== Revoking API KEY ==============");
			ProcessBuilder processBuilder = new ProcessBuilder();
	   	 	String restApiCall = new RevokeApiKeyStrategy().restApiCall();
	   	 	String response = runProcessAPi(processBuilder, restApiCall).replaceAll("[{|}]+", "");
	   	 	String pattern = "(\"apiKey\"):(.*)";
	   	 	
	        Pattern r = Pattern.compile(pattern);
	        Matcher m = r.matcher(response);
	        if(m.find()) {
	        	System.out.println("System Log>> Error , key wasn't deleted! ");
	        }else {
	        	if(response.indexOf("error") > 0) {
	        		System.out.println("System Log>> Error there is no key");
	        	}else {
	        		System.out.println("System Log>> Key was deleted successfully!");
	        	}
	        }
		}	
		
		
		public static void exit( ) {
			System.out.println("Exiting Menu... ");
		}
	
		
		
	/**
	 * Create a cmd process to send REST API Calls to Artifactory server
	 */
	private static String runProcessAPi(ProcessBuilder processBuilder, String restApiCall) { 
		 StringBuilder response = new StringBuilder();
		 System.out.println("Sent: " + restApiCall );
		 processBuilder.command("cmd.exe", "/c", restApiCall);
		 int exitCode = -1;
		 
		 try {
	         Process process = processBuilder.start();
	         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

	         String line;
	         System.out.println("Response:");
	         System.out.println("{\n");
	         
 
	         while ((line = reader.readLine()) != null) {
	             System.out.println(line);     
	             response.append(line);
	         }

	         exitCode = process.waitFor();
	         
	         System.out.println("\nRequest exit code status : " + exitCode);
	         System.out.println("\n}\n");
		     } catch (IOException e) {
		         e.printStackTrace();
		     } catch (InterruptedException e) {
		         e.printStackTrace();
		     }

		 if(exitCode != 0) {
			 System.out.println("Error....Operation Failed!");
		 } 
		 
		 return response.toString();
	}
	
	
	/*****************************
	 *	   Menu of API tests
	 *****************************
	 */
	public static void runTests() {
		BufferedReader reader = null;
		String testId = null;
		boolean keepReading = true;
		
		while (keepReading)
	    // Reading data using readLine
	    try {
	    	displayMenu();
	    	reader = new BufferedReader(new InputStreamReader(System.in));
			testId = reader.readLine();
			
			switch(testId) {
				
			case "1":
				deployMvnFile();
				break;
			case "2":
				createApiKey();
				break;
			case "3":
				regenerateApiKey();
				break;
			case "4":
				revokeApiKey();
				break;	
			case "5":
				keepReading = false;
				exit();
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    
	}
	
	
	public static void displayMenu() {
		System.out.println();
		System.out.println("===============  Main Menu  =============== ");
		System.out.println("1: Deploy a jar file to artifactory");
		System.out.println("2: Create api key");
		System.out.println("3: Regenerate api key");
		System.out.println("4: Revoke api key");
		System.out.println("5: Exit");
		System.out.print("Insert option 1 to 5: ");
		
	}
	
    public static void main(String[] args)  {	
    	runTests();
    } 
}
