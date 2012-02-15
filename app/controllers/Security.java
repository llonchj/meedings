package controllers;

public class Security extends Secure.Security {

    static boolean authentify(String username, String password) {
        return "2573d7ade9e10dd9b2bf33d20718468e".equals(play.libs.Crypto.encryptAES(password));
    }    
 
		static boolean check(String role) {
		    if("admin".equals(role)) {
		        System.out.println("Role -> "+ connected() );
		    }
		    return false;
		}
			
		
   
}