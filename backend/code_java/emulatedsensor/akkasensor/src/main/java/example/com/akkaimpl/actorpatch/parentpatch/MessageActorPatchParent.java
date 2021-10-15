package example.com.akkaimpl.actorpatch.parentpatch;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.commonimpl.LifeCycle;

public abstract class MessageActorPatchParent implements LifeCycle {
    protected MessageActorPatchParent(){}
    protected interface CommandPatchParent extends LifeCycleActor{}
    public static record CreateSensor() implements CommandPatchParent {}
    public static record RemoveSensor() implements CommandPatchParent {}
    protected Behavior<CommandPatchParent> createMsg(final ActorContext<CommandPatchParent> context, final int n) {
        return Behaviors.receive(CommandPatchParent.class)
                .onMessage(CreateSensor.class, child -> createChildActor(context, n))
                .onMessage(RemoveSensor.class, child -> removeChildActor(context, n))
                .build();
    }


}
