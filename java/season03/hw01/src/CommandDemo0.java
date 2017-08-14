

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by xqy on 17/8/7.
 */

interface Order {
    public abstract void execute ( );
}

//Receiver.
class StockTrade {
    public void buy() {
        System.out.println("You want to buy stocks");
    }
    public void sell() {
        System.out.println("You want to sell stocks ");
    }
}

//Invoker.
class Agent {
    private Queue ordersQueue = new ConcurrentLinkedQueue<>();

    public Agent() {
    }

    void placeOrder(Order order) {
        //ordersQueue.add(order);
        //ordersQueue.poll();
        order.execute();
        //ordersQueue.addLast(order);
        //order.execute(ordersQueue.getFirstAndRemove());
    }
}

//ConcreteCommand.
class BuyStockOrder implements Order {
    private StockTrade stock;
    public BuyStockOrder ( StockTrade st) {
        stock = st;
    }
    public void execute( ) {
        stock.buy( );
    }
}

//ConcreteCommand.
class SellStockOrder implements Order {
    private StockTrade stock;
    public SellStockOrder ( StockTrade st) {
        stock = st;
    }
    public void execute( ) {
        stock.sell( );
    }
}

// client
public class CommandDemo0 {
    public static void main(String[] args) {
        StockTrade stock = new StockTrade();
        BuyStockOrder bsc = new BuyStockOrder (stock);
        SellStockOrder ssc = new SellStockOrder (stock);
        Agent agent = new Agent();

        agent.placeOrder(bsc); // Buy Shares
        agent.placeOrder(ssc); // Sell Shares
    }
}



