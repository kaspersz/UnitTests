package testing;

public class Account {
    private boolean active = false;
    private Address defaultDeliveryAddress;

    public Account() {
    }
    public Account(Address address) {
        defaultDeliveryAddress = address;
    }
    public Account(boolean active) {
        this.active = active;
    }


    public void activate() {
        this.active = true;
    }

    public boolean isActive() {
        return this.active;
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }
}
