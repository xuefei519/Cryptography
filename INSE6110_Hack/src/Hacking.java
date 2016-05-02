import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;



public class Hacking {

	
	public static float[][] bigram_frequency(String fileName) throws IOException{
		float[][] bigram = new float[26][26];
		for (int row = 0; row < 26; row ++)
            for (int col = 0; col < 26; col++)
                bigram[row][col] = 0;
		
		float total_freq=0;
		File file = new File(fileName);
		
		Scanner scanner = new Scanner(file);
		 while(scanner.hasNextLine()) {  
			 String content=scanner.nextLine();
			 String[] parts = content.toString().split(" ");
			 String combination=parts[0].toLowerCase();
			 float freq = Float.parseFloat(parts[1]);
			 total_freq=total_freq+freq;
			 char first=combination.charAt(0);
			 char second=combination.charAt(1);
			 int row = (int) first-97;
			 int col=(int)second-97;
			 bigram[row][col]=freq;
	         }
		 scanner.close();
		 
		 for (int row = 0; row < 26; row ++)
	            for (int col = 0; col < 26; col++)
	                bigram[row][col] = bigram[row][col]/total_freq;
		 
		/* for (int row = 0; row < 26; row ++){
	            for (int col = 0; col < 26; col++){
	               System.out.print( bigram[row][col] );   // Print the Matrix
	            }
		 System.out.print("/n");
		 }*/
		return bigram;
	}
	
	public static String InitKey(String ciphertxt){//get the initial key using monogram
		String LetterFreq="ETAOINSRHLDCUMFPGWYBVKXJQZ";
		String[] letterFreq=LetterFreq.toLowerCase().split("");

	    HashMap<String, Integer> letter=new HashMap<String, Integer>();
	    letter.clear();
		String[]  c_array= ciphertxt.split("");

	    for(int i=0;i<c_array.length;i++){
	    	if (letter.containsKey(c_array[i])){
	    		letter.put(c_array[i], letter.get(c_array[i]) + 1);
	    	}
	    	else if(!c_array[i].matches(".*\\d.*") )
	    		letter.put(c_array[i], 1);
	    	else;
	    }
	    String sortedFreq=sortByV(letter);
	    String[] cipherF=sortedFreq.split("");
	    
	    String[] key=new String[26];
	    //System.out.println(Arrays.toString(letterFreq));
	    //System.out.println(Arrays.toString(cipherF));

	    for(int i=0;i<cipherF.length;i++){

	    	int index=letterFreq[i].charAt(0)-97;
	    	key[index]=cipherF[i];
	    }
	    if(cipherF.length<26){
	    		for(int i=0;i<key.length;i++){
	    			if(key[i]==null){
	    				for(int j=0;j<letterFreq.length;j++){
	    					if(!Arrays.asList(key).contains(letterFreq[j]))
	    						key[i]=letterFreq[j];
	    					else;
	    				}
	    			}
	    			else;
	    		}
	    	
	    }
	    
	    
	    String initK=Arrays.toString(key);
        initK = initK.substring(1, initK.length()-1).replaceAll(",", "").replaceAll(" ", "");

	    return initK;
	}
	
	public static String sortByV(HashMap map){//sort from high to low frequency
		String sorted="";
		 Set<Entry<String, Integer>> set = map.entrySet();
	     List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
	     Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
	        {
	            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
	            {
	                return (o2.getValue()).compareTo( o1.getValue() );
	            }
	        } );
	     for(Map.Entry<String, Integer> entry:list){
	    	 sorted=sorted.concat(entry.getKey());
	     }
	     //System.out.println(sorted);
	     return sorted;
	 	}
	
	public static String substitue(String key,String ciphertxt){
		String[] Key=key.split("");
		String[] Cipher=ciphertxt.split("");
		String alphabet="abcdefghijklmnopqrstuvwxyz";
		String[] alphabet_array=alphabet.split("");
		//String result="";
		for(int i=0;i<Cipher.length;i++){
			for(int j=0;j<Key.length;j++){
				if(Cipher[i].equals(Key[j])){
					Cipher[i]=alphabet_array[j];
				break;	
				}
				else ;
			}
		}
		 String result = Arrays.toString(Cipher);               
         result = result.substring(1, result.length()-1).replaceAll(",", "").replaceAll("\\s+", "");
         return result;
	}
	
	public static float[][] freqMatrix(String text){
		int total=0;
		String[] txt=text.split("");
		float[][] freqMatrix=new float[26][26];
		for(int i=0;i<26;i++){
			for(int j=0;j<26;j++){
				freqMatrix[i][j]=0;		
				}
		}
		for(int i=0;i<txt.length-1;i++){
			if(!txt[i].matches(".*\\d.*")&&!txt[i+1].matches(".*\\d.*")){
				int row=(int)txt[i].charAt(0) -97;
				int col=(int)txt[i+1].charAt(0) -97;	
				freqMatrix[row][col]=freqMatrix[row][col]+1;
				total++;
				}
			else;
		}
		for(int i=0;i<26;i++){
			for(int j=0;j<26;j++){
				freqMatrix[i][j]=freqMatrix[i][j]/total;		
				}
		}
		/*for(int i=0;i<26;i++){
			for(int j=0;j<26;j++){
				System.out.print(freqMatrix[i][j]);		Print the Matrix
				}
			System.out.print("\n");
		}*/
	    return freqMatrix;
	}
	
	public static void swap(float[][] a, int i0, int j0, int i1, int j1) {
	    float temp = a[i0][j0];
	    a[i0][j0] = a[i1][j1];
	    a[i1][j1] = temp;
	  }
	
	public static float[][] swapRows(float[][] matrix,int row0,int row1){
		int cols = matrix[0].length;
	    for (int col=0; col<cols; col++)
	      swap(matrix, row0, col, row1, col);
		return matrix;
	}
	
	public static float[][] swapCol(float[][] matrix,int col0,int col1){
		int rows = matrix.length;
	    for (int row=0; row<rows; row++)
	        swap(matrix, row, col0, row, col1);
	    return matrix;
	}
	
	public static float score(float[][] matrix1,float[][] matrix2){
		float score=0;
		for(int i=0;i<matrix1.length;i++){
			for(int j=0;j<matrix1[0].length;j++){
				float dif=matrix1[i][j]-matrix2[i][j];
				score=score+Math.abs(dif);
			}
		}
		return score;
	}
	
	public static String[] key_swap(String[] key,int i,int dist){
		String temp;		
		temp=key[i];
		key[i]=key[i+dist];
		key[i+dist]=temp;
		return key;
	}
	 
	public static String find_key(String[] key,float[][] matrix1,float[][] matrix2){
		float best_score=score(matrix1,matrix2);
		int dist=1;
		for(int j=0;j<26;j++){
			for(int i=0;i+dist<26;i++){
			swapRows(matrix2,i,i+dist);
			swapCol(matrix2,i,i+dist);
			float score1=score(matrix1,matrix2);
			if(score1<best_score){//new key is better,
				key_swap(key,i,dist);
				best_score=score1;
			}
			else{//new key is not better
				swapRows(matrix2,i,i+dist);
				swapCol(matrix2,i,i+dist);
				}
			}
			dist++;
		}

		String k=Arrays.toString(key);
        k = k.substring(1, k.length()-1).replaceAll(",", "").replaceAll(" ", "");
		return k;
		
	}
	
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

	
	public static float accuracy(String key0, String key1){
		String[] Key0=key0.split("");
		String[] Key1=key1.split("");
		int j=0;
		for(int i=0;i<26;i++){
			if(Key0[i].equals(Key1[i]))
				j++;
			else;
		}
		float accuracy=(float)j/26;
		return accuracy;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			
				
	float[][] bigram =bigram_frequency("2grams.txt").clone();//read the bigram into memory
	
	
		/*for(int i=0;i<26;i++){
			for(int j=0;j<26;j++){
				System.out.print(bigram[i][j]+" ");
			}
			System.out.println("\n");
		}*/
		
		System.out.println("Please enter the ciphertext:");
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		String initCipher = br.readLine();
		String initk=InitKey(initCipher);
		
		System.out.println("monoKey:"+initk);
		
		String decipher=substitue(initk,initCipher);
		float[][] freq=freqMatrix(decipher).clone();//compute a matrix from the cipher text

		String[] initkey=initk.split("");
		String best_key=find_key(initkey,bigram, freq);
		String deciphertxt=substitue(best_key,initCipher);
		
		System.out.println("The key is: "+best_key);
		System.out.println("The plaintext is: "+deciphertxt);
		System.out.println("To get the accuracy of the key, please enter the correct key:");
		BufferedReader br1 = new BufferedReader( new InputStreamReader(System.in));
		String expectedK = br1.readLine();
		
		
		
		System.out.println("The accuracy of the key is "+accuracy(expectedK,best_key)*100+"%");
	
	}
	
	public static String[] run(String ciphertxt) throws IOException {
		// TODO Auto-generated method stub
			String[] output=new String[2];
				
	float[][] bigram =bigram_frequency("2grams.txt").clone();//read the bigram into memory
		String initk=InitKey(ciphertxt);
				
		String decipher=substitue(initk,ciphertxt);
		float[][] freq=freqMatrix(decipher).clone();//compute a matrix from the cipher text

		String[] initkey=initk.split("");
		String best_key=find_key(initkey,bigram, freq);
		String deciphertxt=substitue(best_key,ciphertxt);
		
		output[0]=best_key;
		output[1]=deciphertxt;
		/*System.out.println("To get the accuracy of the key, please enter the correct key:");
		BufferedReader br1 = new BufferedReader( new InputStreamReader(System.in));
		String expectedK = br1.readLine();
		System.out.println("The accuracy of the key is "+accuracy(expectedK,best_key)*100+"%");*/
		return output;
	
	}

}
