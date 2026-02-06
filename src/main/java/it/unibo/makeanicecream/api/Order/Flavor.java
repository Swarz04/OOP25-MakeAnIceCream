package it.unibo.makeanicecream.api.Order;

public class Flavor {
    private final String name;
    

    public Flavor(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object obj){
        if(this.name==obj){
            return true;
        }
        if(obj==null || !(obj instanceof Topping)){
            return false;
        }
        Flavor other = (Flavor) obj;
        return name.equals(other.name);
    }
    
    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public String toString(){
       return name;
    }
}
