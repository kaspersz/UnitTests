package testing;

public class Account {
    private boolean active;

    public Account(boolean active) {
        this.active = active;
    }

    public void activate(){
        this.active = active;
    }

    public boolean isActive(){
        return this.active;
    }
    
}
