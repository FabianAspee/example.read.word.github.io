package example.com.akkaimpl.actorguardian;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.commonimpl.LifeCycle;
import example.com.akkaimpl.commonimpl.LoggerImpl;

public abstract class MessageActorGuardianMain extends LoggerImpl<ActorGuardianMain> implements LifeCycle {
    protected MessageActorGuardianMain(){
        super(ActorGuardianMain.class);
    }
    protected interface CommandGuardianMain extends LifeCycleActor {}
    public static record CreateSensor(int numberGuardian) implements CommandGuardianMain {}
    public static record RemoveSensor() implements CommandGuardianMain {}
    protected Behavior<CommandGuardianMain> createMsg(final ActorContext<CommandGuardianMain> context) {
        return Behaviors.receive(CommandGuardianMain.class)
                .onMessage(CreateSensor.class, createSensor -> createChildActor(context, createSensor))
                .onMessage(RemoveSensor.class, removeSensor -> removeChildActor(context, removeSensor))
                .build();
    }

}
