package com.example.demo.server;

public class EchoStart {
    public static void main(String[] args) {
        try {
            new EchoServer().bind(8007);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
