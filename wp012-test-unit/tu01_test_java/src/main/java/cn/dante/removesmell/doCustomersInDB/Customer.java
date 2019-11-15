package cn.dante.removesmell.doCustomersInDB;

import jdk.nashorn.internal.objects.NativeString;
import lombok.Data;

@Data
public class Customer {
    private  String IDNumber;
    private  String name;
    public String getIDNumber() {
        return IDNumber;
    }

    public static void main(String[] args) {
        System.out.println(1/0);
    }
}
