package edu.northsouth.smartbin;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Repository
public class BinModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public String binID;
    public double firstDepthLevel;
    public double secondDepthLevel;
    public double dateTime;

    public String getBinID() {
        return binID;
    }

    public void setBinID(String binID) {
        this.binID = binID;
    }

    public double getFirstDepthLevel() {
        return firstDepthLevel;
    }

    public void setFirstDepthLevel(double firstDepthLevel) {
        this.firstDepthLevel = firstDepthLevel;
    }

    public double getSecondDepthLevel() {
        return secondDepthLevel;
    }

    public void setSecondDepthLevel(double secondDepthLevel) {
        this.secondDepthLevel = secondDepthLevel;
    }

    public double getDateTime() {
        return dateTime;
    }

    public void setDateTime(double dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "BinModel{" +
                "binID='" + binID + '\'' +
                ", firstDepthLevel=" + firstDepthLevel +
                ", secondDepthLevel=" + secondDepthLevel +
                ", dateTime=" + dateTime +
                '}';
    }
}
