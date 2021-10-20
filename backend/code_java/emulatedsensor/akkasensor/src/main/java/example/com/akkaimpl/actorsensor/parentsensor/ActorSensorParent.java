package example.com.akkaimpl.actorsensor.parentsensor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.actorsensor.childsensor.ActorSensorChild;
import example.com.akkaimpl.actorsensor.childsensor.MessageActorSensor.InitSensor;
import example.com.akkaimpl.configproperties.ApplicationProperties;
import scala.NotImplementedError;

import java.util.Optional;
import java.util.stream.IntStream;

public class ActorSensorParent extends MessageActorSensorParent{
    private ActorSensorParent(){
        super();
    }
    public static Behavior<CommandSensorParent> getInstance(){
        return Behaviors.setup(ctx -> new ActorSensorParent().createMsg(ctx));
    }

    @Override
    public CreateSensor getInstance(LifeCycleActor message) {
        if(message instanceof CreateSensor sensor){
            return sensor;
        }
        return null;
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> createChildActor(ActorContext<T> context, T message) {
        Optional.ofNullable(getInstance(message)).ifPresent(createSensor ->
                IntStream.range(0,createSensor.numberSensor()).forEach(index->{
                    var result = context.spawn(ActorSensorChild.getInstance(),
                            ApplicationProperties.getInstance().getSensorName()+index);
                    result.tell(new InitSensor());
        }));
        return Behaviors.same();
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> removeChildActor(ActorContext<T> context, T message) {
        throw new NotImplementedError();
    }
}
