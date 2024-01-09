package com.example.testing.step2;

import java.util.LinkedList;
import java.util.Queue;

public class Application2 {
    public static void main(String[] args) {

        Queue<String> danhSachSV = new LinkedList<String>();
        danhSachSV.offer("hs1");
        danhSachSV.offer("hs2");
        danhSachSV.offer("hs3");
        danhSachSV.offer("hs4");

        while (true){
            String ten = danhSachSV.poll(); // poll => lay ra roi xoa khoi queue
            // peek => lay ra nhung khong xoa
            if(ten == null){
                break;
            }
            System.out.println(ten);
        }
    }
}
