package vehicleRegCheck.model;

public class Vehicle {
private String Vehicle_Registration;
private String Vehicle_make;
private String Date_of_first_registration;
private String Year_of_manufacture;
private String Cylinder_capacity;
private String CO2Emissions;
private String Fuel_type;
private String Export_marker;
private String Vehicle_status;
private String Vehicle_colour;
private String Vehicle_type_approval;
private String  Wheelplan;
private String  Revenue_weight;

    public Vehicle(){}
    public Vehicle(String Vehicle_Registration)
    {
        super();
        this.Vehicle_Registration=Vehicle_Registration;
    }

    public String getVehicle_Registration() {
        return Vehicle_Registration;
    }

    public void setVehicle_Registration(String vehicle_Registration) {
        Vehicle_Registration = vehicle_Registration;
    }


    public String getVehicle_make() {
        return Vehicle_make;
    }

    public void setVehicle_make(String vehicle_make) {
        Vehicle_make = vehicle_make;
    }


    public String getDate_of_first_registration() {
        return Date_of_first_registration;
    }

    public void setDate_of_first_registration(String date_of_first_registration) {
        Date_of_first_registration = date_of_first_registration;
    }


    public String getYear_of_manufacture() {
        return Year_of_manufacture;
    }

    public void setYear_of_manufacture(String year_of_manufacture) {
        Year_of_manufacture = year_of_manufacture;
    }


    public String getCylinder_capacity() {
        return Cylinder_capacity;
    }

    public void setCylinder_capacity(String cylinder_capacity) {
        Cylinder_capacity = cylinder_capacity;
    }

    public String getCO2Emissions() {
        return CO2Emissions;
    }

    public void setCO2Emissions(String CO2Emissions) {
        this.CO2Emissions = CO2Emissions;
    }


    public String getFuel_type() {
        return Fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        Fuel_type = fuel_type;
    }

    public String getExport_marker() {
        return Export_marker;
    }

    public void setExport_marker(String export_marker) {
        Export_marker = export_marker;
    }

    public String getVehicle_status() {
        return Vehicle_status;
    }

    public void setVehicle_status(String vehicle_status) {
        Vehicle_status = vehicle_status;
    }

    public String getVehicle_colour() {
        return Vehicle_colour;
    }

    public void setVehicle_colour(String vehicle_colour) {
        Vehicle_colour = vehicle_colour;
    }

    public String getVehicle_type_approval() {
        return Vehicle_type_approval;
    }

    public void setVehicle_type_approval(String vehicle_type_approval) {
        Vehicle_type_approval = vehicle_type_approval;
    }

    public String getWheelplan() {
        return Wheelplan;
    }

    public void setWheelplan(String wheelplan) {
        Wheelplan = wheelplan;
    }

    public String getRevenue_weight() {
        return Revenue_weight;
    }

    public void setRevenue_weight(String revenue_weight) {
        Revenue_weight = revenue_weight;
    }
}
