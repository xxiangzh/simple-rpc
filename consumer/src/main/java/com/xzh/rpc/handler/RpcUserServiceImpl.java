package com.xzh.rpc.handler;

import com.xzh.rpc.method.RpcRequest;
import com.xzh.service.UserService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author 向振华
 * @date 2021/03/10 14:46
 */
public class RpcUserServiceImpl implements UserService {

    public String getUserNameById(Long userId) {
        try {
            Socket socket = new Socket("127.0.0.1", 8081);
            // 将请求体序列化并发给服务提供方
            RpcRequest rpcRequest = new RpcRequest("getUserNameById", userId);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            // 将响应体反序列化
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object response = objectInputStream.readObject();
            // 返回结果
            return (String) response;
        } catch (Exception e) {
            return null;
        }
        // TODO socket.close();
    }
}
