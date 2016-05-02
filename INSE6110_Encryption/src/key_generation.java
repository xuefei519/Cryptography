import java.util.Arrays;
import java.util.Random;


public class key_generation {
	
	public static String random_key(){
		String str="abcdefghijklmnopqrstuvwxyz";
		String[] arr = str.split("");
		int length=arr.length;
		Random rnd = new Random();
		 
		for (int i=0; i<arr.length; i++) {
		    int randomPosition = rnd.nextInt(arr.length);
		    String temp = arr[i];
		    arr[i] = arr[randomPosition];
		    arr[randomPosition] = temp;
		}
		String key=Arrays.toString(arr).replaceAll("[^a-zA-Z]+", "");
		return key;
		
	}
	
	public static void main(String[] args){
		random_key();
	}

}
