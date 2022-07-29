package threads;

public class PrintNumbers {
	public static void main(String[] args) {
		
		Printer printer = new Printer(10);
		
		EvenPrinter even = new EvenPrinter(printer);
		Thread t1 = new Thread(even, "even");
		t1.start();
		OddPrinter odd = new OddPrinter(printer);
		Thread t2 = new Thread(odd, "odd");
		//t1.start();

		t2.start();
		
	}
	
	static class EvenPrinter implements Runnable{
		
		Printer mP;
		EvenPrinter(Printer p){
			mP = p;
		}
		@Override
		public void run() {
			
			mP.printEven();
		}
		
	}
	
	static class OddPrinter implements Runnable{
		
		Printer mP;
		OddPrinter(Printer p){
			mP = p;
		}
		@Override
		public void run() {
			mP.printOdd();
		}
		
	}
	
	static class Printer{
		int i = 0;
		int mN;
		Printer(int N){
			mN = N;
		}
		void printOdd() {
			synchronized (this) {
				while(mN > i) {
					if(i % 2 == 0) {
						try {
							System.out.println("waiting in printodd");
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					System.out.println(i + " - " + Thread.currentThread().getName());
					i++;
					notifyAll();

									

				}

			}
									
		}
		
		void printEven() {
			synchronized (this) {
				while(mN > i) {
					if(i % 2 == 1) {
						try {
							System.out.println("waiting in printeven");
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					System.out.println(i + " - " + Thread.currentThread().getName());
					i++;
					notifyAll();


				}
			}
			
			
		}
		
		boolean isDone() {
			if(i == mN) {
				return true;
			}else {
				return false;
			}
		}
	}
}
