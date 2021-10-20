package example.com.akkaimpl.actorguardian.parentguardin;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.commonimpl.LifeCycle;
import example.com.akkaimpl.commonimpl.LoggerImpl;

public abstract class MessageActorGuardianParent extends LoggerImpl<ActorGuardianParent> implements LifeCycle {
    protected MessageActorGuardianParent(){
        super(ActorGuardianParent.class);
    }
    protected interface CommandGuardianParent extends LifeCycleActor{}
    public static record CreateSensor(int numberGuardian) implements CommandGuardianParent {}
    public static record RemoveSensor() implements CommandGuardianParent {}
    protected Behavior<CommandGuardianParent> createMsg(final ActorContext<CommandGuardianParent> context) {
        return Behaviors.receive(CommandGuardianParent.class)
                .onMessage(CreateSensor.class, createSensor -> createChildActor(context, createSensor))
                .onMessage(RemoveSensor.class, removeSensor -> removeChildActor(context, removeSensor))
                .build();
    }


}