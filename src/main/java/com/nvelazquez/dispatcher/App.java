package com.nvelazquez.dispatcher;

import com.nvelazquez.model.Call;
import com.nvelazquez.model.Operator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

    	Dispatcher dispatcher = new Dispatcher();
    	
    	dispatcher.addObserver(new Operator());
    	dispatcher.addObserver(new Operator());

    	dispatcher.dispatchCall(new Call(5l));
    	dispatcher.dispatchCall(new Call(10l));
    	dispatcher.dispatchCall(new Call(5l));
    	dispatcher.dispatchCall(new Call(5l));
    	dispatcher.dispatchCall(new Call(5l));
    	dispatcher.dispatchCall(new Call(10l));
    	dispatcher.dispatchCall(new Call(5l));
    	dispatcher.dispatchCall(new Call(5l));
    	dispatcher.dispatchCall(new Call(5l));
    	dispatcher.dispatchCall(new Call(10l));
    	dispatcher.dispatchCall(new Call(5l));
    	dispatcher.dispatchCall(new Call(5l));
    	
//    	dispatcher.shutdownExecutor();
    	
    }
}
