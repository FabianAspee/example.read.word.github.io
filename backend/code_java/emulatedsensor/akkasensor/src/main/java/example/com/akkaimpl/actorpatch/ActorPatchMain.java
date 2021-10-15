package example.com.akkaimpl.actorpatch;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;

public final class ActorPatchMain  extends MessageActorPatchMain {

    public ActorPatchMain(ActorContext<CommandPatchMain> ctx) {
        super();
    }

    public static Behavior<CommandPatchMain> getInstance(final int numberSensor){
        return Behaviors.setup(ctx -> new ActorPatchMain(ctx).createMsg(ctx,numberSensor));
    }
    @Override
    public <T extends LifeCycleActor> Behavior<T> createChildSensor(ActorContext<T> context, int n) {
        return null;
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> removeChildSensor(ActorContext<T> context, int n) {
        return null;
    }
}
