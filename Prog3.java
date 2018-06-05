import java.util.*;
import java.io.*;
class Prog3
{
	public static void operate(int plain[], int key[], char set[], int dec)
	{
		int keylen = key.length;
		int key2[] = new int[plain.length];
		int s[] = new int[64];
		int t[] = new int[s.length];
		int result[] = new int[plain.length];
		int temp = 0;
		String op = "";
	
		//System.out.println("Set length: "+set.length);
		for(int i=0; i<s.length; i++)
		{
			s[i] = i;
			t[i] = key[i%keylen];
		}
		
		System.out.println("S :"+Arrays.toString(s));
		System.out.println("\nT: "+Arrays.toString(t));
		
		for(int i=0, j=0; i<s.length; i++)
		{
			j = (j+s[i]+t[i])%s.length;
			
			//swapping//
			temp = s[i];
			s[i] = s[j];
			s[j] = temp;
			temp = 0;
			
			//System.out.println("S :"+Arrays.toString(s));
			//System.out.println("s["+i+"]= "+s[i]+" s["+j+"]= "+s[j]);
		}
		
		System.out.println("\nS :"+Arrays.toString(s));
		
		int i=0, j=0, t1=0,k =0, x=plain.length;
		while(x>0)
		{
			i = (i+1)%s.length;
			j = (j+s[i])%s.length;
			
			//swapping//
			temp = s[i];
			s[i] = s[j];
			s[j] = temp;
			temp = 0;
			
			t1 = (s[i]+s[j])%s.length;
			key2[k] = s[t1];
			k++;
			x--;
		}
		
		System.out.println("Cipher Key :"+Arrays.toString(key2));
		for(int n=0; n<result.length; n++)
			result[n] = (plain[n]^key2[n])%s.length;
			
		//System.out.println("Operated Text(int) : "+Arrays.toString(result));
		
		char optxt[] = new char[result.length];
		
		for(int a=0; a<optxt.length; a++)
				optxt[a] = set[result[a]];
			
		if(dec == 1)
			op = "Encrypted";
		else if(dec == 2)
				op = "Decrypted";
		System.out.println(op+" Text : "+Arrays.toString(optxt));
	}
	
	public static void main(String args[])
	{
		String plaintext;
		char msgarray[];
		int msgint[];
		
		String key;
		char keychar[];
		int keyint[];
		
		String choice;
		int choiceint = 0;
		int err1 = 0;
		int err2 = 0;
		String decision;
		int decint = 0;
		int err3 = 0;
		int dec = 0;
		
		Scanner src = new Scanner(System.in);
		
		char set[] = {' ','!','"','#','$','%','&','\'','(',')','*','+',',','-','.','/','0','1','2','3','4','5','6','7','8','9',':',';','<','=','>','?','@','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','[','\\',']','^','_','`','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','{','|','}','~'};
		System.out.print(set.length);

		do
		{
			do
			{
				System.out.println("What do you want to perform?\n1. Encryption \t 2. Decryption \t 3. Exit");
				decision = src.next();
				if(!decision.matches("-?\\d+"))
				{
					err3 = 1;
					System.out.println("Enter only integer!");
				}
				else
				{
					decint = Integer.parseInt(decision);
					if(decint==1 || decint==2 || decint==3)
					{
						err3 = 0;
						if(decint == 3)
							System.exit(0);
					}
					else
					{
						err3 = 1;
						System.out.println("Enter a valid choice!");
					}
					
				}
			}while(err3 == 1);
			src.nextLine();
			switch(decint)
			{
				case 1 :  System.out.println("Encryption"); dec = 1;
				break;
				case 2 :  System.out.println("Decryption"); dec = 2;
				break;
				case 3 : System.exit(0);
				default:
			}
			
			
			
			System.out.print("Enter plain text : ");
			plaintext = src.nextLine();
			msgarray = plaintext.toCharArray();
			System.out.println(Arrays.toString(msgarray));
			
			msgint = new int[msgarray.length];
			for(int i=0; i<msgint.length; i++)
				for(int j=0; j<set.length; j++)
				{
					if(msgarray[i] == set[j])
					{
						msgint[i] = j;
						continue;
					}
				}
			System.out.println(Arrays.toString(msgint));
			
			do
			{
				System.out.print("\nEnter key : ");
				key = src.nextLine();
				keychar = key.toCharArray();
				
				if(msgint.length >= keychar.length)
					err2 = 0;
				else
				{
					err2 = 1;
					System.out.println("Enter a valid key with valid length!");
				}
			}while(err2 == 1);
			System.out.println(Arrays.toString(keychar));
			keyint = new int[keychar.length];
			for(int i=0; i<keychar.length; i++)
				for(int j=0; j<set.length; j++)
				{
					if(keychar[i] == set[j])
					{
						keyint[i] = j;
						continue;
					}
				}
				
			System.out.println(Arrays.toString(keyint));
			
			
			operate(msgint, keyint, set, dec);
			
			do
			{
				System.out.println("Do you want to continue?\n1.Yes\t2.No");
				choice = src.next();
				if(!choice.matches("-?\\d+"))
				{
					err1 = 1;
					System.out.println("Enter only integer");
				}
				else
				{
					choiceint = Integer.parseInt(choice);
					if(choiceint==1 || choiceint==2)
						err1 = 0;
					else
					{
						err1 = 1;
						System.out.println("Enter a valid choice");
					}
				}
			
			}while(err1 == 1);
			
			src.nextLine();
			
		}while(choiceint == 1);
	}
}