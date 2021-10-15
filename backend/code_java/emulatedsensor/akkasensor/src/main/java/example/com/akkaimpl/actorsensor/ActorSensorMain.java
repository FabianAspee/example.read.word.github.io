package example.com.akkaimpl.actorsensor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.actorsensor.parentsensor.ActorSensorParent;
import example.com.akkaimpl.actorsensor.parentsensor.MessageActorSensorParent;
import example.com.akkaimpl.configproperties.ApplicationProperties;
import scala.NotImplementedError;

public final class ActorSensorMain extends MessageActorSensorMain{
    public ActorSensorMain() {
        super();
    }
    public static Behavior<CommandSensorMain> getInstance(final int numberSensor){
        return Behaviors.setup(ctx -> new ActorSensorMain().createMsg(ctx,numberSensor));
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> createChildSensor(final ActorContext<T> context, int n) {
        var ref = context.spawn(ActorSensorParent.getInstance(n), ApplicationProperties.getInstance().getSensorName());
        ref.tell(new MessageActorSensorParent.CreateSensor());
        return Behaviors.empty();
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> removeChildSensor(final ActorContext<T> context, int n) {
        //TODO
        throw new NotImplementedError();
    }
}
