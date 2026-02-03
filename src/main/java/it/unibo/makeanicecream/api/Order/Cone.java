package it.unibo.makeanicecream.api.Order;

public class Cone {
    private final String type; // Tipi di coni: "Normale", "Coppa", "Waffle"

    public Cone (String type){
        this.type=type;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj){
        if(this.type==obj){
            return true;
        }
        if(obj==null || !(obj instanceof Cone)){
            return false;
        }
        Cone other = (Cone) obj;
        return type.equals(other.type);  
    }

    @Override
    public int hashCode(){
        return type.hashCode();
    }

    @Override
    public String toString(){
        return "Cono" + type;
    }
}
