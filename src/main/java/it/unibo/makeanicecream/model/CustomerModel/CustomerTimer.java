package it.unibo.makeanicecream.model.CustomerModel;

import it.unibo.makeanicecream.api.Timer;

public class CustomerTimer implements Timer{

    private double secondsLeft;
    private boolean expired = false;
    private boolean paused = true;
    private Runnable onExpiredCallback;

    public CustomerTimer(double seconds){
       if(seconds <= 0)
        {
            throw new IllegalArgumentException("Il tempo deve essere positivo");
        }
        this.secondsLeft = seconds;
    }
    @Override
    public void start() {
        this.paused = false;
    }

    @Override
    public void pause() {
        this.paused=true;
    }

    @Override
    public void resume() {
        this.paused=false;
    }

    @Override
    public void update(double deltaTime) {
        if(expired || paused) return;

        secondsLeft -= deltaTime;

        if(secondsLeft <= 0){  
            expired = true;
            secondsLeft = 0;
            if(onExpiredCallback != null){
                onExpiredCallback.run();
            }
        }
    }
    
    @Override
    public boolean isExpired() {
       return expired;
    }

    @Override
    public double getTimeLeft() {
       return Math.max(0, secondsLeft);// Non negativo
    }

    @Override
    public boolean isPaused() {
        return paused;
    }

    @Override
    public void setOnExpired(Runnable callback) { //Comportamento una volta il timer sia scaduto 
        this.onExpiredCallback= callback;
    }
    

   
    
}