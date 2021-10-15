package example.com.akkaimpl.commonimpl;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;

public interface LifeCycle{
    interface LifeCycleActor{}
    <T extends LifeCycleActor> Behavior<T> createChildSensor(final ActorContext<T> context, final int n);
    <T extends LifeCycleActor> Behavior<T> removeChildSensor(final ActorContext<T> context, final int n);
}
