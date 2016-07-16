package com.urvesh.bello;


public class LectureDetails extends Item {
    String facultyName, subject, time;
    int roomNo,lecColor;


    public LectureDetails(String facultyName, String subject, String time, int roomNo,int lecColor) {
        this.facultyName = facultyName;
        this.subject = subject;
        this.time = time;
        this.roomNo = roomNo;
        this.lecColor = lecColor;

    }

    public int getLecColor() {return lecColor;}

    public String getTime() {
        return time;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public String getSubject() {return subject;}

    public void setLecColor(int color) {this.lecColor = color;}

    public void setTime(String time) {
        this.time = time;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
