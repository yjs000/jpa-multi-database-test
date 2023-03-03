package com.example.demo.common;

import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;

import javax.websocket.Session;
import java.util.HashSet;
import java.util.Set;

public class Define {
//    public static ResQueue resQueue;
    public static Set<Session> clients = new HashSet<>();

    public static Gson gson = new Gson();
    public static ApplicationContext ctx;
}
