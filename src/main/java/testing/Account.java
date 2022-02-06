package testing;

public class Account {
    private boolean active = false;

    public Account() {
    }

    public Account(boolean active) {
        this.active = active;
    }

    public void activate(){
        this.active = true;
    }

    public boolean isActive(){
        return this.active;
    }

}
