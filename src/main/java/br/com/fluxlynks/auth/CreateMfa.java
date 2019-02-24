package br.com.fluxlynks.auth;

import java.util.Scanner;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sts.model.Credentials;
import software.amazon.awssdk.services.sts.model.GetSessionTokenRequest;
import software.amazon.awssdk.services.sts.StsClient;

public class CreateMfa {

	public Credentials getMfa() {
		
		Scanner scan = null;
	try {
		scan = new Scanner(System.in);
		System.out.print("Type your MFA code: ");
		String token = scan.nextLine();
		
		
		LoadInformation a = new LoadInformation();
		
		Region region = Region.US_EAST_1;
		StsClient client = StsClient.builder().region(region).build();
		
		GetSessionTokenRequest sessionRequest = GetSessionTokenRequest.builder().serialNumber(a.getUserArn()).tokenCode(token).build();
		
	    return client.getSessionToken(sessionRequest).credentials();
	    
		}
		
	finally {
		    if(scan!=null)
		        scan.close();
		}	
	}
}
