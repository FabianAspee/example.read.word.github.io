package example.com.akkaimpl.actorsensor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.commonimpl.LifeCycle;
import example.com.akkaimpl.commonimpl.LoggerImpl;

public abstract class MessageActorSensorMain extends LoggerImpl<ActorSensorMain> implements LifeCycle {
    protected MessageActorSensorMain(){
        super(ActorSensorMain.class);
    }
    protected interface CommandSensorMain extends LifeCycleActor {}
    public static record CreateSensor(int numberSensor) implements CommandSensorMain {}
    public static record RemoveSensor() implements CommandSensorMain {}
    protected Behavior<CommandSensorMain> createMsg(final ActorContext<CommandSensorMain> context) {
        return Behaviors.receive(CommandSensorMain.class)
                .onMessage(CreateSensor.class, createSensor -> createChildActor(context, createSensor))
                .onMessage(RemoveSensor.class, removeSensor -> removeChildActor(context, removeSensor))
                .build();
    }

}
