package example.com.akkaimpl.actorsensor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.commonimpl.LifeCycle;

public abstract class MessageActorSensorMain implements LifeCycle {
    protected MessageActorSensorMain(){}
    protected interface CommandSensorMain extends LifeCycleActor {}
    public static record CreateSensor() implements CommandSensorMain {}
    public static record RemoveSensor() implements CommandSensorMain {}
    protected Behavior<CommandSensorMain> createMsg(final ActorContext<CommandSensorMain> context, final int n) {
        return Behaviors.receive(CommandSensorMain.class)
                .onMessage(CreateSensor.class, child -> createChildActor(context, n))
                .onMessage(RemoveSensor.class, child -> removeChildActor(context, n))
                .build();
    }

}
