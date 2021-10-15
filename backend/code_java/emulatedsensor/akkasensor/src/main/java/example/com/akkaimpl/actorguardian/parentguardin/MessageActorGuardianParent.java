package example.com.akkaimpl.actorguardian.parentguardin;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.commonimpl.LifeCycle;

public abstract class MessageActorGuardianParent implements LifeCycle {
    protected MessageActorGuardianParent(){}
    protected interface CommandGuardianParent extends LifeCycleActor{}
    public static record CreateSensor() implements CommandGuardianParent {}
    public static record RemoveSensor() implements CommandGuardianParent {}
    protected Behavior<CommandGuardianParent> createMsg(final ActorContext<CommandGuardianParent> context, final int n) {
        return Behaviors.receive(CommandGuardianParent.class)
                .onMessage(CreateSensor.class, child -> createChildActor(context, n))
                .onMessage(RemoveSensor.class, child -> removeChildActor(context, n))
                .build();
    }


}