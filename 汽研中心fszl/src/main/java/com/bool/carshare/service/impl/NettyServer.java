package com.bool.carshare.service.impl;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
@Component
public class NettyServer {

	public static void start(Integer port) throws Exception{
		// 主工作线程
		EventLoopGroup bossGroup = new NioEventLoopGroup(1); // (1)
		// 子工作线程
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// netty 服务启动类
			ServerBootstrap b = new ServerBootstrap(); // (2)
			// 设置主 -> 子 线程 设置通道
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) // (3)使用的通道
					.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new MessageHandler());
						}
					}).option(ChannelOption.SO_BACKLOG, 128) // (5)设置的参数服务端
					.childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
			// 绑定  端口 并且开始监听客户端
			// Bind and start to accept incoming connections.
			ChannelFuture f = b.bind(port).sync(); // (7)绑定端口

			// Wait until the server socket is closed.
			// In this example, this does not happen, but you can do that to
			// gracefully
			// shut down your server.
			f.channel().closeFuture().sync();
		} finally {
			// 结束工作线程
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
}
