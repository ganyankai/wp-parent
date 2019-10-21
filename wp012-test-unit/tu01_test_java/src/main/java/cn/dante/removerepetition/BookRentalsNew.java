package cn.dante.removerepetition;

import java.util.Vector;

public class BookRentalsNew {
    private Vector rentals = new Vector();

    public String getCustomerName(String rentalId) {
//        int i = getRentalIdxById(rentalId);
//                 ((BookRental)rentals.elementAt(getRentalIdxById(rentalId))).getCustomerName();
        return ((BookRental)rentals.elementAt(getRentalIdxById(rentalId))).getCustomerName();
    }

    public void deleteRental(String rentalId) {

        rentals.remove(getRentalIdxById(rentalId));
    }

    //通过id获得Rental在rentals中索引
    public int getRentalIdxById(String rentalId){
        for (int i = 0; i < rentals.size(); i++) {
            BookRental rental = (BookRental) rentals.elementAt(i);
            if (rental.getId().equals(rentalId)) {
                return i;
            }
        }
        throw new RentalNotFoundException();
    }


    public static void main(String[] args) {
        BookRentalsNew bookRentals = new BookRentalsNew();
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