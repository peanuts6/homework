package asmtest;

import com.sun.xml.internal.ws.org.objectweb.asm.*;

/**
 * Created by xqy on 17/9/27.
 */
public class AddSecurityCheckClassAdapter extends ClassAdapter {
    public AddSecurityCheckClassAdapter(ClassVisitor cv) {
        super(cv);
    }

    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        String enhancedSuperName = name;
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature,exceptions);
        MethodVisitor wrappedMv = mv;
        if (mv != null) {
            if (name.equals("operation")) {
                wrappedMv = new AddSecurityCheckMethodAdapter(mv);
            }
        }
        return wrappedMv;
    }
}
