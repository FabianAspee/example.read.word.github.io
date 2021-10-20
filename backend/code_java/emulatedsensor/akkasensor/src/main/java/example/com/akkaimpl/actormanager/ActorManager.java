package example.com.akkaimpl.actormanager;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class ActorManager extends MessageActorManager {
    private final List<RegisterSensor> registerSensors = new ArrayList<>();
    private final List<RegisterGuardian> registerGuardian = new ArrayList<>();
    private final List<RegisterPatch> registerPatch = new ArrayList<>();
    private final List<RegisterDashBoard> registerDashBoard = new ArrayList<>();
    @Override
    protected Behavior<ManagerCommand> registerSensor(ActorContext<ManagerCommand> ctx, RegisterSensor sender) {
        registerSensors.add(sender);
        return Behaviors.same();
    }

    @Override
    protected Behavior<ManagerCommand> registerPatch(ActorContext<ManagerCommand> ctx, RegisterPatch sender) {
        registerPatch.add(sender);
        return Behaviors.same();
    }

    @Override
    protected Behavior<ManagerCommand> registerGuardian(ActorContext<ManagerCommand> ctx, RegisterGuardian sender) {
        registerGuardian.add(sender);
        return Behaviors.same();
    }

    @Override
    protected Behavior<ManagerCommand> registerDashBoard(ActorContext<ManagerCommand> ctx, RegisterDashBoard sender) {
        registerDashBoard.add(sender);
        return Behaviors.same();
    }
}
