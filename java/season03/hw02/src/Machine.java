public class Machine {
    private int[] regs = {0,0,0,0,0,0,0,0};//r0, r1, r2, r3, r4, r5, r6, r7
    private int   flag = 0;
    private int   pc   = 0;

    interface Command {
        void exe();
    }

    class MovC implements Command {
        private final int number;
        private final int reg;

        public MovC(int number, int reg) {
            this.number = number;
            this.reg = reg;
        }

        @Override
        public void exe() {
            regs[reg] = number;
        }
    }

    class Cmp implements Command {
        private final int reg_1;
        private final int reg_2;

        public Cmp(int reg_1, int reg_2) {
            this.reg_1 = reg_1;
            this.reg_2 = reg_2;
        }

        @Override
        public void exe() {
            int x = regs[reg_1]; int y = regs[reg_2];flag = (x < y) ? -1 : ((x == y) ? 0 : 1);
        }
    }
    class Eq implements Command {
        private final int address;

        public Eq(int address) {
            this.address = address;
        }

        @Override
        public void exe() {
            if (flag == 0) pc = address;
        }
    }

    class Lt implements Command {
        private final int address;

        public Lt(int address) {
            this.address = address;
        }

        @Override
        public void exe() {
            if (flag < 0) pc = address;
        }
    }

    class Gt implements Command {
        private final int address;

        public Gt(int address) {
            this.address = address;
        }

        @Override
        public void exe() {
            if (flag > 0) pc = address;
        }
    }

    class Jump implements Command {
        private final int address;

        public Jump(int address) {
            this.address = address;
        }

        @Override
        public void exe() {
            pc = address;
        }
    }

    class Sub implements Command {
        private final int reg_1;
        private final int reg_2;
        private final int reg_3;

        public Sub(int reg_1, int reg_2, int reg_3) {
            this.reg_1 = reg_1;
            this.reg_2 = reg_2;
            this.reg_3 = reg_3;
        }

        @Override
        public void exe() {
            regs[reg_3] = regs[reg_1] - regs[reg_2];
        }
    }

    class Print implements Command {
        private final int reg;

        public Print(int reg) {
            this.reg = reg;
        }

        @Override
        public void exe() {
            System.out.println(regs[reg]);
        }
    }

    class Label implements Command {
        private final String name;

        public Label(String name) {
            this.name = name;
        }

        @Override
        public void exe() {

        }
    }

    public void run(Command[] commands) {
        pc = 0;
        while (pc < commands.length) {
            commands[pc].exe();
            pc++;
        }
    }

    public static void main(String[] args) {
        Machine m = new Machine();
        Command[] commands= {
                    m.new MovC(12, 0),
                    m.new MovC(18, 1),
                m.new Label("gcd"), // address 2
                    m.new Cmp(0, 1),
                    m.new Eq(11), //end,
                    m.new Lt(8), //less
                    m.new Sub(0, 1, 0),
                    m.new Jump(2), //gcd
                m.new Label("less"), //less
                    m.new Sub(1, 0, 1),
                    m.new Jump(2), //gcd
                m.new Label("end"),
                    m.new Print(0)
        };
        m.run(commands);
    }
}

