package com.jfrog.testing.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

public class Utils {
	

	public static String retrieveMVNFile() {
		
		String artifactId = "user-test-api-app";
		String artifactVersion = "1.0-SNAPSHOT.jar";
		
//		MavenXpp3Reader reader = new MavenXpp3Reader();
//        Model model = null;
//        
//		try {
//			model = reader.read(new FileReader("pom.xml"));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (XmlPullParserException e) {
//			e.printStackTrace();
//		}
//   
//        return model.getArtifactId()+"-"+model.getVersion()+".jar";
		
		return artifactId + "-" + artifactVersion;
		
	}
	
}
