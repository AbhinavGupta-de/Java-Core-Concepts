package org.abhinavgpt.threads;

public class SequencePrinter extends Thread{
    private int number;
    private int max;

    public SequencePrinter(int number, int max){
        this.number = number;
        this.max = max;
    }

    @Override
    public void run(){
        for(int i = number; i <= max; i+=3){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                System.out.println("Error while printing.");
            }
            System.out.println(i + " " + Thread.currentThread().getName());
        }
    }
}
