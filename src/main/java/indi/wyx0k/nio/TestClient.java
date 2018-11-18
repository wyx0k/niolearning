package indi.wyx0k.nio;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class TestClient {
    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                    }
                });
        connect(bootstrap,"localhost",8080,1);
    }
    private static void connect (final Bootstrap b, final String host, final int port, final int tryTimes){

        b.connect(host,port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("连接服务器成功:-->"+host+":"+port);
                }else{
                    if(tryTimes <=5){
                        System.out.println("连接失败,第"+tryTimes+"次尝试重连中...");
                        connect(b,host,port,tryTimes+1);
                    }else{
                        System.out.println("连接失败");
                    }
                }
            }
        });
    }
}
