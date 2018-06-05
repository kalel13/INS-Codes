import java.util.*;
class Prog2
{
	public static void operation(char msg[][], int dec)
	{
		Scanner src = new Scanner(System.in);
		
		// INPUTTING ALL VARIABLES //
		String keychoice, operation;
		String key[] = new String[msg.length*msg[0].length];
		
		int keychint = 0, err5 = 0, opint = 0, err6 = 0, err7 = 0, err8 = 0;
		int keyint[];
	
		char temp[][] = new char[msg.length][msg[0].length];
		
		do{
			// ASKING FOR OPERATIONS ( WITH VALIDATION ) //
			do
			{
				System.out.println("Which operation you want to perform?\n1. Row operation \t 2. Column operation");
				operation = src.next();
				
				if(!operation.matches("-?\\d+"))
				{
					err6 = 1;
					System.out.println("Enter only integer!");
				}
				else
				{
					opint = Integer.parseInt(operation);
					if(opint==1 || opint==2)
						err6 = 0;
					else
					{
						err6 = 1;
						System.out.println("Enter a valid choice!");
					}
				}
			}while(err6 == 1);
			
			// DECIDING LENGTH OF KEY BASED ON OPERATION //
			if(opint == 1)
				key = new String[msg.length];
			else if(opint == 2)
					key = new String[msg[0].length];
			
			System.out.println("Length of key should be : "+key.length);
			keyint = new int[key.length];
			
			// INPUTTING KEY SEQUENCE ( WITH VALIDATION ) //
			do
			{
				System.out.println("Enter the sequence key for operation (Insert space after every element) or (Press Enter after each element): ");
				
				for(int i=0; i<key.length; i++)
					key[i] = src.next();
				
				for(int i=0; i<key.length; i++)
				{
					// CHECKING FOR INTEGERS //
					if(!key[i].matches("-?\\d+"))
					{
						err8 = 1;
						System.out.println("Enter only integer!");
						break;
					}
					else
					{	
						// CHECKING FOR RANGE //
						keyint[i] = Integer.parseInt(key[i]);
						if(opint == 1)
						{
							if(keyint[i]>=0 && keyint[i]<msg.length)
								err8 = 0;
							else
							{
								err8 = 1;
								System.out.println("Enter key from a valid range");
								break;
							}
						}
						else if(opint == 2)
							 {
								 if(keyint[i]>=0 && keyint[i]<msg[0].length)
									 err8 = 0;									 						 
								 else
								 {
									 err8 = 1;
									 System.out.println("Enter key from a valid range");
									 break;		
								 }
							 }
					}
				}
				
				// CHECKING FOR UNIQUENESS OF KEY //
				if(err8 != 1)
				{
					if(allUnique(keyint)==0)
					{
						err8 = 1;
						System.out.println("Enter all unique elements for key!");
					}
					else 
						err8 = 0; 
				}
				
			}while(err8 == 1);
			 
			System.out.println("Entered Key : "+Arrays.toString(keyint));
			
			// MANIPULATING INPUT-MATRIX BASED ON OPERATION //
			switch(opint)
			{
				case 1 : // LOGIC FOR ROW OPERATION //
						 for(int i=0; i<temp.length; i++)
							for(int j=0; j<temp[0].length; j++)
								temp[i][j] = msg[keyint[i]][j];

							
						for(int i=0; i<temp.length; i++)
							for(int j=0; j<temp[0].length; j++)
								msg[i][j] = temp[i][j];
						
				break;
				
				case 2 : // LOGIC FOR COLUMN OPERATION //
						 for(int i=0; i<temp.length; i++)
							for(int j=0; j<temp[0].length; j++)
								temp[i][j] = msg[i][keyint[j]];								
							
						for(int i=0; i<temp.length; i++)
							for(int j=0; j<temp[0].length; j++)
								msg[i][j] = temp[i][j];
				break;

				default : System.out.println("Invalid operation");
			}
			
			// DISPLAYING THE INPUT MATRIX AFTER EACH OPERATION //
			for(int i=0; i<temp.length; i++)
			{
				for(int j=0; j<temp[0].length; j++)
					System.out.print(temp[i][j]+" ");
				System.out.println();
			}
			
			// ASKING FOR CONTINUATION ( WITH VALIDATIONS ) //
			do
			{
				System.out.println("Do you want to perform any operation?\n1. Yes \t 2. No");
				keychoice = src.next();
				
				if(!keychoice.matches("-?\\d+"))
				{
					err5 = 1;
					System.out.println("Enter only integer!");
				}
				else
				{
					keychint = Integer.parseInt(keychoice);
					if(keychint==1 || keychint==2)
						err5 = 0;
					else
					{
						err5 = 1;
						System.out.println("Enter a valid choice!");
					}
				}
			}while(err5 == 1);
			
		}while(keychint == 1);
		
		// DISPLAYING THE FINAL MESSAGE //
		if(dec==1)
			System.out.print("\nEncrypted Message is : ");
		else
			System.out.print("\nDecrypted Message is : ");
		
		for(int i=0; i<temp.length; i++)
			for(int j=0; j<temp[0].length; j++)
				//if(dec == 2)
					if(temp[i][j] == '#')
						continue;
					else
						System.out.print(temp[i][j]);
		
		System.out.println();
			
	}
	
	// FUNCTION TO CHECK UNIQUENESS OF AN ARRAY //
	public static int allUnique(int key[])
	{
		int err = 0;
		l: for(int i=0; i<key.length; i++)
		{
			for(int j=i+1; j<key.length; j++)
			{
				if(key[i]==key[j])
				{
					err = 1;
					break l;
				}
				else
					err = 0;
			}
		}
		
		if(err == 1)
			return 0;
		else 
			return 1;
	}
	
	// FUNCTION TO CHECK IF A NUMBER IS PRIME //
	public static int checkPrime(int len)
	{
		int count = 0;
		for(int i=1; i<=len; i++)
		{
			if(len%i==0)
				count++;
		}
		if(count==2)
			return 1;
		else 
			return 0;
		
	}
	
	// FUNCTION TO CALCULATE ALL POSSIBLE MATRIX SIZE BASED ON LENGTH OF THE PLAIN TEXT //
	public static int[] calFacs(int len)
	{
		int count=0;
		for(int i=2; i<len; i++)
			if(len%i==0)
				count++;
			
		int arr[] = new int[count];
		int temp[] = new int[2*count];
		int result[]; 
		
		// CALCULATING ALL FACTORS //
		for(int i=2,k=0; i<len; i++)
			if(len%i==0)
			{	
				arr[k] = i;
				k++;
			}
		
		// LOGIC FOR ONLY ONE FACTOR //
		int k=0;
		if(arr.length==1)
		{
			result = new int[2*arr.length];
			result[0] = arr[k];
			result[1] = arr[k];
			return result;
		}
		else
		{
			// LOGIC FOR MORE THAN ONE FACTORS //
			result = new int[(temp.length)+2];
			
			int x=0;
			for(int i=0; i<arr.length; i++)
			{
				// FOR SAME ELEMENT //
				if(arr[i]*arr[i]==len)
				{
					temp[x] = arr[i];
					temp[x+1] = arr[i];
					x=x+2;	
				}
				else
				{
					// FOR DIFFERENT ELEMENTS //
					for(int j=i+1; j<arr.length; j++)
						if(arr[i]*arr[j]==len)
						{
							temp[x] = arr[i];
							temp[x+1] = arr[j];
							x=x+2;
						}	
				}
			}
			
			// FORMATION OF RESULT MATRIX WITH ALL POSSIBILITIES //
			for(int i=0,n=0; i<result.length; i=i+4,n=n+2)
			{
				if(temp[n]==0)
					continue;
				else
				{
					result[i] = temp[n];
					result[i+1] = temp[n+1];
					result[i+2] = temp[n+1];
					result[i+3] = temp[n];
				}
			}
			
			// DISCARDING ONE EXTRA PAIR OF SAME ELEMENTS // 
			int result1[] = new int[result.length-2];
			for(int i=0; i<result1.length; i++)
				result1[i] = result[i];
			
			return result1;	
		}	
	}
	public static void main(String args[])
	{
		Scanner src = new Scanner(System.in);

		// INPUTTING ALL VARIABLES //
		int primeFlag = 0, err1 = 0, err2 = 0, err3 = 0, err4 = 0, count=0, chint = 0, m=0, n=0, choiceint = 0, decint = 0, len = 0;
		int factors[];
		
		String ch, choice, decision;
		
		char inp[];
		
		do{
			// INPUTTING PLAIN TEXT ( WITH VALIDATION ) //
			do
			{
				System.out.print("Enter Plain Text : ");
				String input = src.next();
				inp = input.toCharArray();
				len = inp.length;
				
				for(int i=0; i<len; i++)
				{
					if( (inp[i]>64 && inp[i]<91) || (inp[i]>96 && inp[i]<123) )
						err1 = 0;
					else 
					{
						if(inp[i] == 32)
						{
							err1 = 1;
							System.out.println("No spaces allowed!");
							break;
						}
						else
						{
							err1 = 1;
							System.out.println("Only alphabets allowed!");
							break;
						}	
					}
				}
		
			}while(err1 == 1);	
			
			System.out.println("Plain Text :"+Arrays.toString(inp));
			System.out.println("Length of plaintext is : "+len);
			
			// CALCULATING POSSIBLE OPTIONS //
			if(len==1 || len==2)
			{
				len = 4;
				factors = calFacs(len);
			}
			else
			{
				if(checkPrime(len)==1)
				{
					len++;
					factors = calFacs(len);
				}
				else
					factors = calFacs(len);
			}
			
			// DISPLAYING POSSIBLE OPTIONS BASED ON LENGTH OF THE INPUT PLAIN TEXT //
			count = factors.length/2;
			int k=0;
			int options[][] = new int[count][2];
			System.out.println("So Options available are (row,column) :  ");
			for(int i=0; i<count; i++)
			{
				System.out.print((i+1)+". ");
				System.out.print(factors[k]+",");
				System.out.println(factors[k+1]);
				options[i][0] = factors[k];
				options[i][1] = factors[k+1];
				k=k+2;	
			}
			
			// INPUTTING OPTION ( WITH VALIDATION ) //
			do
			{
				System.out.println("Enter your choice: ");
				ch = src.next();
				
				if(!ch.matches("-?\\d+"))
				{
					err2 = 1;
					System.out.println("Enter an integer option!");
				}
				else
				{
					chint = Integer.parseInt(ch);
					if(chint>0 && chint<(options.length+1))
						err2= 0;
					else
					{
						err2 = 1;
						System.out.println("Enter a valid selection");
					}
				}
			}while(err2 == 1);
	
			m = options[chint-1][0];
			n = options[chint-1][1];

			System.out.println("Selected option is : "+m+" "+n);
			
			// FORMATION OF INPUT MATRIX // 
			char msg[][] = new char[m][n];
			int a=0;
			for(int i=0; i<m; i++)
				for(int j=0; j<n; j++)
				{
					if(a-inp.length==2 || a-inp.length==1 || a-inp.length==0)
					{
						msg[i][j] = '#';
						continue;
					}
					else
					{
						msg[i][j] = inp[a];
						a++;
					}
				}
				
			// DISPLAYING THE INPUT MATRIX // 	
			System.out.println("\nInput Matrix would be :");
			for(int i=0; i<m; i++)
			{
				for(int j=0; j<n; j++)
					System.out.print(msg[i][j]+" ");
				System.out.println();
			}
			
			// ASKING FOR OPERATIONS ( WITH VALIDATION ) //
			do
			{
				System.out.println("What do you want to perform ?\n1. Encryption \t 2. Decryption \t 3. Exit");
				choice = src.next();
				
				if(!choice.matches("-?\\d+"))
				{
					err3 = 1;
					System.out.println("Enter only integer!");
				}
				else
				{
					choiceint = Integer.parseInt(choice);
					if(choiceint==1 || choiceint==2 || choiceint==3)
						err3 = 0;
					else
					{
						err3 = 1;
						System.out.println("Enter a valid choice!");
					}
				}
			}while(err3 == 1);
			
			// CALLING OPERATION FUNCTION BASED ON INPUT OPERATION //
			switch(choiceint)
			{
				case 1 : System.out.println("Encryption"); operation(msg, 1);
				break;
				case 2 : System.out.println("Decryption"); operation(msg, 2);
				break;
				default: 
			}
		
			// ASKING FOR DECISION ( WITH VALIDATION ) //
			do
			{
				System.out.println("Do you want to continue?\n1. Yes \t 2. No");
				decision = src.next();
				
				if(!decision.matches("-?\\d+"))
				{
					err4 = 1;
					System.out.println("Enter only integer!");
				}
				else
				{
					decint = Integer.parseInt(decision);
					if(decint==1 || decint==2)
						err4 = 0;
					else
					{
						err4 = 1;
						System.out.println("Enter a valid choice!");
					}
				}
			
			}while(err4 == 1);
				
		}while(decint == 1);
	}
}