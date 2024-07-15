package search;

import java.util.*;

public class Server extends Thread{
    private final List<Search> queue = Collections.synchronizedList(new LinkedList<>());
    private final ResultsListener listener;

    public Server(ResultsListener listener){
        this.listener = listener;
        start();
    }

    public void run(){
        while (true){
            if(!queue.isEmpty())
                execute(queue.removeFirst());
            Thread.yield();
        }
    }

    public void add(Search search) throws Exception{
        queue.add(search);
    }

    private void execute(Search search){
        search.execute();
        listener.executed(search);
    }
}
