package io.perfeccionista.framework.measurements;

public class LocationGPS {

    private final double latitude;
    private final double longitude;
    private final double altitude;

    private LocationGPS(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public static LocationGPS of(double latitude, double longitude, double altitude) {
        return new LocationGPS(latitude, longitude, altitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    @Override
    public String toString() {
        return String.format("Latitude: %s, Longitude: %s, Altitude: %s",
                latitude, longitude, altitude);
    }

}
