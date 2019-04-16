package com.company;
class Process {
    String processName;
    int serviceTime;
    int waitingTime;
    int turnAroundTime;
    int remainingServiceTime;
    Process(String pn,int st){
        this.processName = pn;
        this.serviceTime = st;
        this.remainingServiceTime = st;
    }
}
