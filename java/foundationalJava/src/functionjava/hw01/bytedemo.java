package functionjava.hw01;

public class bytedemo {
	public static void main(String[] args){
		byte a=(byte) 0xf0;
		System.out.println(a); 
		int b=0xff&a; 
		System.out.println(b);
		
		int flg = 0B01101000;
		byte c = (byte) ((flg & 0B00110000) >> 4);
		System.out.println(c);
		
		hexbyte();
	}
	
	public static void hexbyte(){
		char hex[] = {
				'0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		byte b = (byte) 0xf1;
		System.out.println("b = 0x" + hex[(b >> 4) & 0x0f] + hex[b & 0x0f]);
	}
}
