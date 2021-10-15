package example.com.akkaimpl.actorsensor.childsensor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;

import java.time.Duration;

public abstract class MessageActorSensor {
    protected MessageActorSensor(){}
    protected interface CommandSensor {}
    public static record InitSensor() implements CommandSensor {}
    public static record MoveSensor() implements CommandSensor {}
    protected Behavior<CommandSensor> createMsg(final ActorContext<CommandSensor> context) {
        return Behaviors.receive(CommandSensor.class)
                .onMessage(InitSensor.class, start -> startSensor(context))
                .onMessage(MoveSensor.class, start -> moveSensor(context))
                .build();
    }
    protected abstract Behavior<CommandSensor> startSensor(final ActorContext<CommandSensor> context);
    protected abstract Behavior<CommandSensor> moveSensor(final ActorContext<CommandSensor> context);

}
