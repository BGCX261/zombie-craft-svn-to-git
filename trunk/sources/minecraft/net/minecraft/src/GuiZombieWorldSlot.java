package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.spec.*;

import java.io.*;
import java.util.*;
import java.util.zip.*;
import java.net.URL;
import java.security.*;
import net.minecraft.client.Minecraft;
import java.security.MessageDigest;

class GuiZombieWorldSlot extends GuiWorldSlot
{

    public GuiZombieWorldSlot(GuiSelectWorld guiselectworld)
    {
        super(guiselectworld);
		selectWorldGui = guiselectworld;
    }
	
	static public boolean deleteDirectory(File path) {
	if(path.exists() && path.isDirectory()) {
	  File[] files = path.listFiles();
	  for(int i=0; i<files.length; i++) {
		 if(files[i].isDirectory()) {
		   deleteDirectory(files[i]);
		 }
		 else {
		   files[i].delete();
		 }
	  }
	  return( path.delete() );
	} else {
	return false;
	}
  }

	protected void func_22247_a(int i, boolean flag)
    {
		GuiSelectWorld.func_22089_a(field_22266_a, i);
        boolean flag1 = GuiSelectWorld.func_22086_b(field_22266_a) >= 0 && GuiSelectWorld.func_22086_b(field_22266_a) < func_22249_a();
		if(flag && flag1)
        {
			// Create encrypter/decrypter class
			zz encrypter = new zz("Zombiecraft is tasty!");

		    // Encrypt
		    //String encrypted = encrypter.encrypt("Don't tell anybody!");
		    
			//Strings to encrypt
		    //System.out.println(encrypter.encrypt("zcmaps"));
		    //System.out.println(encrypter.encrypt("zctemp"));
		    //System.out.println(encrypter.encrypt(".zip"));
		    //System.out.println(encrypter.encrypt("region"));
		    //System.out.println(encrypter.encrypt("r.0.0.mcr"));
		    //System.out.println(encrypter.encrypt("http://modzilla.zymichost.com/store/haspaid.php?user="));
		    //System.out.println(encrypter.encrypt("&mapid="));
		    //System.out.println(encrypter.encrypt("&md5="));
		    
		    //Encrypted results
		    //xToToq+1sng=
		    //rKRSisSMbCk=
		    //AvnvRdcU3oU=
		    //be5RyWQXzcM=
		    //aTm7Jt3UYMdB9O1sF4IfgQ==
		    //Png6B83+j+VGJz6H3AxK25wpR4rQde5Uzynd1mtqxW7BWQ7LHW29TUvjgCry0uDj5/Ohs8xLmAY=
		    //Xp6gmzWvEFk=
		    //BCUegjYEKn0=
			
			// Decrypt
		    //encrypter.decrypt("BCUegjYEKn0=")
		    
			String zipname = ((ZombieSaveRecord)selectWorldGui.func_22090_a(selectWorldGui).get(i)).getZipname();
			File file3 = new File(Minecraft.getMinecraftDir(), encrypter.decrypt("xToToq+1sng="));			
			File file2 = new File(file3, zipname);
			deleteDirectory(new File(file3, zipname.substring(0,zipname.lastIndexOf(encrypter.decrypt("AvnvRdcU3oU=")))));
			File file = new File(Minecraft.getMinecraftDir(), encrypter.decrypt("rKRSisSMbCk="));
			File unzipDir = new File(file, zipname.substring(0,zipname.lastIndexOf(encrypter.decrypt("AvnvRdcU3oU="))));
			recursiveUnzip(file2,unzipDir);

			try
			{
				File mapFile = new File(new File(unzipDir,encrypter.decrypt("be5RyWQXzcM=")),encrypter.decrypt("aTm7Jt3UYMdB9O1sF4IfgQ=="));
				InputStream is = new FileInputStream(mapFile);
				long length = mapFile.length();
				//System.out.println(length);
				if (length > Integer.MAX_VALUE) {
					System.out.println("File is too large");
					return;
				}
				byte[] bytes = new byte[(int)length];
				int offset = 0;
				int numRead = 0;
				while (offset < bytes.length
					   && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
					offset += numRead;
				}
				if (offset < bytes.length) {
					throw new IOException("Could not completely read file "+mapFile.getName());
				}
				//System.out.println(bytes[bytes.length-1]);
				is.close();
				
				try {
				    
				} catch (Exception e) {
				}


				
				try
				{
					String md5 = getMD5Checksum(file2);
					String md5list[] = { "e756fb7619439c7e89f74e134fa79b10", "528abaa8d8cd03a3fe8b1735e4dd592e", "" };
					for (i = 0; i < md5list.length; i++) {
						if (md5.equals(md5list[i])) {
							selectZombieWorld(zipname.substring(0,zipname.lastIndexOf(encrypter.decrypt("AvnvRdcU3oU="))));
							return;
						}
					}
					URL url = new URL((new StringBuilder()).append(encrypter.decrypt("Png6B83+j+VGJz6H3AxK25wpR4rQde5Uzynd1mtqxW7BWQ7LHW29TUvjgCry0uDj5/Ohs8xLmAY=")).append(ModLoader.getMinecraftInstance().session.username).append(encrypter.decrypt("Xp6gmzWvEFk=")).append(bytes[bytes.length-1]).append(encrypter.decrypt("BCUegjYEKn0=")).append(md5).toString());
					//System.out.println(url);
					BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(url.openStream()));
					String s1 = bufferedreader.readLine();
					bufferedreader.close();
					if(s1.equals("true"))
					{
						selectZombieWorld(zipname.substring(0,zipname.lastIndexOf(encrypter.decrypt("AvnvRdcU3oU="))));
						//Premium
					} else
					{
						Minecraft mc = ModLoader.getMinecraftInstance();
						mc.displayGuiScreen(new GuiNotPremium(selectWorldGui));        
						//Not premium
					}
				}
				catch(Exception exception)
				{
					exception.printStackTrace();
				}
			} catch (Exception ex) { ex.printStackTrace(); }
		}
	}
	
	public void selectZombieWorld(String s)
    {
		Minecraft mc = ModLoader.getMinecraftInstance();
        mc.displayGuiScreen(null);
        if(selected)
        {
            return;
        }
        selected = true;
        mc.playerController = new PlayerControllerSP(mc);
		//System.out.println(s);
        mc.startWorld(s, s, 0L);
        mc.displayGuiScreen(null);
    }
	
    protected void func_22242_a(int i, int j, int k, int l, Tessellator tessellator)
    {
        ZombieSaveRecord zombiesaverecord = (ZombieSaveRecord)selectWorldGui.func_22090_a(selectWorldGui).get(i);
        selectWorldGui.drawString(selectWorldGui.fontRenderer, zombiesaverecord.getText(), j + 2, k + 1, 0xffffff);
        selectWorldGui.drawString(selectWorldGui.fontRenderer, zombiesaverecord.getSubText(), j + 2, k + 12, 0x808080);
        selectWorldGui.drawString(selectWorldGui.fontRenderer, zombiesaverecord.getSubText2(), j + 2, k + 12 + 10, 0x808080);
    }
	
	private final static int BUFFER = 10241024;  
	
    private void cleanUp(InputStream in) throws Exception{  
         in.close();  
    }
	
	private void cleanUp(OutputStream out) throws Exception{  
         out.flush();  
         out.close();  
    } 

	public static byte[] createChecksum(File file) throws Exception
	{
		InputStream fis =  new FileInputStream(file);

		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;
		do {
		numRead = fis.read(buffer);
		if (numRead > 0) {
		complete.update(buffer, 0, numRead);
		}
		} while (numRead != -1);
		fis.close();
		return complete.digest();
	}

   // see this How-to for a faster way to convert
   // a byte array to a HEX string
	public static String getMD5Checksum(File file) throws Exception {
		byte[] b = createChecksum(file);
		String result = "";
		for (int i=0; i < b.length; i++) {
		result +=
		  Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		}
		return result;
	}
	
	public File recursiveUnzip(File inFile, File outFolder)  
    {  
         try  
         {  
              createFolder(outFolder, true);  
              BufferedOutputStream out = null;  
              ZipInputStream  in = new ZipInputStream(new BufferedInputStream(new FileInputStream(inFile)));  
              ZipEntry entry;  
              while((entry = in.getNextEntry()) != null)  
              {  
                   //System.out.println("Extracting: " + entry);  
                   int count;  
                   byte data[] = new byte[BUFFER];  
                     
                   // write the files to the disk  
                   File newFile = new File(outFolder.getPath() + "/" + entry.getName());  
                   Stack<File> pathStack = new Stack<File>();  
                   File newNevigate = newFile.getParentFile();  
                   while(newNevigate != null){  		
                       pathStack.push(newNevigate);  
                       newNevigate = newNevigate.getParentFile();  
                   }  
                   //create all the path directories  
                   while(!pathStack.isEmpty()){  
                       File createFile = pathStack.pop(); 
                       createFolder(createFile, true);  
                   }  
                   if(!entry.isDirectory()){  
                         out = new BufferedOutputStream(  
                                   new FileOutputStream(newFile),BUFFER);  
                         while ((count = in.read(data,0,BUFFER)) != -1){  
                              out.write(data,0,count);  
                         }  
                         cleanUp(out);  
                         //recursively unzip files  
                         //if(entry.getName().toLowerCase().endsWith(".zip")){  
                           //  String zipFilePath = outFolder.getPath() + "/" + entry.getName();  
                           //  this.recursiveUnzip(new File(zipFilePath), new File(zipFilePath.substring(0, zipFilePath.length()-4)));  
                         //}  
                   }else{  
                       
					   //this.createFolder(new File(outFolder,entry.getName()), true);  
                   }  
              }  
              cleanUp(in);  
              return outFolder;  
         }catch(Exception e){  
              e.printStackTrace();  
              return inFile;  
         }  
    }  
	
	private void createFolder(File folder, boolean isDirectory){  
        if(isDirectory){  
            folder.mkdir();  
        }  
    } 
	
	private GuiSelectWorld selectWorldGui;
	private boolean selected;
}
