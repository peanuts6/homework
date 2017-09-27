package asmtest;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassVisitor;
import com.sun.xml.internal.ws.org.objectweb.asm.FieldVisitor;
import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;

/**
 * Created by xqy on 17/9/27.
 */
public class AccessClassAdapter extends ClassAdapter {
    public AccessClassAdapter(ClassVisitor cv) {
        super(cv);
    }
    public FieldVisitor visitField(final int access, final String name,
                                   final String desc, final String signature, final Object value) {
        int privateAccess = Opcodes.ACC_PRIVATE;
        return cv.visitField(privateAccess, name, desc, signature, value);
    }
}
