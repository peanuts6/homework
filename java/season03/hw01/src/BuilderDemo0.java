/**
 * Created by xqy on 17/8/11.
 */

//abstract builder
abstract class TextConvert{
    abstract void convertCharacter(char c);
    abstract void convertParagraph();
}

//product
class ASCIIText{
    public void append(char c){
        System.out.println("append charactor " + String.valueOf(c));
    }
}

//concrete builder
class ASCIIConverter extends TextConvert{
    ASCIIText asciiText;

    void convertCharacter(char c){
        char asciiChar = new Character(c).charValue();
        asciiText.append(asciiChar);
    }
    void convertParagraph(){

    }
    ASCIIText getResult(){
        return asciiText;
    }
    @Override
    public String toString(){
        return asciiText.toString();
    }
}

class Document{
    static int value;
    char token;
    public char getNextToken(){
        return token;
    }
}

//director
class RTFReader{
    private static final char EOF = '0';
    final char CHAR = 'c';
    final char PARA = 'p';
    char t;
    TextConvert builder;
    RTFReader(TextConvert t){
        builder = t;
    }
    void parseRTF(Document doc){
        while((t = doc.getNextToken())!=EOF){
            switch (t){
                case CHAR:
                    builder.convertCharacter(t);
                    break;
                case PARA:
                    builder.convertParagraph();
                    break;
            }
        }
    }
}

public class BuilderDemo0 {
    ASCIIText createASCIIText(Document doc){
        ASCIIConverter asciiBuilder = new ASCIIConverter();
        RTFReader rtfReader = new RTFReader(asciiBuilder);
        rtfReader.parseRTF(doc);
        ASCIIText asciiText = asciiBuilder.getResult();
        return asciiText;
    }

    public static void main(String[] args){
        BuilderDemo0 bd = new BuilderDemo0();
        Document doc = new Document();
        ASCIIText asciiText = bd.createASCIIText(doc);
        //System.out.println(asciiText);
        System.out.println("builder pattern");
    }
}
