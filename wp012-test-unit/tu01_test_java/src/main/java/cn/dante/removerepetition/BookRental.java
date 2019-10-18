package cn.dante.removerepetition;

public class BookRental { //该类描述出租记录
    String id;
    String customerName;

    public BookRental() {
    }

    public BookRental(String id, String customerName) {
        this.id = id;
        this.customerName = customerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
