package NettyStudy.netty.s2;


import NettyStudy.netty.s3.TankMsg;
import NettyStudy.netty.s3.TankMsgDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Server {

    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public void serverStart() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);   //餐厅管家  accept
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);    //餐厅服务员  worker

        try {
            ServerBootstrap b = new ServerBootstrap();
            ChannelFuture f = b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pl = socketChannel.pipeline();
                            pl.addLast(new ServerChildHandler());
//                            System.out.println(socketChannel);
//                            System.out.println("my test~");
                        }
                    })
                    .bind(8888)
                    .sync();

            ServerFrame.INSTANCE.updateServerMsg("server started!");

            f.channel().closeFuture().sync();   //clost()->ChannelFuture  ,调用close时餐厅才会关门

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}


class ServerChildHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = null;
        try{
            buf = (ByteBuf) msg;

            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);
            String s = new String(bytes);

            ServerFrame.INSTANCE.updateClientMsg(s);

            //ctx.writeAndFlush(msg);  // 如果只是读，需要写finally释放；如果还有写操作，用这个就可以不用finally释放了
            if(s.equals("_bye_")){
                ServerFrame.INSTANCE.updateServerMsg("客户端要求退出");
                Server.clients.remove(ctx.channel());
                ctx.close();
            }else {
                Server.clients.writeAndFlush(msg);   //回写操作
            }
            System.out.println(buf);
        }finally {
//            if(buf!=null){
//                ReferenceCountUtil.release(buf);
//                //System.out.println(buf.refCnt());
//            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //删除出现异常的客户端channle，并关闭连接
        Server.clients.remove(ctx.channel());
        ctx.close();
    }
}