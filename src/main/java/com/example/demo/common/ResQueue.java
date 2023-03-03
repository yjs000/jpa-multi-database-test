package com.example.demo.common;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;



@Component
public class ResQueue implements Runnable{

    private Logger logger = LoggerFactory.getLogger(ResQueue.class);
    private Queue<ResponseEntity<?>> queue = new LinkedList<>();
    @Autowired
    private Gson gson;
    //jsonObject는 thread safe하지 않다.
    //gson과 jackson은 thread safe하다

//    @Autowired
//    private ModelMapper modelMapper;

    /**
     * body를 가지고 단순히 뿌려주는 역할
     */
    @Override
    public void run() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        while(true) {
//            try{
//                while(!queue.isEmpty()) {
//                    //queue에서 데이터 뽑기
//                    ResponseEntity<?> entity = queue.poll();
//
//                    //ResDataDTO타입이 아닌경우 ResDataDTO로 감싸줌
//                    Object body = entity.getBody();
//
//
//
//                    ResDataDTO result = (ResDataDTO) body;
//
//                    // 데이터 로그 찍기
//                    logger.info(sdf.format(System.currentTimeMillis()) + result.toString());
//
//                    String json = gson.toJson(result);
//
//                    //클라이언트 전송
//                    Set<Session> set = Define.clients.stream().collect(Collectors.toSet());
//                    Iterator<Session> it = set.iterator();
//                    while(it.hasNext()){
//                        Session client = it.next();
//                        try {
//                            if(client.isOpen()) client.getBasicRemote().sendText(json);
//                        } catch (Exception e) {
//                            logger.error(e.getMessage(), e);
//                        }
//                    }
//                }
//
//            } catch (Exception e) {
//                queue.clear(); //여기서 에러가 나면 데이터를 전부 지워버림. 어쩔 수 없이 데이터가 유실됨.
//                throw new RuntimeException(e.getMessage(), e);
//            }
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                logger.error(e.getMessage(), e);
//            }
//        }
    }



    public void addResEntity(ResponseEntity<?> entity) {
        this.queue.add(entity);
    }
}

