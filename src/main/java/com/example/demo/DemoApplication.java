package com.example.demo;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		ServerBootstrap serverBootstrap = new ServerBootstrap();

		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService work = Executors.newCachedThreadPool();

		//设置niosocket工厂
		serverBootstrap.setFactory(new NioServerSocketChannelFactory(boss, work));

		//设置管道工厂
		serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline channelPipeline = Channels.pipeline();
				channelPipeline.addLast("helloHandler", new HelloHandler());
				return null;
			}
		});

		serverBootstrap.bind(new InetSocketAddress(10101));
	}

}
