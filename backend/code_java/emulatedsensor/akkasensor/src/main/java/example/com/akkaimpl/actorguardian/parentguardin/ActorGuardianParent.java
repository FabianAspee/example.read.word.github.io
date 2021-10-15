package example.com.akkaimpl.actorguardian.parentguardin;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.actorguardian.MessageActorGuardianMain;
import example.com.akkaimpl.actorpatch.parentpatch.ActorPatchParent;
import example.com.akkaimpl.actorpatch.parentpatch.MessageActorPatchParent;
import example.com.akkaimpl.commonimpl.LifeCycle;

public class ActorGuardianParent extends MessageActorGuardianParent {
    private ActorGuardianParent(){
        super();
    }
    public static Behavior<CommandGuardianParent> getInstance(final int numberSensor){
        return Behaviors.setup(ctx -> new ActorGuardianParent().createMsg(ctx,numberSensor));
    }

    @Override
    public <T extends LifeCycle.LifeCycleActor> Behavior<T> createChildActor(ActorContext<T> context, int n) {
        return null;
    }

    @Override
    public <T extends LifeCycle.LifeCycleActor> Behavior<T> removeChildActor(ActorContext<T> context, int n) {
        return null;
    }
}
