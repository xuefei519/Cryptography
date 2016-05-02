import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Encryption {
	
	static HashMap<String, String> encipher=new HashMap<String, String>();

	public static boolean Duplicate(String key){
		String[]  key_array= key.split("");
		
		for(int i=0;i<key_array.length-1;i++){
			for(int j=i+1;j<key_array.length;j++){
				if(key_array[i].equals(key_array[j]))
					return true;
			}
		}
		return false;
}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String alphabet="abcdefghijklmnopqrstuvwxyz";
		String[] alphabet_array=alphabet.split(""); 
		String[]  key_array;
		
		while(true){
			
		System.out.println("==========Encryption==========");
		
		while(true){

		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		System.out.println("Please enter the key:");
		String Key = br.readLine();
		String key=Key.toLowerCase().replaceAll("[\\-\\+\\.\\^:,]","");
		System.out.println(key);
		//System.out.println(key);
		if(key.length()<26||key.matches(".*\\d.*")||Duplicate(key)){
			System.out.println("Invalid key, length of key must be 26 non-duplicate letters.");
			}
		else {
			key_array= key.split("");
			break;
			}
		}
	
		for(int i=0;i<key_array.length;i++){
			//encipher.put(key_array[i], alphabet_array[i]);
			encipher.put(alphabet_array[i],key_array[i]);

		}
		
		BufferedReader br2 = new BufferedReader( new InputStreamReader(System.in));
		outer:
		while(true){

		System.out.println("Please enter the text to be encrypted:");
		String Text = br2.readLine();
		String plaintext = Text.toLowerCase().replaceAll("[^\\w\\s]","").replaceAll("_","").replaceAll("\\s+","");
		 
		String[] character = plaintext.split("");
		//System.out.println(plaintext);

		StringBuffer result = new StringBuffer();
		for(int i=0;i<character.length;i++){
			//System.out.println(character[i]);
			if(character[i].matches(".*\\d.*")) //do nothing if it is digit
				;
			else  { //substitution
				//System.out.println(character[i]+" to "+encipher.get(character[i]));		
				character[i]=encipher.get(character[i]);
			}
			result.append( character[i] );
		}
		String ciphertext=result.toString();
		
		System.out.println("==========Ciphertext==========");
		System.out.println(ciphertext);
inner:
		while(true){
			
		System.out.println(">>Do another encryption?(y/n)");
		BufferedReader br3 = new BufferedReader( new InputStreamReader(System.in));
		String In = br3.readLine();
		String in=In.toLowerCase();
		if(in.equals("y"))
		{
			
			while(true){
			System.out.println(">>Encrypt with the previous key?(y/n)");
			BufferedReader br4 = new BufferedReader( new InputStreamReader(System.in));
			String In2 = br4.readLine();
			String in2=In2.toLowerCase();
			if(in2.equals("y")){
			break inner;
			}
			
			else if(in2.equals("n")){
				
			break outer;
			}
			else
				System.out.println("Invalid input.");
			}
			
		}
		
		
		else if(in.equals("n")){
			System.out.println(">>Encryption session ends<<");
			return;
		}
		else
			System.out.println("Invalid input.");
		
		}
	}
		}
	}
}
