import java.util.*;
class Prog1
{
	public static int muli(int a, int b)
	{
		int mi[] = new int[19];
		int counter[] = new int[b+1];
		int x=0,i=0,flag=0;
		l:for(i=1; i<=b; i++)
		{
			for(int j=1; j<=b; j++)
				counter[(j*i)%b]++;
			System.out.println(Arrays.toString(counter));
			
			for(int k=1; k<=b; k++)
				if(counter[k]==1)
					flag=1;
				else
				{
					flag = 0;
					continue l;
				}
				
			if(flag==1)
			{
				mi[x] = i;
				x++;
			}
		}
					
		System.out.println("All possible MI's : "+Arrays.toString(mi));
        return 1;
	}
	public static void encrypt(char alpha1[], char alpha2[], char inparray[], int key)
	{
		
			System.out.println("\nPlain Text : "+Arrays.toString(inparray));
			
			int temp[] = new int[inparray.length];
			char result[] = new char[temp.length];
			
			int k = 0;
			System.out.print("Index Values : ");
			for(int i=0; i<inparray.length; i++)
			{
				for(int j=0; j<alpha1.length; j++)
				{
					if(inparray[i]==alpha1[j] || inparray[i]==alpha2[j])
					{
						temp[k] = j;
						k++;	
					}
				}
			}
			System.out.println(Arrays.toString(temp));
			System.out.println("Key : "+key);
			
			System.out.println("\nENCRYPTION : ");
			for(int i=0; i<temp.length; i++)
			{
				if(inparray[i]==alpha1[temp[i]])
				{
					temp[i] = (temp[i]*key+key)%26;
					result[i] = alpha1[temp[i]];
				}
			/* 	else
				{
					temp[i] = (temp[i]*key+key)%26;
					result[i] = alpha2[temp[i]];
				} */
			}
			
			/* for(int i=0; i<temp.length; i++)
			{
				if(inparray[i]==alpha1[temp[i]])
				{
					temp[i] = (temp[i]+key)%26;
					result[i] = alpha1[temp[i]];
				}
			} */
			
			
			System.out.println("The Encrypted indexes are : "+Arrays.toString(temp));
			System.out.println("The Encrypted Code is : "+Arrays.toString(result));
			
		//decrypt(alpha1, alpha2, result, key);
	}
	
	public static void decrypt(char alpha1[], char alpha2[], char inresult[], int key)
	{
		System.out.println("\nDECRYPTION :");
		int temp[] = new int[inresult.length];
		int k = 0;
		char result[] = new char[temp.length];
		System.out.print("Indexes Received from Encryption are : ");
		for(int i=0; i<inresult.length; i++)
		{
			for(int j=0; j<alpha1.length; j++)
			{
				if(inresult[i]==alpha1[j] || inresult[i]==alpha2[j])
				{
					temp[k] = j;
					k++;
				}		
			}
		}
		System.out.println(Arrays.toString(temp));
		
		for(int i=0; i<temp.length; i++)
		{
			if(inresult[i]==alpha1[temp[i]])
			{
				temp[i] = ((temp[i]-key)*muli(key,26))%26;
				
				 if(temp[i]<0)
					temp[i]+=26;
				result[i] = alpha1[temp[i]];
			}
			/* else
			{
				temp[i] = (temp[i]*muli(key,26))%26;
				/* if(temp[i]<0)
					temp[i]+=26;
				result[i] = alpha2[temp[i]];
			}*/
		} 
		
		System.out.println("The Decrypted indexes are : "+Arrays.toString(temp));
		System.out.println("The Decrypted Code is : "+Arrays.toString(result));
	}
	public static void main(String args[])
	{	
		char decision = 'y';
		char decision2 = 'y';
		int err0 = 0;
		int err1 = 0;
		int err2 = 0;
		int err3 = 0;
		int err4 = 0;
		String choice;
		int choice1=0;
		do
		{
			Scanner src = new Scanner(System.in);
			char alpha1[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
			char alpha2[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
			char inparray[];
			
			do
			{				
				System.out.println("What do you want to perform?\n1. Encryption \t 2. Decryption \t 3. Exit?");
				choice = src.next();
				if(!choice.matches("-?\\d+"))
				{
					err0 = 1;
					System.out.println("Enter only integer!");
				}
				else
				{
					choice1 = Integer.parseInt(choice);
					if(choice1<1 && choice1>3)
					{
						err0 = 1;
						System.out.println("Enter a valid choice");
					}
				}
			}while(err0 == 1);
			
			if(choice1 == 3)
				System.exit(0);
		
			do
			{
				System.out.println("Enter Plain Text : ");				
				String input = src.next();				
				inparray = input.toCharArray();
				
				for(int i=0; i<inparray.length; i++)
				{
					if( (inparray[i]>64 && inparray[i]<91) || (inparray[i]>96 && inparray[i]<123) )
						err1=0;
					else
					{
						err1=1;
						break;
					}
				}
				
				if(err1 == 1)
					System.out.println("Only alphabets allowed");
				
			}while(err1 == 1);
			
			String key;
			int key1=0;
			
			do
			{
				do
				{
					System.out.println("Enter The Key For Encryption : ");
					key = src.next();
					if(!key.matches("-?\\d+"))
					{
						err2 =1;
						System.out.println("Enter only integer key!");
					}
					else
					{
						key1 = Integer.parseInt(key);
						if(key1<0)
						{
							System.out.println("Enter positive key only!");
							err2 = 1;
						}
						else 
							err2 = 0;
					}
					
				}while(err2 == 1);
				
				if(choice1 == 1)
					encrypt(alpha1, alpha2, inparray, key1);
				else if(choice1 == 2)
						decrypt(alpha1, alpha2, inparray, key1);
				
				do
				{
					System.out.println("\nDo you want to continue with same plain text?");
					System.out.println("If yes then press 'y' or to exit press 'n'.");
					decision = src.next().charAt(0);
					if(decision=='y' || decision=='Y' || decision=='n' || decision=='N') 					
						err3 = 0;
					else
					{
						err3 = 1;
						System.out.println("Please enter a valid decision!");
					}
				}while(err3 == 1);
				
			}while(decision == 'y' || decision == 'Y');
			
			do
			{
				System.out.println("\nDo you want to continue ?");
				System.out.println("If yes then press 'y' or to exit press 'n'.");	
				decision2 = src.next().charAt(0);
				if(decision2 == 'y' || decision2 == 'Y' || decision2 == 'n' || decision2 == 'N') 					
					err4 = 0;
				else
				{
					err4 = 1;
					System.out.println("Please enter a valid decision!");
				}
			}while(err4 == 1);
			
		}while(decision2 == 'y' || decision2 == 'Y');	
	}
}