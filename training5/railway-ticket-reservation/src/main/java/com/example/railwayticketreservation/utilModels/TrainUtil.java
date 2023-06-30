package com.example.railwayticketreservation.utilModels;

import java.util.List;

public class TrainUtil {

    private String trainName;
    private Integer no1stAc;
    private Integer no2ndAc;
    private Integer no3rdAc;
    private Integer noSl;
    private List<String> stations;
    private List<Integer> startDays;
    private List<Integer> halts;

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Integer getNo1stAc() {
        return no1stAc;
    }

    public void setNo1stAc(Integer no1stAc) {
        this.no1stAc = no1stAc;
    }

    public Integer getNo2ndAc() {
        return no2ndAc;
    }

    public void setNo2ndAc(Integer no2ndAc) {
        this.no2ndAc = no2ndAc;
    }

    public Integer getNo3rdAc() {
        return no3rdAc;
    }

    public void setNo3rdAc(Integer no3rdAc) {
        this.no3rdAc = no3rdAc;
    }

    public Integer getNoSl() {
        return noSl;
    }

    public void setNoSl(Integer noSl) {
        this.noSl = noSl;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public List<Integer> getStartDays() {
        return startDays;
    }

    public void setStartDays(List<Integer> startDays) {
        this.startDays = startDays;
    }

    public List<Integer> getHalts() {
        return halts;
    }

    public void setHalts(List<Integer> halts) {
        this.halts = halts;
    }
}
