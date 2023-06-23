package com.example.railwayticketreservation.utilModels;

public class TrainAvailable {

    private String trainName;
    private Integer slCount;
    private Integer a3Count;
    private Integer a2Count;
    private Integer a1Count;

    public TrainAvailable() {
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Integer getSlCount() {
        return slCount;
    }

    public void setSlCount(Integer slCount) {
        this.slCount = slCount;
    }

    public Integer getA3Count() {
        return a3Count;
    }

    public void setA3Count(Integer a3Count) {
        this.a3Count = a3Count;
    }

    public Integer getA2Count() {
        return a2Count;
    }

    public void setA2Count(Integer a2Count) {
        this.a2Count = a2Count;
    }

    public Integer getA1Count() {
        return a1Count;
    }

    public void setA1Count(Integer a1Count) {
        this.a1Count = a1Count;
    }
}
