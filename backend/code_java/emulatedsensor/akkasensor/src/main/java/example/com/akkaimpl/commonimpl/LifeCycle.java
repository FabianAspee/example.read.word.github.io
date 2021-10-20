package example.com.akkaimpl.commonimpl;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;

import java.util.Optional;

public interface LifeCycle{
    LifeCycleActor getInstance(LifeCycleActor message);
    interface LifeCycleActor{}
    <T extends LifeCycleActor> Behavior<T> createChildActor(final ActorContext<T> context, T message);
    <T extends LifeCycleActor> Behavior<T> removeChildActor(final ActorContext<T> context, T message);


}
