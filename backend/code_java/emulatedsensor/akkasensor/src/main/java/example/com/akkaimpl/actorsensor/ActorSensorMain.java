package example.com.akkaimpl.actorsensor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.actorsensor.parentsensor.ActorSensorParent;
import example.com.akkaimpl.actorsensor.parentsensor.MessageActorSensorParent;
import example.com.akkaimpl.configproperties.ApplicationProperties;
import scala.NotImplementedError;

import java.util.Optional;

public final class ActorSensorMain extends MessageActorSensorMain{
    public ActorSensorMain() {
        super();
    }
    public static Behavior<CommandSensorMain> getInstance(){
        return Behaviors.setup(ctx -> new ActorSensorMain().createMsg(ctx));
    }

    @Override
    public CreateSensor getInstance(LifeCycleActor message) {
        if(message instanceof CreateSensor sensor){
            return sensor;
        }
        return null;
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> createChildActor(final ActorContext<T> context, T message) {
        Optional.ofNullable(getInstance(message)).ifPresent(createSensor->{
            var ref = context.spawn(
                    ActorSensorParent.getInstance(),
                    ApplicationProperties.getInstance().getSensorName());
            ref.tell(new MessageActorSensorParent.CreateSensor(createSensor.numberSensor()));
        });
        return Behaviors.empty();
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> removeChildActor(final ActorContext<T> context, T message) {
        //TODO
        throw new NotImplementedError();
    }

}
