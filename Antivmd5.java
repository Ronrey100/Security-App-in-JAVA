/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
public class Antivmd5 {
    static int file_num=0;
    public static void prnt_files(File fl)
    {
        String[] f1=fl.list();
        int l=f1.length;
        String in_f;
        FileInputStream fis = null;
         File[] f2=fl.listFiles();
        for(int i=0; i<l; i++)
            {
                if(f2[i].isDirectory())
                    {
                        
                  prnt_files(f2[i]);
                        continue;
                     }
                file_num++;
                System.out.println(f2[i]);
               // in_f= f2[i];
                //System.out.println("file in str format "+in_f);
			try {
					fis = new FileInputStream(f2[i]);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
                checkfor(fis);
           
            }
    }
	static String[] checkh={"90eacac988a408a68939abc64ce8d0f6","3620927f1d332bd53ba88e4d2aac08b3,8B78190E81E32C46C94B7C34A9B3C81E,8B78190E81E32C46C94B7C34A9B3C81E,8B78190E81E32C46C94B7C34A9B3C81E"};
			
	    public static String getMD5(String input) {
	        byte[] source;
	        try {
	            //Get byte according by specified coding.
	            source = input.getBytes("UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            source = input.getBytes();
	        }
	        String result = null;
	        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7',
	                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(source);
	            //The result should be one 128 integer
	            byte temp[] = md.digest();
	            char str[] = new char[16 * 2];
	            int k = 0;
	            for (int i = 0; i < 16; i++) {
	                byte byte0 = temp[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            result = new String(str);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	 public static void checkfor(FileInputStream fis)
	 {
		 
		 MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	        byte[] dataBytes = new byte[1024];
	     
	        int nread = 0;
	        try {
				while ((nread = ( fis).read(dataBytes)) != -1) {
				    md.update(dataBytes, 0, nread);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
	        byte[] mdbytes = md.digest();
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < mdbytes.length; i++) {
	            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        int flag=0;
	        String mdis=sb.toString();
	        System.out.print("Digest(in hex format):: " + mdis);
	       int i=0;
	        while(i<1)
	        {
	        	if(mdis.equals(checkh[i]))
                         
	        	{
	        		System.out.println("\nVirus detected "+fis);
	        		 flag=1;
	        	}	
	        	i++;
	        }
	        if(flag==0)
	        	System.out.println("\nno virus found");
	       
	 }
	    public static void main(String[] args) throws NoSuchAlgorithmException {
	       int ch=0;
	    	//System.out.println(getMD5("bhushan112313"));
	        FileInputStream fis = null;
	        String in_f = null;
	        String cha;
	        Scanner input=new Scanner(System.in);
	        do{
	        System.out.println("\n0. file 1. folder 2.exit ");
	        
	        ch=input.nextInt();
		     cha=input.nextLine();
		     if(ch==0)
		     {
		    	   System.out.println("input a file with full address e.g c:\\patch.exe ");
			       in_f=input.nextLine();
			        
					try {
						fis = new FileInputStream(in_f);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	 checkfor(fis);
					
		     }
		     else if (ch==1)
		     { System.out.println("input a file with full address e.g c:\\patch.exe ");
		       String in=input.nextLine();
		    	File infolder=new File(in); 
		       prnt_files(infolder);
		     }
			
	        
	    }while(ch!=2);
	}
}
