package example.com.akkaimpl.actorpatch.childpatch;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;

public class ActorPatchChild extends MessageActorPatchChild {
    private ActorPatchChild() {
        super();
    }


    public static Behavior<CommandPatch> getInstance() {
        return Behaviors.setup(ctx -> new ActorPatchChild().createMsg(ctx));
    }

    @Override
    protected Behavior<CommandPatch> startPatch(ActorContext<CommandPatch> context) {
        return null;
    }

    @Override
    protected Behavior<CommandPatch> receiveInfoFromSensor(ActorContext<CommandPatch> context) {
        return null;
    }
}