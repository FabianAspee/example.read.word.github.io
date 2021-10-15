package example.com.akkaimpl.actorguardian;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;

public final class ActorGuardianMain extends MessageActorGuardianMain {

    public ActorGuardianMain(ActorContext<CommandGuardianMain> ctx) {
        super();
    }

    public static Behavior<CommandGuardianMain> getInstance(final int numberSensor){
        return Behaviors.setup(ctx -> new ActorGuardianMain(ctx).createMsg(ctx,numberSensor));
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> createChildActor(ActorContext<T> context, int n) {
        return null;
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> removeChildActor(ActorContext<T> context, int n) {
        return null;
    }
}
