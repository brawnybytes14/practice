package parkinglot;

import java.util.*;

enum VehicleSize {
    Small,
    Medium,
    Large
}

public abstract class Vehicle {
    protected String licensePlate; 
    protected VehicleSize size; 
}