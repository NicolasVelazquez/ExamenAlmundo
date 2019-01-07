package com.nvelazquez.ExamenAlmundo;

import com.nvelazquez.dispatcher.Dispatcher;
import com.nvelazquez.model.Call;
import com.nvelazquez.model.Operator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DispatcherTest extends TestCase {

	public DispatcherTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( DispatcherTest.class );
    }
    
    @Override
    protected void setUp() throws Exception {
    	
    	super.setUp();
    }

    public void testApp(){
    	
    	Dispatcher dispatcher = new Dispatcher();
    	
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
    	
//    	dispatcher.shutdownExecutor();
    	
    	while(!dispatcher.getExecutorService().isTerminated()){
	    	try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
        assertTrue( true );
    }
}
