package com.wszib.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarehouseApplication {

    private Warehouse warehouse;

    @Autowired
    public WarehouseApplication(Warehouse theWarehouse) {
        warehouse = theWarehouse;
        //warehouse.runApplication();
    }

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }


}
