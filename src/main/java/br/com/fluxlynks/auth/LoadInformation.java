package br.com.fluxlynks.auth;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.ListMfaDevicesRequest;
import software.amazon.awssdk.services.iam.model.ListMfaDevicesResponse;
import software.amazon.awssdk.services.iam.model.ListUsersRequest;
import software.amazon.awssdk.services.iam.model.ListUsersResponse;
import software.amazon.awssdk.services.iam.model.User;
import software.amazon.awssdk.regions.Region;



public class LoadInformation {
	
		private String serial;

        public String getUserArn() {
        	
        	
        	
        	
        	Region region = Region.AWS_GLOBAL;
        	IamClient iam = IamClient.builder().region(region).build();
        	
        	boolean done = false;
        	String new_marker = null;

        	while(!done) {
        	    ListUsersResponse response;

        	    if (new_marker == null) {
        	        ListUsersRequest request = ListUsersRequest.builder().build();
        	        response = iam.listUsers(request);
        	    }
        	    else {
        	        ListUsersRequest request = ListUsersRequest.builder()
        	        		.marker(new_marker).build();
        	        response = iam.listUsers(request);
        	    }

        	    for(User user : response.users()) {
        	        if (user.userName().toString().equals(System.getProperty("user.name"))){
        	        	ListMfaDevicesRequest request = ListMfaDevicesRequest.builder().build();
        	        	ListMfaDevicesResponse result = iam.listMFADevices(request);
        	        	
        	        	String[] sn =  result.mfaDevices().toString().split(",");
        	        	
        	        	this.serial = sn[1].replace("SerialNumber=", "").replaceAll("\\s+","");
        	        }
        	    }

        	    if(!response.isTruncated()) {
        	        done = true;
        	    }
        	    else {
        	    	new_marker = response.marker();
        	    }
        	    
        	    
        	    
        	}
        	

        	return serial;
        	
       
}
        
        
        

        
}
