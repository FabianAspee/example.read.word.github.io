package example.com.akkaimpl.actorpatch.childpatch;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.actorsensor.childsensor.MessageActorSensor;
import jnr.ffi.annotations.In;

public abstract class MessageActorPatchChild {
    protected interface CommandPatch {}
    public static record InitPatch() implements CommandPatch {}
    public static record ReceiveInfoSensor() implements CommandPatch {}
    protected Behavior<CommandPatch> createMsg(final ActorContext<CommandPatch> context) {
        return Behaviors.receive(CommandPatch.class)
                .onMessage(InitPatch.class, start -> startPatch(context))
                .onMessage(ReceiveInfoSensor.class, start -> receiveInfoFromSensor(context))
                .build();
    }

    protected abstract Behavior<CommandPatch> startPatch(ActorContext<CommandPatch> context);

    protected abstract Behavior<CommandPatch> receiveInfoFromSensor(ActorContext<CommandPatch> context);

}