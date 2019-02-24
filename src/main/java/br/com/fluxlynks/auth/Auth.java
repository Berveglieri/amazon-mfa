package br.com.fluxlynks.auth;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Auth
{
    public static void main( String[] args ) throws IOException
    {
    	
    	
        LoadInformation a = new LoadInformation();
        System.out.println("Your arn MFA access is: "+a.getUserArn());
        
        CreateMfa b = new CreateMfa();
        String[] mfa = b.getMfa().toString().split(",");
        String id = mfa[0].replace("Credentials(", "").replaceAll("\\s+","");
        String secret = mfa[1].replace("SecretAccessKey=", "").replaceAll("\\s+","");
        String token = mfa[2].replace("SessionToken=", "").replaceAll("\\s+","");
        
        System.out.println("Temporary credentials: \n"+"SessionToken="+token);
        
        
        String fileContent = "[default-mpa]\n"+id+"\nSecretAccessKey="+secret+"\nSessionToken="+token;
        BufferedWriter writer = new BufferedWriter(new FileWriter("/home/users/"+System.getProperty("user.name")+"/.aws/credentials"));
        writer.write(fileContent);
        writer.close();
        
        System.out.println("MFA created Successfully, .aws/credentials updated.");
        
       
    }
}
