package example.com.akkaimpl.actorsensor.parentsensor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.commonimpl.LifeCycle;

public abstract class MessageActorSensorParent implements LifeCycle {
    protected MessageActorSensorParent(){}
    protected interface CommandSensorParent extends LifeCycleActor{}
    public static record CreateSensor(int numberSensor) implements CommandSensorParent {}
    public static record RemoveSensor() implements CommandSensorParent {}
    protected Behavior<CommandSensorParent> createMsg(final ActorContext<CommandSensorParent> context) {
        return Behaviors.receive(CommandSensorParent.class)
                .onMessage(CreateSensor.class, createSensor -> createChildActor(context, createSensor))
                .onMessage(RemoveSensor.class, removeSensor -> removeChildActor(context, removeSensor))
                .build();
    }


}
