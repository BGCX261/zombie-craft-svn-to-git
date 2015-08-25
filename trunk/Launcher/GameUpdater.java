// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   GameUpdater.java

package net.minecraft;

import java.applet.Applet;
import java.io.*;
import java.lang.reflect.*;
import java.math.BigInteger;
import java.net.*;
import java.security.*;
import java.security.cert.Certificate;
import java.util.*;
import java.util.jar.*;

// Referenced classes of package net.minecraft:
//            Launcher, Util

public class GameUpdater
    implements Runnable {

    public static final int STATE_INIT = 1;
    public static final int STATE_DETERMINING_PACKAGES = 2;
    public static final int STATE_CHECKING_CACHE = 3;
    public static final int STATE_DOWNLOADING = 4;
    public static final int STATE_EXTRACTING_PACKAGES = 5;
    public static final int STATE_UPDATING_CLASSPATH = 6;
    public static final int STATE_SWITCHING_APPLET = 7;
    public static final int STATE_INITIALIZE_REAL_APPLET = 8;
    public static final int STATE_START_REAL_APPLET = 9;
    public static final int STATE_DONE = 10;
    public int percentage;
    public int currentSizeDownload;
    public int totalSizeDownload;
    public int currentSizeExtract;
    public int totalSizeExtract;
    protected URL urlList[];
    private static ClassLoader classLoader;
    protected Thread loaderThread;
    protected Thread animationThread;
    public boolean fatalError;
    public String fatalErrorDescription;
    protected String subtaskMessage;
    protected int state;
    protected boolean lzmaSupported;
    protected boolean pack200Supported;
    protected String genericErrorMessage[] = {
        "An error occured while loading the applet.", "Please contact support to resolve this issue.", "<placeholder for error message>"
    };
    protected boolean certificateRefused;
    protected String certificateRefusedMessage[] = {
        "Permissions for Applet Refused.", "Please accept the permissions dialog to allow", "the applet to continue the loading process."
    };
    protected static boolean natives_loaded = false;
    public static boolean forceUpdate = false;
    private String latestVersion;
    private String mainGameUrl;
    public boolean pauseAskUpdate;
    public boolean shouldUpdate;

    public GameUpdater(String latestVersion, String mainGameUrl) {
        subtaskMessage = "";
        state = 1;
        lzmaSupported = false;
        pack200Supported = false;
        this.latestVersion = latestVersion;
        this.mainGameUrl = mainGameUrl;
    }

    public void init() {
        state = 1;

        try {
            Class.forName("LZMA.LzmaInputStream");
            lzmaSupported = true;
        } catch(Throwable throwable) { }

        try {
            (java.util.jar.Pack200.class).getSimpleName();
            pack200Supported = true;
        } catch(Throwable throwable1) { }
    }

    private String generateStacktrace(Exception exception) {
        java.io.Writer result = ((java.io.Writer) (new StringWriter()));
        PrintWriter printWriter = new PrintWriter(result);
        exception.printStackTrace(printWriter);
        return ((Object) (result)).toString();
    }

    protected String getDescriptionForState() {
        switch(state) {
        case 1: // '\001'
            return "Initializing loader";
        case 2: // '\002'
            return "Determining packages to load";
        case 3: // '\003'
            return "Checking cache for existing files";
        case 4: // '\004'
            return "Downloading packages";
        case 5: // '\005'
            return "Extracting downloaded packages";
        case 6: // '\006'
            return "Updating classpath";
        case 7: // '\007'
            return "Switching applet";
        case 8: // '\b'
            return "Initializing real applet";
        case 9: // '\t'
            return "Starting real applet";
        case 10: // '\n'
            return "Done loading";
        }

        return "unknown state";
    }

    protected String trimExtensionByCapabilities(String file) {
        if(!pack200Supported)
            file = file.replaceAll(".pack", "");

        if(!lzmaSupported)
            file = file.replaceAll(".lzma", "");

        return file;
    }

    protected void loadJarURLs() throws Exception {
        state = 2;
        String jarList = (new StringBuilder("lwjgl.jar, jinput.jar, lwjgl_util.jar, ")).append(mainGameUrl).toString();
        jarList = trimExtensionByCapabilities(jarList);
        StringTokenizer jar = new StringTokenizer(jarList, ", ");
        int jarCount = jar.countTokens() + 1;
        urlList = new URL[jarCount];
        URL path = new URL("http://s3.amazonaws.com/MinecraftDownload/");

        for(int i = 0; i < jarCount - 1; i++)
            urlList[i] = new URL(path, jar.nextToken());

        String osName = System.getProperty("os.name");
        String nativeJar = null;

        if(osName.startsWith("Win"))
            nativeJar = "windows_natives.jar.lzma";
        else if(osName.startsWith("Linux"))
            nativeJar = "linux_natives.jar.lzma";
        else if(osName.startsWith("Mac"))
            nativeJar = "macosx_natives.jar.lzma";
        else if(osName.startsWith("Solaris") || osName.startsWith("SunOS"))
            nativeJar = "solaris_natives.jar.lzma";
        else
            fatalErrorOccured((new StringBuilder("OS (")).append(osName).append(") not supported").toString(), ((Exception) (null)));

        if(nativeJar == null) {
            fatalErrorOccured("no lwjgl natives files found", ((Exception) (null)));
        } else {
            nativeJar = trimExtensionByCapabilities(nativeJar);
            urlList[jarCount - 1] = new URL(path, nativeJar);
        }
    }

    public void run() {
        label0: {
            label1: {
                init();
                state = 3;
                percentage = 5;

                try {
                    try {
                        loadJarURLs();
                        String path = (String)AccessController.doPrivileged(new PrivilegedExceptionAction() {
                            public Object run() throws Exception {
                                return ((Object) ((new StringBuilder()).append(((Object) (Util.getWorkingDirectory()))).append(File.separator).append("bin").append(File.separator).toString()));
                            }
                        }
                                                                           );
                        File dir = new File(path);

                        if(!dir.exists())
                            dir.mkdirs();

                        if(latestVersion != null) {
                            File versionFile = new File(dir, "version");
                            boolean cacheAvailable = false;

                            if(!forceUpdate && versionFile.exists() && (latestVersion.equals("-1") || latestVersion.equals(((Object) (readVersionFile(versionFile)))))) {
                                cacheAvailable = true;
                                percentage = 90;
                            }

                            if(forceUpdate || !cacheAvailable) {
                                shouldUpdate = true;

                                if(!forceUpdate && versionFile.exists())
                                    checkShouldUpdate();

                                if(shouldUpdate) {
                                    writeVersionFile(versionFile, "");
                                    downloadJars(path);
                                    extractJars(path);
                                    extractNatives(path);

                                    if(latestVersion != null) {
                                        percentage = 90;
                                        writeVersionFile(versionFile, latestVersion);
                                    }
                                } else {
                                    cacheAvailable = true;
                                    percentage = 90;
                                }
                            }
                        }

                        updateClassPath(dir);
                        state = 10;
                    } catch(AccessControlException ace) {
                        fatalErrorOccured(ace.getMessage(), ((Exception) (ace)));
                        certificateRefused = true;
                        break label0;
                    } catch(Exception e) {
                        fatalErrorOccured(e.getMessage(), e);
                        break label0;
                    }

                    break label1;
                } finally {
                    loaderThread = null;
                }
            }
            loaderThread = null;
        }
    }

    private void checkShouldUpdate() {
        pauseAskUpdate = true;

        while(pauseAskUpdate)
            try {
                Thread.sleep(1000L);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
    }

    protected String readVersionFile(File file) throws Exception {
        DataInputStream dis = new DataInputStream(((InputStream) (new FileInputStream(file))));
        String version = dis.readUTF();
        dis.close();
        return version;
    }

    protected void writeVersionFile(File file, String version) throws Exception {
        DataOutputStream dos = new DataOutputStream(((OutputStream) (new FileOutputStream(file))));
        dos.writeUTF(version);
        dos.close();
    }

    protected void updateClassPath(File dir) throws Exception {
        state = 6;
        percentage = 95;
        URL urls[] = new URL[urlList.length];

        for(int i = 0; i < urlList.length; i++)
            urls[i] = (new File(dir, getJarName(urlList[i]))).toURI().toURL();

        if(classLoader == null)
            classLoader = ((ClassLoader) (new URLClassLoader(urls) {
            protected PermissionCollection getPermissions(CodeSource codesource) {
                PermissionCollection perms = null;

                try {
                    Method method = (java.security.SecureClassLoader.class).getDeclaredMethod("getPermissions", new Class[] {
                                        java.security.CodeSource.class
                                    });
                    method.setAccessible(true);
                    perms = (PermissionCollection)method.invoke(((Object) (((Object)this).getClass().getClassLoader())), new Object[] {
                                codesource
                            });
                    String host = "www.minecraft.net";

                    if(host != null && host.length() > 0)
                        perms.add(((java.security.Permission) (new SocketPermission(host, "connect,accept"))));
                    else
                        codesource.getLocation().getProtocol().equals("file");

                    perms.add(((java.security.Permission) (new FilePermission("<<ALL FILES>>", "read"))));
                } catch(Exception e) {
                    e.printStackTrace();
                }

                return perms;
            }
        }
                                     ));
        String path = dir.getAbsolutePath();

        if(!path.endsWith(File.separator))
            path = (new StringBuilder(String.valueOf(((Object) (path))))).append(File.separator).toString();

        unloadNatives(path);
        System.setProperty("org.lwjgl.librarypath", (new StringBuilder(String.valueOf(((Object) (path))))).append("natives").toString());
        System.setProperty("net.java.games.input.librarypath", (new StringBuilder(String.valueOf(((Object) (path))))).append("natives").toString());
        natives_loaded = true;
    }

    private void unloadNatives(String nativePath) {
        if(!natives_loaded)
            return;

        try {
            Field field = (java.lang.ClassLoader.class).getDeclaredField("loadedLibraryNames");
            field.setAccessible(true);
            Vector libs = (Vector)field.get(((Object) (((Object)this).getClass().getClassLoader())));
            String path = (new File(nativePath)).getCanonicalPath();

            for(int i = 0; i < libs.size(); i++) {
                String s = (String)libs.get(i);

                if(s.startsWith(path)) {
                    libs.remove(i);
                    i--;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Applet createApplet() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class appletClass = classLoader.loadClass("net.minecraft.client.MinecraftApplet");
        return (Applet)appletClass.newInstance();
    }

    protected void downloadJars(String path) throws Exception {
        File versionFile;
        Properties md5s;
        URLConnection urlconnection;
        int fileSizes[];
        boolean skip[];
        int initialPercentage;
        byte buffer[];
        int i;
        versionFile = new File(path, "md5s");
        md5s = new Properties();

        if(versionFile.exists())
            try {
                FileInputStream fis = new FileInputStream(versionFile);
                md5s.load(((InputStream) (fis)));
                fis.close();
            } catch(Exception e) {
                e.printStackTrace();
            }

        state = 4;
        fileSizes = new int[urlList.length];
        skip = new boolean[urlList.length];

        for(int i = 0; i < urlList.length; i++) {
            urlconnection = urlList[i].openConnection();
            urlconnection.setDefaultUseCaches(false);
            skip[i] = false;

            if(urlconnection instanceof HttpURLConnection) {
                ((HttpURLConnection)urlconnection).setRequestMethod("HEAD");
                String etagOnDisk = (new StringBuilder("\"")).append(md5s.getProperty(getFileName(urlList[i]))).append("\"").toString();

                if(!forceUpdate && etagOnDisk != null)
                    urlconnection.setRequestProperty("If-None-Match", etagOnDisk);

                int code = ((HttpURLConnection)urlconnection).getResponseCode();

                if(code / 100 == 3)
                    skip[i] = true;
            }

            fileSizes[i] = urlconnection.getContentLength();
            totalSizeDownload += fileSizes[i];
        }

        initialPercentage = percentage = 10;
        buffer = new byte[0x10000];
        i = 0;
        goto _L1
        _L9:
        int unsuccessfulAttempts;
        int maxUnsuccessfulAttempts;
        boolean downloadFile;

        if(skip[i]) {
            percentage = initialPercentage + (fileSizes[i] * 45) / totalSizeDownload;
            continue; /* Loop/switch isn't completed */
        }

        try {
            md5s.remove(((Object) (getFileName(urlList[i]))));
            md5s.store(((OutputStream) (new FileOutputStream(versionFile))), "md5 hashes for downloaded files");
        } catch(Exception e) {
            e.printStackTrace();
        }

        unsuccessfulAttempts = 0;
        maxUnsuccessfulAttempts = 3;
        downloadFile = true;
        goto _L2
        _L7:
        String etag;
        String currentFile;
        InputStream inputstream;
        FileOutputStream fos;
        long downloadStartTime;
        int downloadedAmount;
        int fileSize;
        String downloadSpeedMessage;
        MessageDigest m;
        downloadFile = false;
        urlconnection = urlList[i].openConnection();
        etag = "";

        if(urlconnection instanceof HttpURLConnection) {
            urlconnection.setRequestProperty("Cache-Control", "no-cache");
            urlconnection.connect();
            etag = urlconnection.getHeaderField("ETag");
            etag = etag.substring(1, etag.length() - 1);
        }

        currentFile = getFileName(urlList[i]);
        inputstream = getJarInputStream(currentFile, urlconnection);
        fos = new FileOutputStream((new StringBuilder(String.valueOf(((Object) (path))))).append(currentFile).toString());
        downloadStartTime = System.currentTimeMillis();
        downloadedAmount = 0;
        fileSize = 0;
        downloadSpeedMessage = "";
        m = MessageDigest.getInstance("MD5");
        goto _L3
        _L5:
        int bufferSize;
        fos.write(buffer, 0, bufferSize);
        m.update(buffer, 0, bufferSize);
        currentSizeDownload += bufferSize;
        fileSize += bufferSize;
        percentage = initialPercentage + (currentSizeDownload * 45) / totalSizeDownload;
        subtaskMessage = (new StringBuilder("Retrieving: ")).append(currentFile).append(" ").append((currentSizeDownload * 100) / totalSizeDownload).append("%").toString();
        downloadedAmount += bufferSize;
        long timeLapse = System.currentTimeMillis() - downloadStartTime;

        if(timeLapse >= 1000L) {
            float downloadSpeed = (float)downloadedAmount / (float)timeLapse;
            downloadSpeed = (float)(int)(downloadSpeed * 100F) / 100F;
            downloadSpeedMessage = (new StringBuilder(" @ ")).append(downloadSpeed).append(" KB/sec").toString();
            downloadedAmount = 0;
            downloadStartTime += 1000L;
        }

        this;
        subtaskMessage;
        JVM INSTR new #184 <Class StringBuilder>;
        JVM INSTR dup_x1 ;
        JVM INSTR swap ;
        String.valueOf();
        StringBuilder();
        downloadSpeedMessage;
        append();
        toString();
        subtaskMessage;
        _L3:

        if((bufferSize = inputstream.read(buffer, 0, buffer.length)) != -1) goto _L5;
        else goto _L4
            _L4:
            inputstream.close();

        fos.close();
        String md5;

        for(md5 = (new BigInteger(1, m.digest())).toString(16); md5.length() < 32; md5 = (new StringBuilder("0")).append(md5).toString());

        boolean md5Matches = true;

        if(etag != null)
            md5Matches = md5.equals(((Object) (etag)));

        if(urlconnection instanceof HttpURLConnection)
            if(md5Matches && (fileSize == fileSizes[i] || fileSizes[i] <= 0))
                try {
                    md5s.setProperty(getFileName(urlList[i]), etag);
                    md5s.store(((OutputStream) (new FileOutputStream(versionFile))), "md5 hashes for downloaded files");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            else if(++unsuccessfulAttempts < maxUnsuccessfulAttempts) {
                downloadFile = true;
                currentSizeDownload -= fileSize;
            } else {
                throw new Exception((new StringBuilder("failed to download ")).append(currentFile).toString());
            }

        _L2:

        if(downloadFile) goto _L7;
        else goto _L6
            _L6:
            i++;

        _L1:

        if(i < urlList.length) goto _L9;
        else goto _L8
            _L8:
            subtaskMessage = "";

        return;
    }

    protected InputStream getJarInputStream(String currentFile, final URLConnection urlconnection) throws Exception {
        final InputStream is[] = new InputStream[1];

        for(int j = 0; j < 3 && is[0] == null; j++) {
            Thread t = ((Thread) (new Thread() {
                public void run() {
                    try {
                        is[0] = urlconnection.getInputStream();
                    } catch(IOException ioexception) { }
                }
            }
                                 ));
            t.setName("JarInputStreamThread");
            t.start();

            for(int iterationCount = 0; is[0] == null && iterationCount++ < 5;)
                try {
                    t.join(1000L);
                } catch(InterruptedException interruptedexception) { }

            if(is[0] == null)
                try {
                    t.interrupt();
                    t.join();
                } catch(InterruptedException interruptedexception1) { }
        }

        if(is[0] == null) {
            if(currentFile.equals("minecraft.jar"))
                throw new Exception((new StringBuilder("Unable to download ")).append(currentFile).toString());
            else
                throw new Exception((new StringBuilder("Unable to download ")).append(currentFile).toString());
        } else {
            return is[0];
        }
    }

    protected void extractLZMA(String in, String out) throws Exception {
        File f = new File(in);

        if(!f.exists())
            return;

        FileInputStream fileInputHandle = new FileInputStream(f);
        Class clazz = Class.forName("LZMA.LzmaInputStream");
        Constructor constructor = clazz.getDeclaredConstructor(new Class[] {
                                      java.io.InputStream.class
                                  });
        InputStream inputHandle = (InputStream)constructor.newInstance(new Object[] {
                                      fileInputHandle
                                  });
        OutputStream outputHandle = ((OutputStream) (new FileOutputStream(out)));
        byte buffer[] = new byte[16384];

        for(int ret = inputHandle.read(buffer); ret >= 1; ret = inputHandle.read(buffer))
            outputHandle.write(buffer, 0, ret);

        inputHandle.close();
        outputHandle.close();
        outputHandle = null;
        inputHandle = null;
        f.delete();
    }

    protected void extractPack(String in, String out) throws Exception {
        File f = new File(in);

        if(!f.exists()) {
            return;
        } else {
            FileOutputStream fostream = new FileOutputStream(out);
            JarOutputStream jostream = new JarOutputStream(((OutputStream) (fostream)));
            java.util.jar.Pack200.Unpacker unpacker = Pack200.newUnpacker();
            unpacker.unpack(f, jostream);
            jostream.close();
            f.delete();
            return;
        }
    }

    protected void extractJars(String path) throws Exception {
        state = 5;
        float increment = 10F / (float)urlList.length;

        for(int i = 0; i < urlList.length; i++) {
            percentage = 55 + (int)(increment * (float)(i + 1));
            String filename = getFileName(urlList[i]);

            if(filename.endsWith(".pack.lzma")) {
                subtaskMessage = (new StringBuilder("Extracting: ")).append(filename).append(" to ").append(filename.replaceAll(".lzma", "")).toString();
                extractLZMA((new StringBuilder(String.valueOf(((Object) (path))))).append(filename).toString(), (new StringBuilder(String.valueOf(((Object) (path))))).append(filename.replaceAll(".lzma", "")).toString());
                subtaskMessage = (new StringBuilder("Extracting: ")).append(filename.replaceAll(".lzma", "")).append(" to ").append(filename.replaceAll(".pack.lzma", "")).toString();
                extractPack((new StringBuilder(String.valueOf(((Object) (path))))).append(filename.replaceAll(".lzma", "")).toString(), (new StringBuilder(String.valueOf(((Object) (path))))).append(filename.replaceAll(".pack.lzma", "")).toString());
            } else if(filename.endsWith(".pack")) {
                subtaskMessage = (new StringBuilder("Extracting: ")).append(filename).append(" to ").append(filename.replace(".pack", "")).toString();
                extractPack((new StringBuilder(String.valueOf(((Object) (path))))).append(filename).toString(), (new StringBuilder(String.valueOf(((Object) (path))))).append(filename.replace(".pack", "")).toString());
            } else if(filename.endsWith(".lzma")) {
                subtaskMessage = (new StringBuilder("Extracting: ")).append(filename).append(" to ").append(filename.replace(".lzma", "")).toString();
                extractLZMA((new StringBuilder(String.valueOf(((Object) (path))))).append(filename).toString(), (new StringBuilder(String.valueOf(((Object) (path))))).append(filename.replace(".lzma", "")).toString());
            }
        }
    }

    protected void extractNatives(String path) throws Exception {
        state = 5;
        int initialPercentage = percentage;
        String nativeJar = getJarName(urlList[urlList.length - 1]);
        Certificate certificate[] = (net.minecraft.Launcher.class).getProtectionDomain().getCodeSource().getCertificates();

        if(certificate == null) {
            URL location = (net.minecraft.Launcher.class).getProtectionDomain().getCodeSource().getLocation();
            JarURLConnection jurl = (JarURLConnection)(new URL((new StringBuilder("jar:")).append(location.toString()).append("!/net/minecraft/Launcher.class").toString())).openConnection();
            jurl.setDefaultUseCaches(true);

            try {
                certificate = jurl.getCertificates();
            } catch(Exception exception) { }
        }

        File nativeFolder = new File((new StringBuilder(String.valueOf(((Object) (path))))).append("natives").toString());

        if(!nativeFolder.exists())
            nativeFolder.mkdir();

        File file = new File((new StringBuilder(String.valueOf(((Object) (path))))).append(nativeJar).toString());

        if(!file.exists())
            return;

        JarFile jarFile = new JarFile(file, true);
        Enumeration entities = jarFile.entries();
        totalSizeExtract = 0;

        while(entities.hasMoreElements())  {
            JarEntry entry = (JarEntry)entities.nextElement();

            if(!entry.isDirectory() && entry.getName().indexOf('/') == -1)
                totalSizeExtract += ((int) (entry.getSize()));
        }

        currentSizeExtract = 0;

        for(entities = jarFile.entries(); entities.hasMoreElements();) {
            JarEntry entry = (JarEntry)entities.nextElement();

            if(!entry.isDirectory() && entry.getName().indexOf('/') == -1) {
                File f = new File((new StringBuilder(String.valueOf(((Object) (path))))).append("natives").append(File.separator).append(entry.getName()).toString());

                if(!f.exists() || f.delete()) {
                    InputStream in = jarFile.getInputStream(jarFile.getEntry(entry.getName()));
                    OutputStream out = ((OutputStream) (new FileOutputStream((new StringBuilder(String.valueOf(((Object) (path))))).append("natives").append(File.separator).append(entry.getName()).toString())));
                    byte buffer[] = new byte[0x10000];
                    int bufferSize;

                    while((bufferSize = in.read(buffer, 0, buffer.length)) != -1)  {
                        out.write(buffer, 0, bufferSize);
                        currentSizeExtract += bufferSize;
                        percentage = initialPercentage + (currentSizeExtract * 20) / totalSizeExtract;
                        subtaskMessage = (new StringBuilder("Extracting: ")).append(entry.getName()).append(" ").append((currentSizeExtract * 100) / totalSizeExtract).append("%").toString();
                    }

                    validateCertificateChain(certificate, entry.getCertificates());
                    in.close();
                    out.close();
                }
            }
        }

        subtaskMessage = "";
        jarFile.close();
        File f = new File((new StringBuilder(String.valueOf(((Object) (path))))).append(nativeJar).toString());
        f.delete();
    }

    protected static void validateCertificateChain(Certificate ownCerts[], Certificate native_certs[]) throws Exception {
        if(ownCerts == null)
            return;

        if(native_certs == null)
            throw new Exception("Unable to validate certificate chain. Native entry did not have a certificate chain at all");

        if(ownCerts.length != native_certs.length)
            throw new Exception((new StringBuilder("Unable to validate certificate chain. Chain differs in length [")).append(ownCerts.length).append(" vs ").append(native_certs.length).append("]").toString());

        for(int i = 0; i < ownCerts.length; i++)
            if(!ownCerts[i].equals(((Object) (native_certs[i]))))
                throw new Exception((new StringBuilder("Certificate mismatch: ")).append(((Object) (ownCerts[i]))).append(" != ").append(((Object) (native_certs[i]))).toString());
    }

    protected String getJarName(URL url) {
        String fileName = url.getFile();

        if(fileName.contains("?"))
            fileName = fileName.substring(0, fileName.indexOf("?"));

        if(fileName.endsWith(".pack.lzma"))
            fileName = fileName.replaceAll(".pack.lzma", "");
        else if(fileName.endsWith(".pack"))
            fileName = fileName.replaceAll(".pack", "");
        else if(fileName.endsWith(".lzma"))
            fileName = fileName.replaceAll(".lzma", "");

        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }

    protected String getFileName(URL url) {
        String fileName = url.getFile();

        if(fileName.contains("?"))
            fileName = fileName.substring(0, fileName.indexOf("?"));

        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }

    protected void fatalErrorOccured(String error, Exception e) {
        e.printStackTrace();
        fatalError = true;
        fatalErrorDescription = (new StringBuilder("Fatal error occured (")).append(state).append("): ").append(error).toString();
        System.out.println(fatalErrorDescription);
        System.out.println(generateStacktrace(e));
    }

    public boolean canPlayOffline() {
        File dir;
        String path = (String)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Object run() throws Exception {
                return ((Object) ((new StringBuilder()).append(((Object) (Util.getWorkingDirectory()))).append(File.separator).append("bin").append(File.separator).toString()));
            }
        }
                                                           );
        dir = new File(path);

        if(!dir.exists())
            return false;

        dir = new File(dir, "version");

        if(!dir.exists())
            return false;

        String version;

        if(!dir.exists())
            break MISSING_BLOCK_LABEL_87;

        version = readVersionFile(dir);

        if(version != null && version.length() > 0)
            return true;

        break MISSING_BLOCK_LABEL_87;
        Exception e;
        e;
        e.printStackTrace();
        return false;
        return false;
    }

}
