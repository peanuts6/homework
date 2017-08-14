/**
 * Created by xqy on 17/8/12.
 */
//complex parts
class CPU {
    CPU(){
        System.out.println("initialize CPU");
    }
    public void freeze() {
        System.out.println("freeze");
    }
    public void jump(long position) {
        System.out.println("jump:"+String.valueOf(position));
    }
    public void execute() {
        System.out.println("execute");
    }
}
class HardDrive {
    HardDrive(){
        System.out.println("initialize HardDrive");
    }
    public byte[] read(long lba, int size) {
        byte[] b = new byte[size];
        System.out.println("HardDrive read sector:"+String.valueOf(lba)+" with size:"+String.valueOf(size));
        return b;
    }
}
class Memory {
    Memory(){
        System.out.println("initialize Memory");
    }
    public void load(long position, byte[] data) {
        System.out.println("load from position:"+String.valueOf(position) + " "+data.length);
    }
}

//facade
class ComputerFacade {
    private long BOOT_ADDRESS = 0;
    private long BOOT_SECTOR = 20;
    private int SECTOR_SIZE = 256;
    private CPU processor;
    private Memory ram;
    private HardDrive hd;

    public ComputerFacade() {
        this.processor = new CPU();
        this.ram = new Memory();
        this.hd = new HardDrive();
    }

    public void start() {
        processor.freeze();
        ram.load(BOOT_ADDRESS, hd.read(BOOT_SECTOR, SECTOR_SIZE));
        processor.jump(BOOT_ADDRESS);
        processor.execute();
    }
}

public class FacadeDemo {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}
