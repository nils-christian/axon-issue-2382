package com.example.demo;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoApplication {

	public static void main( final String[] args ) throws Exception {
		System.setProperty( "optimize-event-consumption", "false" );
		final ConfigurableApplicationContext applicationContext = SpringApplication.run( DemoApplication.class, args );

		Thread.sleep( 2500 );

		final EventGateway eventGateway = applicationContext.getBean( EventGateway.class );
		System.out.println( "Publishing event" );
		eventGateway.publish( 0 );
	}

	@Component
	public class MyEventProcessor {

		@EventHandler
		public void handle( final EventMessage<?> eventMessage ) {
			System.out.println( "Handled: " + eventMessage );
		}

	}

}
