// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   Util.java

package net.minecraft;

import java.io.*;
import java.net.URL;
import java.security.PublicKey;
import java.security.cert.Certificate;
import javax.net.ssl.HttpsURLConnection;

public class Util {
    private static final class OS extends Enum {

        public static final OS linux;
        public static final OS solaris;
        public static final OS windows;
        public static final OS macos;
        public static final OS unknown;
        private static final OS ENUM$VALUES[];

        public static OS[] values() {
            OS aos[];
            int i;
            OS aos1[];
            System.arraycopy(((Object) (aos = ENUM$VALUES)), 0, ((Object) (aos1 = new OS[i = aos.length])), 0, i);
            return aos1;
        }

        public static OS valueOf(String s) {
            return (OS)Enum.valueOf(net.minecraft.Util$OS.class, s);
        }

        static  {
            linux = new OS("linux", 0);
            solaris = new OS("solaris", 1);
            windows = new OS("windows", 2);
            macos = new OS("macos", 3);
            unknown = new OS("unknown", 4);
            ENUM$VALUES = (new OS[] {
                               linux, solaris, windows, macos, unknown
                           });
        }

        private OS(String s, int i) {
            super(s, i);
        }
    }


    private static File workDir = null;
    private static int $SWITCH_TABLE$net$minecraft$Util$OS[]; /* synthetic field */

    public Util() {
    }

    public static File getWorkingDirectory() {
        if(workDir == null)
            workDir = getWorkingDirectory("minecraft");

        return workDir;
    }

    public static File getWorkingDirectory(String applicationName) {
        String userHome = System.getProperty("user.home", ".");
        File workingDirectory;

        switch($SWITCH_TABLE$net$minecraft$Util$OS()[getPlatform().ordinal()]) {
        case 1: // '\001'
        case 2: // '\002'
            workingDirectory = new File(userHome, (new StringBuilder(String.valueOf('.'))).append(applicationName).append('/').toString());
            break;
        case 3: // '\003'
            String applicationData = System.getenv("APPDATA");

            if(applicationData != null)
                workingDirectory = new File(applicationData, (new StringBuilder(".")).append(applicationName).append('/').toString());
            else
                workingDirectory = new File(userHome, (new StringBuilder(String.valueOf('.'))).append(applicationName).append('/').toString());

            break;
        case 4: // '\004'
            workingDirectory = new File(userHome, (new StringBuilder("Library/Application Support/")).append(applicationName).toString());
            break;
        default:
            workingDirectory = new File(userHome, (new StringBuilder(String.valueOf(((Object) (applicationName))))).append('/').toString());
            break;
        }

        if(!workingDirectory.exists() && !workingDirectory.mkdirs())
            throw new RuntimeException((new StringBuilder("The working directory could not be created: ")).append(((Object) (workingDirectory))).toString());
        else
            return workingDirectory;
    }

    private static OS getPlatform() {
        String osName = System.getProperty("os.name").toLowerCase();

        if(osName.contains("win"))
            return OS.windows;

        if(osName.contains("mac"))
            return OS.macos;

        if(osName.contains("solaris"))
            return OS.solaris;

        if(osName.contains("sunos"))
            return OS.solaris;

        if(osName.contains("linux"))
            return OS.linux;

        if(osName.contains("unix"))
            return OS.linux;
        else
            return OS.unknown;
    }

    public static String excutePost(String targetURL, String urlParameters) {
        HttpsURLConnection connection = null;

        try {
            try {
                URL url = new URL(targetURL);
                connection = (HttpsURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length", (new StringBuilder()).append(Integer.toString(urlParameters.getBytes().length)).toString());
                connection.setRequestProperty("Content-Language", "en-US");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();
                Certificate certs[] = connection.getServerCertificates();
                byte bytes[] = new byte[294];
                DataInputStream dis = new DataInputStream((net.minecraft.Util.class).getResourceAsStream("minecraft.key"));
                dis.readFully(bytes);
                dis.close();
                Certificate c = certs[0];
                PublicKey pk = c.getPublicKey();
                byte data[] = pk.getEncoded();

                for(int i = 0; i < data.length; i++)
                    if(data[i] != bytes[i])
                        throw new RuntimeException("Public key mismatch");

                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();
                java.io.InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(((java.io.Reader) (new InputStreamReader(is))));
                StringBuffer response = new StringBuffer();
                String line;

                while((line = rd.readLine()) != null)  {
                    response.append(line);
                    response.append('\r');
                }

                rd.close();
                String s = response.toString();
                return s;
            } catch(Exception e) {
                e.printStackTrace();
            }

            return null;
        } finally {
            if(connection != null)
                connection.disconnect();
        }
    }

    static int[] $SWITCH_TABLE$net$minecraft$Util$OS() {
        $SWITCH_TABLE$net$minecraft$Util$OS;

        if($SWITCH_TABLE$net$minecraft$Util$OS == null) goto _L2;
        else goto _L1
            _L1:
            return;

        _L2:
        JVM INSTR pop ;
        int ai[] = new int[OS.values().length];

        try {
            ai[OS.linux.ordinal()] = 1;
        } catch(NoSuchFieldError _ex) { }

        try {
            ai[OS.macos.ordinal()] = 4;
        } catch(NoSuchFieldError _ex) { }

        try {
            ai[OS.solaris.ordinal()] = 2;
        } catch(NoSuchFieldError _ex) { }

        try {
            ai[OS.unknown.ordinal()] = 5;
        } catch(NoSuchFieldError _ex) { }

        try {
            ai[OS.windows.ordinal()] = 3;
        } catch(NoSuchFieldError _ex) { }

        return $SWITCH_TABLE$net$minecraft$Util$OS = ai;
    }

}
