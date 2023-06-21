package com.example.railwayticketreservation.train;

import jakarta.persistence.*;

@Entity
@Table (name = "train")
public class TrainModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int trainNumber;
    private String trainName;
    private String source;
    private String destination;

    public TrainModel() {

    }

    public TrainModel(long id, int trainNumber, String trainName, String source, String destination) {
        this.id = id;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "TrainModel{" +
                "id=" + id +
                ", trainNumber=" + trainNumber +
                ", trainName='" + trainName + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
