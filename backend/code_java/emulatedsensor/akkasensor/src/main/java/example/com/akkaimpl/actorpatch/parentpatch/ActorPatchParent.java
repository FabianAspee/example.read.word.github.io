package example.com.akkaimpl.actorpatch.parentpatch;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.actorsensor.childsensor.ActorSensorImpl;
import example.com.akkaimpl.actorsensor.childsensor.MessageActorSensor;
import example.com.akkaimpl.commonimpl.LifeCycle;
import example.com.akkaimpl.configproperties.ApplicationProperties;
import scala.NotImplementedError;

import java.util.stream.IntStream;

public class ActorPatchParent extends MessageActorPatchParent{
    private ActorPatchParent(){
        super();
    }
    public static Behavior<CommandPatchParent> getInstance(final int numberSensor){
        return Behaviors.setup(ctx -> new ActorPatchParent().createMsg(ctx,numberSensor));
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
