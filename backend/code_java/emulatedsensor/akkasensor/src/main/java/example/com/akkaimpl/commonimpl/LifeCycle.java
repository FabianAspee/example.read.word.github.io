package example.com.akkaimpl.commonimpl;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;

public interface LifeCycle{
    interface LifeCycleActor{}
    <T extends LifeCycleActor> Behavior<T> createChildActor(final ActorContext<T> context, final int n);
    <T extends LifeCycleActor> Behavior<T> removeChildActor(final ActorContext<T> context, final int n);
}
