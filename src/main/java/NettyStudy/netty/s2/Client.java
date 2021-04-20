package NettyStudy.netty.s2;

import NettyStudy.netty.s3.TankMsg;
import NettyStudy.netty.s3.TankMsgEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

public class Client {

    private Channel channel = null;

    public void connect() {
        // 事件处理线程池
        EventLoopGroup group = new NioEventLoopGroup();

        // 辅助启动类
        Bootstrap b = new Bootstrap();

        try {
            ChannelFuture f = b.group(group) // 把线程池放进来
                    .channel(NioSocketChannel.class)  // 指定io类型
                    .handler(new ClientChannelInitializer())
                    .connect("127.0.0.1", 8888);

            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (!channelFuture.isSuccess()) {
                        System.out.println("not connected~");
                    } else {
                        System.out.println("connected!");
                        channel = channelFuture.channel();
                    }
                }
            });

            f.sync();
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void send(String msg){
        ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes());
        channel.writeAndFlush(buf);
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.connect();
    }


    public void closeConnect() {
        this.send("_bye_");
        //channel.close();
    }
}


class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
                .addLast(new ClientHandler());
    }
}


class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = null;
        try {
            buf = (ByteBuf) msg;
            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);
            String msgAccepted = new String(bytes);
            ClientFrame.INSTANCE.updateText(msgAccepted);
        } finally {
            if (buf != null) {
                ReferenceCountUtil.release(buf);
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // channel 第一次连上可用，写出一个字符串
        // ByteBuf是Direct Memory，直接访问内存，效率很高，但会跳过JVM垃圾回收机制，需要释放内存
        ByteBuf buf = Unpooled.copiedBuffer("nmb".getBytes());
        ctx.writeAndFlush(buf);
    }
}