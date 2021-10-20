package example.com.akkaimpl.actorpatch;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.commonimpl.LifeCycle;

public abstract class MessageActorPatchMain implements LifeCycle{
    protected MessageActorPatchMain(){}
    protected interface CommandPatchMain extends LifeCycleActor {}
    public static record CreateSensor(int numberPatchX, int numberPatchY) implements CommandPatchMain {}
    public static record RemoveSensor() implements CommandPatchMain {}
    protected Behavior<CommandPatchMain> createMsg(final ActorContext<CommandPatchMain> context) {
        return Behaviors.receive(CommandPatchMain.class)
                .onMessage(CreateSensor.class, createSensor -> createChildActor(context, createSensor))
                .onMessage(RemoveSensor.class, removeSensor -> createChildActor(context, removeSensor))
                .build();
    }

}
