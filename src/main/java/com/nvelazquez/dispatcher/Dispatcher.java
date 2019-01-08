package com.nvelazquez.dispatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nvelazquez.model.Call;
import com.nvelazquez.model.Observer;
import com.nvelazquez.model.ObserverWithPriority;
import com.nvelazquez.model.Subject;
import com.nvelazquez.util.PriorityComparator;

/**
 * Dispatcher encargado de despachar llamadas usando una cola para almacenar las llamadas no atendidas cuando 
 * no encuentra empleados disponibles.
 * <br>
 * Una lista representa los empleados disponibles, cuando un empleado está ocupado es removido de la lista y vuelto
 * a poner cuando termina su llamada.
 * <br>
 * La lista de empleados es ordenada por prioridad siendo Operador: 1, Supervisor: 2 y Director: 3
 * <br>
 * El ExecutorService se encarga de procesar las llamadas en paralelo.
 * 
 * @author NicolasVelazquez
 *
 */
public class Dispatcher implements Subject {

	private List<ObserverWithPriority> observers = Collections.synchronizedList(new ArrayList<>());
	private ExecutorService executorService = Executors.newFixedThreadPool(10);
	private Queue<Call> queue = new LinkedList<>();

	public Dispatcher() {
		retryDaemon();
	}

	public Dispatcher(List<ObserverWithPriority> observers, List<Call> calls) {
		this();
		this.observers = observers;
		Collections.sort(this.observers, new PriorityComparator());
	}

	public void dispatchCall(Call call) {

		Observer observer = assignObserver();
		
		if(observer == null){
			addCallToQueue(call);
		}else{
			executorService.submit(getDispatchTask(observer, call));
		}
	}
	
	/**
	 * Cada llamada se asigna a un empleado y se procesa en paralelo.
	 * @param observer
	 * @param call
	 * @return Runnable a ejecutar en el ExecutorService
	 */
	private Runnable getDispatchTask(Observer observer, Call call){
		Runnable dispatchTask = new Runnable() {
			public void run() {
				observer.update(Dispatcher.this, call);
			};
		};
		return dispatchTask;
	}

	/**
	 * Método encargado de asignar un empleado para cada llamada. 
	 * Es synchronized ya que es necesario que 1 empleado atienda sólo 1 llamada.
	 * @return Observer asignado
	 */
	public synchronized Observer assignObserver() {
		if(observers.isEmpty()){
			return null;
		}
		Observer o = this.observers.get(0);
		removeObserver(o);
		return o;
	}

	public void shutdownExecutor() {

		System.out.println("Shutdown...");
		executorService.shutdown();

	}

	public void addObserver(Observer o) {
		if (!this.observers.contains(o)) {
			this.observers.add((ObserverWithPriority) o);
			Collections.sort(observers, new PriorityComparator());
		}
	}

	public void removeObserver(Observer o) {
		this.observers.remove(o);
	}

	public List<ObserverWithPriority> getObservers() {
		return observers;
	}

	public void setObservers(List<ObserverWithPriority> observers) {
		this.observers = observers;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}
	
	private void addCallToQueue(Call call){
		queue.add(call);
	}
	
	/**
	 * Thread encargado de procesar llamadas en cola.
	 */
	private void retryDaemon(){
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(;;) {
					try {
						Thread.sleep(2000l);
						int queueSize = queue.size();
						for(int i = 0; i < queueSize; i++) {
							Call call = queue.poll();
							dispatchCall(call);
						}
						Thread.sleep(3000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

}