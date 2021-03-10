package com.xzh.rpc.handler;

import com.xzh.rpc.method.RpcRequest;
import com.xzh.service.UserService;
import com.xzh.service.impl.UserServiceImpl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 向振华
 * @date 2021/03/10 14:22
 */
public class RpcHandler {

    public void run() throws Exception {
        ServerSocket serverSocket = new ServerSocket(8081);
        while (true) {
            Socket socket = serverSocket.accept();
            // 将请求体反序列化
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = objectInputStream.readObject();
            // 服务调用
            Object result = null;
            if (object instanceof RpcRequest) {
                RpcRequest rpcRequest = (RpcRequest) object;
                if ("getUserNameById".equals(rpcRequest.getMethodName())) {
                    UserService userService = new UserServiceImpl();
                    result = userService.getUserNameById(rpcRequest.getParameter());
                } else {
                    throw new RuntimeException("method not found");
                }
            }
            // 结果返回
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
        }
        // TODO socket.close(); serverSocket.close();
    }
}
