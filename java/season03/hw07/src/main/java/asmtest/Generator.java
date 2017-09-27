package asmtest;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassReader;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassWriter;

import java.io.*;

/**
 * Created by xqy on 17/9/27.
 */
public class Generator {
    public static void main(String[] args) throws IOException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("asmtest/Account.class");;
        ClassReader cr = new ClassReader(in);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        AddSecurityCheckClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
        byte[] data = cw.toByteArray();

        File outputDir=new File("./hw07/target/classes/asmtest");
        File file = new File(outputDir,"Account2.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();


//        Account account = new Account();
//        account.operation();
    }
}
