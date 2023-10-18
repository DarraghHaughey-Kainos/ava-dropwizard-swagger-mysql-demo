package org.kainos.ea.cli;

public class Contractor implements IPayable{

    private String name;
    private double dailyRate;
    private int monthlyDayWorked;

    public Contractor(String name, double dailyRate, int monthlyDayWorked) {
        setName(name);
        setDailyRate(dailyRate);
        setMonthlyDayWorked(monthlyDayWorked);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public int getMonthlyDayWorked() {
        return monthlyDayWorked;
    }

    public void setMonthlyDayWorked(int monthlyDayWorked) {
        this.monthlyDayWorked = monthlyDayWorked;
    }

    @Override
    public double calcPay() {
        return getDailyRate()*getMonthlyDayWorked();
    }
}
