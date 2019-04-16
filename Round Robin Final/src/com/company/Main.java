package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("How many number of processes :");
        Scanner scanner = new Scanner(System.in);
        int numberOfProcesses = scanner.nextInt();
        Process[] process = new Process[numberOfProcesses];
        setData(scanner, numberOfProcesses, process);
        System.out.print(" \n Enter the Time Quantum:");
        int timeQuantum = scanner.nextInt();
        calculateWaitingAndTurnAroundTime(numberOfProcesses, process, timeQuantum);
        display(numberOfProcesses, process);
    }

    private static void display(int numberOfProcesses, Process[] process) {
        String  max;
        Process temp;
        for(int i=0;i<process.length;i++){
            for(int j=i+1;j<process.length;j++){
                if(process[i].processName.length() > process[j].processName.length()){
                    temp = process[i];
                    process[i] = process[j];
                    process[j] = temp;
                }
            }
        }
        max = process[process.length-1].processName;
        for (Process value : process) {
            if (value.processName.equals(max)) {
                System.out.println();
            }
            else {
                for (int j = 0; j < max.length() - value.processName.length(); j++) {
                    value.processName += " ";
                }
            }
        }
        System.out.println("pname" + "\tst\twt\ttat");
        for(int i=0;i<numberOfProcesses;i++){
            //System.out.print(process[i].processName);
            System.out.println(process[i].processName + "\t\t" + process[i].serviceTime + "\t" + process[i].waitingTime + "\t" + process[i].turnAroundTime);
        }
    }

    private static void calculateWaitingAndTurnAroundTime(int numberOfProcesses, Process[] process, int timeQuantum) {
        int count=0,time=0;
        while(count < numberOfProcesses){
            for(int i=0;i<numberOfProcesses;i++){
                if(process[i].remainingServiceTime > timeQuantum){
                    process[i].remainingServiceTime -= timeQuantum;
                    time += timeQuantum;
                }
                else if((process[i].remainingServiceTime <= timeQuantum)&&(process[i].remainingServiceTime > 0)){
                    time += process[i].remainingServiceTime;
                    process[i].remainingServiceTime = 0;
                    process[i].waitingTime = time - process[i].serviceTime;
                    process[i].turnAroundTime = process[i].serviceTime + process[i].waitingTime;
                    count++;
                }
            }
        }
    }

    private static void setData(Scanner scanner, int numberOfProcesses, Process[] process) {
        System.out.println("Start entering the values of process name and service Time");
        for(int i=0;i<numberOfProcesses;i++){
            process[i] = new Process(scanner.next(),scanner.nextInt());
            process[i].waitingTime = 0;
        }
    }
}
