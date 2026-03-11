package com.example.demo.application.util;

public class Constants {

    public static String urlFixa = "http://localhost:8080/";
    public static String identifierUrl = "url1";
    public static String x_api_key = (System.getenv("X_API_KEY") == null || System.getenv("X_API_KEY").isBlank())
                    ? "80037aa6-21a5-4087-9124-fbbcaa18a866"
                    : System.getenv("X_API_KEY");}
