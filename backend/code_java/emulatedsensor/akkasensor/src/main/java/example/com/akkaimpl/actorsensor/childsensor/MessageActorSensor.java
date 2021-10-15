package example.com.akkaimpl.actorsensor.childsensor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;

import java.time.Duration;

public abstract class MessageActorSensor {
    protected MessageActorSensor(){}
    protected interface CommandSensor {}
    public static record InitSensor() implements CommandSensor {}
    protected Behavior<CommandSensor> createMsg(final ActorContext<CommandSensor> context) {
        return Behaviors.receive(CommandSensor.class)
                .onMessage(InitSensor.class, start -> context.scheduleOnce(Duration.ofSeconds(2),context.getSelf(),new InitSensor()))
                .build();
    }
    protected abstract Behavior<CommandSensor> startSensor(final ActorContext<CommandSensor> context);

}
