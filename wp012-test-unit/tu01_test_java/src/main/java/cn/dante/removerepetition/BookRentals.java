package cn.dante.removerepetition;

import java.util.Vector;

public class BookRentals {
    private Vector rentals = new Vector();

    public String getCustomerName(String rentalId) {
        for (int i = 0; i < rentals.size(); i++) {
            BookRental rental = (BookRental) rentals.elementAt(i);
            if (rental.getId().equals(rentalId)) {
                return rental.getCustomerName();
            }
        }
        throw new RentalNotFoundException();
    }

    public void deleteRental(String rentalId) {
        for (int i = 0; i < rentals.size(); i++) {
            BookRental rental = (BookRental) rentals.elementAt(i);
            if (rental.getId().equals(rentalId)) {
                rentals.remove(i);
                return;
            }
        }
        throw new RentalNotFoundException();
    }

    public static void main2(String[] args) {
        BookRentals bookRentals = new BookRentals();
        Vector rentals = bookRentals.rentals;
        // TODO: 2019/10/18
//        rentals.add()
        rentals.addElement(new BookRental("1","xm"));
        rentals.addElement(new BookRental("2","xh"));
        System.out.println(rentals);

        String name = bookRentals.getCustomerName("2");
        System.out.println("name:"+name);

        bookRentals.deleteRental("2");
        System.out.println("rentals2:"+rentals);
    }
}