package com.mycompany.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Port {
    int numberOfDocks;
    int containerStorageCapacity = 1000;
    List<Ship> shipList;
    public static final int maxShips = 30;
    public static final int minShips = 0;
    private int shipCount;
    String name;

    public Port() {
        shipList = new ArrayList<>();
    }

    //    add ship
    public synchronized boolean add(Ship ship) {
        try {
            if (shipCount < maxShips) {
                notifyAll();
                shipList.add(ship);
                String announcement = "Number of ships in port " + name + " right now: " + shipCount + ", New ship has arrived: " + ship.getId();
                System.out.println(announcement);
                shipCount++;
            } else {
                System.out.println("Port is full");
                wait();
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    //get : get ship from port to dock
    public synchronized Ship get() {
        try {
            if (shipCount > minShips) {
                Random random = new Random();
                Ship randomShip = shipList.get(random.nextInt(shipList.size()));
                shipCount--;
                System.out.println("Port has " + shipCount + " ships");
                shipList.remove(randomShip);
                System.out.println("Ship " + randomShip.getId() + " has left port");
                System.out.println("Ship now is in: " + Thread.currentThread().getName());
                return randomShip;
            }
            System.out.println("Port is empty");
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
        //drop ship if port is full
    }
}