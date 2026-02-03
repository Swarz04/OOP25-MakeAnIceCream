package it.unibo.makeanicecream.api.Order;

public class Topping {
    private final String name;
    private final boolean isLiquid;

    public Topping(String name, boolean isLiquid){
        this.name=name;
        this.isLiquid=isLiquid;
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
        Topping other = (Topping) obj;
        return name.equals(other.name) && isLiquid == other.isLiquid;  
    }

    @Override
    public int hashCode(){
        return 31*name.hashCode() + Boolean.hashCode(isLiquid);
    }

    @Override
    public String toString(){
        if(isLiquid==true){
            return name + "liquido";
        }else{
            return name + "solido";
        }
    }
}
