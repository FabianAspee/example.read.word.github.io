package example.com.akkaimpl.actormanager;

import akka.actor.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.commonimpl.LoggerImpl;

public abstract class MessageActorManager extends LoggerImpl<ActorManager> {
    protected MessageActorManager() {
        super(ActorManager.class);
    }
    protected interface ManagerCommand{}
    public record RegisterSensor(ActorRef sender) implements ManagerCommand{}
    public record RegisterPatch(ActorRef sender) implements ManagerCommand{}
    public record RegisterGuardian(ActorRef sender) implements ManagerCommand{}
    public record RegisterDashBoard(ActorRef sender) implements ManagerCommand{}
    protected Behavior<ManagerCommand> createManagerMsg(ActorContext<ManagerCommand> ctx){
        return Behaviors.receive(ManagerCommand.class)
                .onMessage(RegisterSensor.class,registerSensor->registerSensor(ctx,registerSensor))
                .onMessage(RegisterPatch.class,registerPatch->registerPatch(ctx,registerPatch))
                .onMessage(RegisterGuardian.class,registerGuardian->registerGuardian(ctx,registerGuardian))
                .onMessage(RegisterDashBoard.class,registerDashBoard->registerDashBoard(ctx,registerDashBoard))
                .build();

    }

    protected abstract Behavior<ManagerCommand> registerSensor(ActorContext<ManagerCommand> ctx, RegisterSensor sender);

    protected abstract Behavior<ManagerCommand> registerPatch(ActorContext<ManagerCommand> ctx, RegisterPatch sender);

    protected abstract Behavior<ManagerCommand> registerGuardian(ActorContext<ManagerCommand> ctx, RegisterGuardian sender);

    protected abstract Behavior<ManagerCommand> registerDashBoard(ActorContext<ManagerCommand> ctx, RegisterDashBoard sender);
}
