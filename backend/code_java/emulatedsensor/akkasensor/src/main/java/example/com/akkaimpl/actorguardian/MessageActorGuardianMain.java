package example.com.akkaimpl.actorguardian;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.commonimpl.LifeCycle;

public abstract class MessageActorGuardianMain implements LifeCycle {
    protected MessageActorGuardianMain(){}
    protected interface CommandGuardianMain extends LifeCycleActor {}
    public static record CreateSensor() implements CommandGuardianMain {}
    public static record RemoveSensor() implements CommandGuardianMain {}
    protected Behavior<CommandGuardianMain> createMsg(final ActorContext<CommandGuardianMain> context, final int n) {
        return Behaviors.receive(CommandGuardianMain.class)
                .onMessage(CreateSensor.class, child -> createChildSensor(context, n))
                .onMessage(RemoveSensor.class, child -> removeChildSensor(context, n))
                .build();
    }

}
