package com.example.demo.server;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint(value = "/djitsWs")
public class WebsocketServer {

    private final Logger logger = LoggerFactory.getLogger(WebsocketServer.class);

    // bean container보다 먼저 실행되므로, filed에서 bean을 주입받을 수 없음.
    // 주입받으려고 하면, ws연결 끊김

    @OnOpen
    public void onOpen(Session session) {
        logger.info("[ws][open]");
//        try {
//            Define.clients.add(session);
//            logger.info("[addClient] " + session + "[size] " + Define.clients.size());
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        logger.info("[ws][onMessage] : " + message);
        logger.info(message);

        Gson gson = new Gson();

//        ResDataDTO result = null;
//        try {
//            HashMap<String, ?> req = gson.fromJson(message, HashMap.class);
//            String serviceName = String.valueOf(req.get("serviceName"));
//            String methodName = String.valueOf(req.get("methodName"));
//            try {
//                if (serviceName.equals("null")) {
//                    throw new NullPointerException("serviceName을 입력해주세요");
//                }
//                if (methodName.equals("null")) {
//                    throw new NullPointerException("methodName를 입력해주세요");
//                }
//
//                Object service = Define.ctx.getBean(serviceName);
//                Method[] methods = service.getClass().getDeclaredMethods();
//
//                Object data = null;
//                Optional<Method> targetMethod = Arrays.stream(methods)
//                        .filter(method -> method.getName().equals(methodName))
//                        .findAny();
//                Method getMethodParam = Arrays.stream(methods)
//                        .filter(method -> method.getName().equals("getDefaultSearchParam"))
//                        .findAny().get();
//                Object param = getMethodParam.invoke(service);
//                if (targetMethod.isPresent()) {
//                    data = targetMethod.get().invoke(service, param);
//                } else {
//                    throw new MethodNotFoundException("methodName을 찾을 수 없습니다.");
//                }
//
//                result = ResDataDTO.builder()
//                        .status(200)
//                        .methodName(methodName)
//                        .message("ok")
//                        .items(data)
//                        .build();
//
//            /**
//             * reflection exception
//             * - [x] param의 서비스, method가 null인경우
//             * - [x] method를 찾을 수 없는경우
//             * - [] 실행중오류가 난 경우
//             */
//            } catch (InvocationTargetException e) {
//                result = ResDataDTO.builder()
//                        .status(500)
//                        .message("메소드 실행중 오류 발생")
//                        .methodName(methodName)
//                        .items(null)
//                        .build();
//                logger.error(e.getMessage(), e);
//            } catch (Exception e) {
//                //SearchDTO가 안맞는 오류 잡아야함
//                //service에서 나는 오류 잡아야함
//                result = ResDataDTO.builder()
//                        .status(500)
//                        .message(e.getMessage())
//                        .methodName(methodName)
//                        .items(null)
//                        .build();
//                logger.error(e.getMessage(), e);
//            }
//        } catch (JsonSyntaxException e) {
//            result = ResDataDTO.builder()
//                    .status(500)
//                    .message("json parsing에 실패하였습니다.")
//                    .methodName(null)
//                    .items(null)
//                    .build();
//            logger.error(e.getMessage(), e);
//        }
//        Define.resQueue.addResEntity(ResponseEntity.ok(result));
        return message;
    }

    @OnClose
    public void onClose(Session session) {
        logger.info("[ws][onclose]");
//
//        try {
//            Define.clients.add(session);
//            logger.info("[addClient] " + session + "[size] " + Define.clients.size());
//        } catch(Exception e) {
//            logger.error(e.getMessage(), e);
//        }
    }

    @OnError
    public void onError(Throwable e) {
        logger.info("[ws][onError]");
        logger.error(e.getMessage(), e);
    }


}
