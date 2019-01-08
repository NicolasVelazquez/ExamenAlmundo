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

    	OldDispatcher dispatcher = new OldDispatcher();
    	
    	dispatcher.addObserver(new Operator());
    	dispatcher.addObserver(new Operator());

    	dispatcher.dispatchCall(new Call(1l));
    	dispatcher.dispatchCall(new Call(2l));
    	dispatcher.dispatchCall(new Call(1l));
    	dispatcher.dispatchCall(new Call(1l));
    	dispatcher.dispatchCall(new Call(1l));
    	dispatcher.dispatchCall(new Call(2l));
    	dispatcher.dispatchCall(new Call(1l));
    	dispatcher.dispatchCall(new Call(1l));
    	dispatcher.dispatchCall(new Call(1l));
    	dispatcher.dispatchCall(new Call(2l));
    	dispatcher.dispatchCall(new Call(1l));
    	dispatcher.dispatchCall(new Call(1l));
    	
    	dispatcher.shutdownExecutor();
    	
    }
}
