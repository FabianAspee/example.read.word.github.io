package example.com.akkaimpl.actorsensor.parentsensor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.commonimpl.LifeCycle;

public abstract class MessageActorSensorParent implements LifeCycle {
    protected MessageActorSensorParent(){}
    protected interface CommandSensorParent extends LifeCycleActor{}
    public static record CreateSensor() implements CommandSensorParent {}
    public static record RemoveSensor() implements CommandSensorParent {}
    protected Behavior<CommandSensorParent> createMsg(final ActorContext<CommandSensorParent> context, final int n) {
        return Behaviors.receive(CommandSensorParent.class)
                .onMessage(CreateSensor.class, child -> createChildSensor(context, n))
                .onMessage(RemoveSensor.class, child -> removeChildSensor(context, n))
                .build();
    }


}
